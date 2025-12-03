package com.typer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.*;

public class TyperApp extends Application {

    // Colors - Black & Burgundy theme
    private static final String BG_COLOR = "#0a0a0a";
    private static final String ACCENT_COLOR = "#8B0000"; // Dark red/burgundy
    private static final String ACCENT_LIGHT = "#DC143C"; // Crimson
    private static final String TEXT_CORRECT = "#e8e8e8";
    private static final String TEXT_ERROR = "#ff4444";
    private static final String TEXT_PENDING = "#4a4a4a";
    private static final String CURSOR_COLOR = "#DC143C";

    private static final String[] ENGLISH_WORDS = {
            "the", "be", "to", "of", "and", "a", "in", "that", "have", "I",
            "it", "for", "not", "on", "with", "he", "as", "you", "do", "at",
            "this", "but", "his", "by", "from", "they", "we", "say", "her", "she",
            "or", "an", "will", "my", "one", "all", "would", "there", "their", "what",
            "so", "up", "out", "if", "about", "who", "get", "which", "go", "me",
            "when", "make", "can", "like", "time", "no", "just", "him", "know", "take",
            "people", "into", "year", "your", "good", "some", "could", "them", "see", "other",
            "than", "then", "now", "look", "only", "come", "its", "over", "think", "also",
            "back", "after", "use", "two", "how", "our", "work", "first", "well", "way",
            "even", "new", "want", "because", "any", "these", "give", "day", "most", "us"
    };

    private static final String[] RUSSIAN_WORDS = {
            "и", "в", "не", "на", "я", "быть", "он", "с", "что", "а",
            "по", "это", "она", "этот", "к", "но", "они", "мы", "как", "из",
            "у", "который", "то", "за", "свой", "что", "весь", "год", "от", "так",
            "о", "для", "ты", "же", "все", "тот", "мочь", "вы", "человек", "такой",
            "его", "сказать", "только", "или", "ещё", "бы", "себя", "один", "как", "уже",
            "до", "время", "если", "сам", "когда", "другой", "вот", "говорить", "наш", "мой",
            "знать", "стать", "при", "чтобы", "дело", "жизнь", "кто", "первый", "очень", "два",
            "день", "её", "новый", "рука", "даже", "во", "со", "раз", "где", "там",
            "под", "можно", "ну", "какой", "после", "их", "работа", "без", "самый", "потом",
            "надо", "хотеть", "ли", "слово", "идти", "большой", "должен", "место", "иметь", "ничто"
    };

    private static final int WORD_COUNT = 10;

    private String[] currentWordPool = ENGLISH_WORDS;
    private boolean isRussian = false;

    private List<String> words;
    private int currentWordIndex = 0;
    private StringBuilder currentInput = new StringBuilder();
    private List<Boolean> wordResults = new ArrayList<>();
    private long startTime = 0;
    private boolean testStarted = false;
    private boolean testFinished = false;

    private TextFlow wordsDisplay;
    private Label statsLabel;
    private Label instructionLabel;
    private VBox resultBox;
    private ToggleButton enButton;
    private ToggleButton ruButton;
    private VBox root;

    @Override
    public void start(Stage primaryStage) {
        words = generateWords();

        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // Language selector
        HBox langSelector = new HBox(10);
        langSelector.setAlignment(Pos.CENTER);

        ToggleGroup langGroup = new ToggleGroup();

        enButton = new ToggleButton("EN");
        enButton.setToggleGroup(langGroup);
        enButton.setSelected(true);
        styleToggleButton(enButton, true);

        ruButton = new ToggleButton("RU");
        ruButton.setToggleGroup(langGroup);
        styleToggleButton(ruButton, false);

        enButton.setOnAction(e -> {
            if (!testStarted) {
                isRussian = false;
                currentWordPool = ENGLISH_WORDS;
                styleToggleButton(enButton, true);
                styleToggleButton(ruButton, false);
                restartTest();
            } else {
                enButton.setSelected(!isRussian);
                ruButton.setSelected(isRussian);
            }
        });

        ruButton.setOnAction(e -> {
            if (!testStarted) {
                isRussian = true;
                currentWordPool = RUSSIAN_WORDS;
                styleToggleButton(enButton, false);
                styleToggleButton(ruButton, true);
                restartTest();
            } else {
                enButton.setSelected(!isRussian);
                ruButton.setSelected(isRussian);
            }
        });

        langSelector.getChildren().addAll(enButton, ruButton);

        // Title
        Label title = new Label("typer");
        title.setFont(Font.font("Consolas", 48));
        title.setTextFill(Color.web(ACCENT_COLOR));

        // Words display
        wordsDisplay = new TextFlow();
        wordsDisplay.setMaxWidth(800);
        wordsDisplay.setLineSpacing(10);
        wordsDisplay.setStyle("-fx-padding: 20;");
        wordsDisplay.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Stats label
        statsLabel = new Label("");
        statsLabel.setFont(Font.font("Consolas", 18));
        statsLabel.setTextFill(Color.web(TEXT_PENDING));

        // Instruction label
        instructionLabel = new Label(isRussian ? "начните печатать... (F1 - заново)" : "start typing... (F1 - restart)");
        instructionLabel.setFont(Font.font("Consolas", 16));
        instructionLabel.setTextFill(Color.web(TEXT_PENDING));

        // Result box (hidden initially)
        resultBox = new VBox(15);
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setVisible(false);

        root.getChildren().addAll(langSelector, title, wordsDisplay, instructionLabel, statsLabel, resultBox);

        updateWordsDisplay();

        Scene scene = new Scene(root, 900, 600);

        scene.setOnKeyPressed(event -> {
            // F1 always restarts
            if (event.getCode() == KeyCode.F1) {
                event.consume();
                restartTest();
                return;
            }

            if (testFinished) {
                return;
            }

            if (event.getCode() == KeyCode.BACK_SPACE) {
                if (currentInput.length() > 0) {
                    currentInput.deleteCharAt(currentInput.length() - 1);
                    updateWordsDisplay();
                }
            } else if (event.getCode() == KeyCode.SPACE) {
                if (currentInput.length() > 0) {
                    processWordComplete();
                }
            } else if (event.getCode() == KeyCode.TAB) {
                event.consume();
            }
        });

        // Keep focus on root
        root.setFocusTraversable(true);

        scene.setOnKeyTyped(event -> {
            if (testFinished) return;

            String character = event.getCharacter();
            if (character.length() == 1 && !character.equals(" ") && !character.equals("\b") && !character.equals("\r")) {
                char c = character.charAt(0);
                if (Character.isLetter(c)) {
                    if (!testStarted) {
                        testStarted = true;
                        startTime = System.currentTimeMillis();
                        instructionLabel.setText("");
                    }
                    currentInput.append(c);
                    
                    // Auto-complete only the LAST word when length matches
                    String currentWord = words.get(currentWordIndex);
                    if (currentWordIndex == words.size() - 1 && currentInput.length() >= currentWord.length()) {
                        processWordComplete();
                    } else {
                        updateWordsDisplay();
                    }
                }
            }
        });

        primaryStage.setTitle("Typer - Typing Test");
        primaryStage.setScene(scene);
        primaryStage.show();

        root.requestFocus();
    }

    private void styleToggleButton(ToggleButton button, boolean selected) {
        button.setFont(Font.font("Consolas", 14));
        button.setPrefWidth(50);
        button.setPrefHeight(30);
        if (selected) {
            button.setStyle(
                    "-fx-background-color: " + ACCENT_COLOR + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
            );
        } else {
            button.setStyle(
                    "-fx-background-color: #1a1a1a;" +
                    "-fx-text-fill: #666666;" +
                    "-fx-background-radius: 5;" +
                    "-fx-border-color: #333333;" +
                    "-fx-border-radius: 5;" +
                    "-fx-cursor: hand;"
            );
        }
    }

    private List<String> generateWords() {
        List<String> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < WORD_COUNT; i++) {
            result.add(currentWordPool[random.nextInt(currentWordPool.length)]);
        }
        return result;
    }

    private void updateWordsDisplay() {
        wordsDisplay.getChildren().clear();

        for (int wordIdx = 0; wordIdx < words.size(); wordIdx++) {
            String word = words.get(wordIdx);

            if (wordIdx < currentWordIndex) {
                // Completed words
                boolean correct = wordResults.get(wordIdx);
                Text wordText = new Text(word + " ");
                wordText.setFont(Font.font("Consolas", 28));
                wordText.setFill(correct ? Color.web(TEXT_CORRECT) : Color.web(TEXT_ERROR));
                if (!correct) {
                    wordText.setUnderline(true);
                }
                wordsDisplay.getChildren().add(wordText);
            } else if (wordIdx == currentWordIndex) {
                // Current word - show character by character with cursor as underline
                for (int charIdx = 0; charIdx < word.length(); charIdx++) {
                    Text charText = new Text(String.valueOf(word.charAt(charIdx)));
                    charText.setFont(Font.font("Consolas", 28));

                    if (charIdx < currentInput.length()) {
                        if (currentInput.charAt(charIdx) == word.charAt(charIdx)) {
                            charText.setFill(Color.web(TEXT_CORRECT)); // Correct
                        } else {
                            charText.setFill(Color.web(TEXT_ERROR)); // Wrong
                        }
                    } else if (charIdx == currentInput.length()) {
                        // Cursor position - underline the next character to type
                        charText.setFill(Color.web(TEXT_PENDING));
                        charText.setStyle("-fx-underline: true; -fx-stroke: " + CURSOR_COLOR + "; -fx-stroke-width: 0.5;");
                        charText.setUnderline(true);
                    } else {
                        charText.setFill(Color.web(TEXT_PENDING)); // Not typed yet
                    }
                    wordsDisplay.getChildren().add(charText);
                }

                // Extra characters typed (errors)
                if (currentInput.length() > word.length()) {
                    String extra = currentInput.substring(word.length());
                    Text extraText = new Text(extra);
                    extraText.setFont(Font.font("Consolas", 28));
                    extraText.setFill(Color.web(TEXT_ERROR));
                    wordsDisplay.getChildren().add(extraText);
                }

                Text space = new Text(" ");
                space.setFont(Font.font("Consolas", 28));
                wordsDisplay.getChildren().add(space);
            } else {
                // Future words
                Text wordText = new Text(word + " ");
                wordText.setFont(Font.font("Consolas", 28));
                wordText.setFill(Color.web(TEXT_PENDING));
                wordsDisplay.getChildren().add(wordText);
            }
        }
    }

    private void processWordComplete() {
        String typed = currentInput.toString();
        String expected = words.get(currentWordIndex);
        boolean correct = typed.equals(expected);
        wordResults.add(correct);

        currentWordIndex++;
        currentInput.setLength(0);

        if (currentWordIndex >= words.size()) {
            finishTest();
        } else {
            updateWordsDisplay();
        }
    }

    private void finishTest() {
        testFinished = true;
        long endTime = System.currentTimeMillis();
        double timeSeconds = (endTime - startTime) / 1000.0;

        int correctWords = 0;
        int correctChars = 0;
        int totalChars = 0;

        for (int i = 0; i < words.size(); i++) {
            totalChars += words.get(i).length();
            if (wordResults.get(i)) {
                correctWords++;
                correctChars += words.get(i).length();
            }
        }

        // WPM calculation: (correct characters / 5) / minutes
        double minutes = timeSeconds / 60.0;
        int wpm = (int) ((correctChars / 5.0) / minutes);
        int accuracy = (int) ((correctWords * 100.0) / words.size());

        updateWordsDisplay();

        // Show results
        resultBox.getChildren().clear();

        Label wpmLabel = new Label("WPM");
        wpmLabel.setFont(Font.font("Consolas", 18));
        wpmLabel.setTextFill(Color.web(TEXT_PENDING));

        Label wpmValue = new Label(String.valueOf(wpm));
        wpmValue.setFont(Font.font("Consolas", 64));
        wpmValue.setTextFill(Color.web(ACCENT_LIGHT));

        Label accLabel = new Label(isRussian ? "точность" : "accuracy");
        accLabel.setFont(Font.font("Consolas", 18));
        accLabel.setTextFill(Color.web(TEXT_PENDING));

        Label accValue = new Label(accuracy + "%");
        accValue.setFont(Font.font("Consolas", 48));
        accValue.setTextFill(Color.web(ACCENT_LIGHT));

        Label timeLabel = new Label(String.format(isRussian ? "время: %.1fс" : "time: %.1fs", timeSeconds));
        timeLabel.setFont(Font.font("Consolas", 18));
        timeLabel.setTextFill(Color.web(TEXT_PENDING));

        Label wordsCorrectLabel = new Label(String.format(isRussian ? "слова: %d/%d" : "words: %d/%d", correctWords, words.size()));
        wordsCorrectLabel.setFont(Font.font("Consolas", 18));
        wordsCorrectLabel.setTextFill(Color.web(TEXT_PENDING));

        Label restartLabel = new Label(isRussian ? "F1 - начать заново" : "F1 - restart");
        restartLabel.setFont(Font.font("Consolas", 14));
        restartLabel.setTextFill(Color.web(TEXT_PENDING));

        HBox statsRow = new HBox(50);
        statsRow.setAlignment(Pos.CENTER);

        VBox wpmBox = new VBox(5);
        wpmBox.setAlignment(Pos.CENTER);
        wpmBox.getChildren().addAll(wpmLabel, wpmValue);

        VBox accBox = new VBox(5);
        accBox.setAlignment(Pos.CENTER);
        accBox.getChildren().addAll(accLabel, accValue);

        statsRow.getChildren().addAll(wpmBox, accBox);

        resultBox.getChildren().addAll(statsRow, timeLabel, wordsCorrectLabel, restartLabel);
        resultBox.setVisible(true);

        instructionLabel.setText("");
    }

    private void restartTest() {
        words = generateWords();
        currentWordIndex = 0;
        currentInput.setLength(0);
        wordResults.clear();
        testStarted = false;
        testFinished = false;
        startTime = 0;

        resultBox.setVisible(false);
        resultBox.getChildren().clear();
        instructionLabel.setText(isRussian ? "начните печатать... (F1 - заново)" : "start typing... (F1 - restart)");
        statsLabel.setText("");

        updateWordsDisplay();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
