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

    // цвета
    private static final String BG_COLOR = "#FDF0D5";
    private static final String ACCENT_COLOR = "#075985ff";
    private static final String ACCENT_LIGHT = "#669bbcff";
    private static final String TEXT_CORRECT = "#1275aaff";
    private static final String TEXT_ERROR = "#9c0000ff";
    private static final String TEXT_PENDING = "#616161ff";
    private static final String CURSOR_COLOR = "#154766";

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
            "even", "new", "want", "because", "any", "these", "give", "day", "most", "us",
            "world", "still", "hand", "high", "keep", "thing", "eye", "never", "last", "let",
            "thought", "city", "tree", "cross", "farm", "hard", "start", "might", "story", "saw",
            "far", "sea", "draw", "left", "late", "run", "while", "press", "close", "night",
            "real", "life", "few", "north", "open", "seem", "together", "next", "white", "children",
            "begin", "got", "walk", "example", "ease", "paper", "group", "always", "music", "those",
            "both", "mark", "often", "letter", "until", "mile", "river", "car", "feet", "care",
            "second", "book", "carry", "took", "science", "eat", "room", "friend", "began", "idea",
            "fish", "mountain", "stop", "once", "base", "hear", "horse", "cut", "sure", "watch",
            "color", "face", "wood", "main", "enough", "plain", "girl", "usual", "young", "ready",
            "above", "ever", "red", "list", "though", "feel", "talk", "bird", "soon", "body",
            "dog", "family", "direct", "pose", "leave", "song", "measure", "door", "product", "black",
            "short", "numeral", "class", "wind", "question", "happen", "complete", "ship", "area", "half",
            "rock", "order", "fire", "south", "problem", "piece", "told", "knew", "pass", "since",
            "top", "whole", "king", "space", "heard", "best", "hour", "better", "true", "during"
    };

    private static final String[] RUSSIAN_WORDS = {
            "и", "в", "не", "на", "я", "быть", "он", "с", "что", "а",
            "по", "это", "она", "этот", "к", "но", "они", "мы", "как", "из",
            "у", "который", "то", "за", "свой", "весь", "год", "от", "так", "о",
            "для", "ты", "же", "все", "тот", "мочь", "вы", "человек", "такой", "его",
            "сказать", "только", "или", "еще", "бы", "себя", "один", "уже", "до", "время",
            "если", "сам", "когда", "другой", "вот", "говорить", "наш", "мой", "знать", "стать",
            "при", "чтобы", "дело", "жизнь", "кто", "первый", "очень", "два", "день", "ее",
            "новый", "рука", "даже", "во", "со", "раз", "где", "там", "под", "можно",
            "ну", "какой", "после", "их", "работа", "без", "самый", "потом", "надо", "хотеть",
            "ли", "слово", "идти", "большой", "должен", "место", "иметь", "ничто", "глаз", "видеть",
            "сторона", "тут", "голова", "друг", "дом", "сейчас", "стоять", "лицо", "здесь", "земля",
            "конец", "сделать", "через", "выйти", "три", "взять", "хороший", "тоже", "вода", "более",
            "всегда", "последний", "город", "почему", "вопрос", "понять", "страна", "ответ", "ждать", "дать",
            "часть", "смотреть", "найти", "старый", "нога", "думать", "услышать", "вечер", "случай", "голос",
            "войти", "сидеть", "утро", "много", "ходить", "каждый", "нужный", "следующий", "никто", "писать",
            "любить", "между", "вести", "спросить", "положить", "великий", "против", "дорога", "начать", "душа",
            "белый", "далеко", "высокий", "сила", "тогда", "путь", "считать", "чувствовать", "книга", "молодой",
            "лежать", "вдруг", "оставить", "комната", "отвечать", "улица", "поднять", "бояться", "черный", "история",
            "читать", "небо", "дверь", "свет", "минута", "мать", "просто", "помнить", "держать", "вместе",
            "жена", "получить", "система", "вечер", "народ", "война", "любой", "никакой", "показать", "ребенок", "ера", "абай", "руслан"
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
    
    // клава
    private VBox keyboardBox;
    private Map<String, Label> keyLabels = new HashMap<>();
    

    

    
    // раскладки клавы
    private static final String[][] EN_LAYOUT = {
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
        {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
        {"Z", "X", "C", "V", "B", "N", "M"}
    };
    
    private static final String[][] RU_LAYOUT = {
        {"Й", "Ц", "У", "К", "Е", "Н", "Г", "Ш", "Щ", "З", "Х", "Ъ"},
        {"Ф", "Ы", "В", "А", "П", "Р", "О", "Л", "Д", "Ж", "Э"},
        {"Я", "Ч", "С", "М", "И", "Т", "Ь", "Б", "Ю"}
    };

    @Override
    public void start(Stage primaryStage) {
        words = generateWords();

        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // выбор языка
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

        // тайтл
        Label title = new Label("typer");
        title.setFont(Font.font("Consolas", 48));
        title.setTextFill(Color.web(ACCENT_COLOR));

        // отображ. слов
        wordsDisplay = new TextFlow();
        wordsDisplay.setMaxWidth(800);
        wordsDisplay.setLineSpacing(10);
        wordsDisplay.setStyle("-fx-padding: 20;");
        wordsDisplay.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // стата
        statsLabel = new Label("");
        statsLabel.setFont(Font.font("Consolas", 18));
        statsLabel.setTextFill(Color.web(TEXT_PENDING));

        // инструкции
        instructionLabel = new Label(isRussian ? "начните печатать... (F1 - заново)" : "start typing... (F1 - restart)");
        instructionLabel.setFont(Font.font("Consolas", 16));
        instructionLabel.setTextFill(Color.web(TEXT_PENDING));

        // резы
        resultBox = new VBox(15);
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setVisible(false);

        // отображ. клавы
        keyboardBox = createKeyboard();

        root.getChildren().addAll(langSelector, title, wordsDisplay, instructionLabel, statsLabel, resultBox, keyboardBox);

        updateWordsDisplay();

        Scene scene = new Scene(root, 1600, 900);

        scene.setOnKeyPressed(event -> {
            // подсветка клавы
            highlightKey(event.getCode(), event.getText(), true);
            
            // рестарт на ф1
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
        
        scene.setOnKeyReleased(event -> {
            highlightKey(event.getCode(), event.getText(), false);
        });

        
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
                    
                    // авто завершение последнего слова
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

    //цвета кнопок
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
                    "-fx-background-color: #505050ff;" +
                    "-fx-text-fill: #ffffffff;" +
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
                // выполненные слова
                boolean correct = wordResults.get(wordIdx);
                Text wordText = new Text(word + " ");
                wordText.setFont(Font.font("Consolas", 28));
                wordText.setFill(correct ? Color.web(TEXT_CORRECT) : Color.web(TEXT_ERROR));
                if (!correct) {
                    wordText.setUnderline(true);
                }
                wordsDisplay.getChildren().add(wordText);
            } else if (wordIdx == currentWordIndex) {
                // андерлайн
                for (int charIdx = 0; charIdx < word.length(); charIdx++) {
                    Text charText = new Text(String.valueOf(word.charAt(charIdx)));
                    charText.setFont(Font.font("Consolas", 28));

                    if (charIdx < currentInput.length()) {
                        if (currentInput.charAt(charIdx) == word.charAt(charIdx)) {
                            charText.setFill(Color.web(TEXT_CORRECT)); 
                        } else {
                            charText.setFill(Color.web(TEXT_ERROR)); 
                        }
                    } else if (charIdx == currentInput.length()) {
                        charText.setFill(Color.web(TEXT_PENDING));
                        charText.setStyle("-fx-underline: true; -fx-stroke: " + CURSOR_COLOR + "; -fx-stroke-width: 0.5;");
                        charText.setUnderline(true);
                    } else {
                        charText.setFill(Color.web(TEXT_PENDING)); 
                    }
                    wordsDisplay.getChildren().add(charText);
                }

                // ошибки
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
                // ожидаемые слова
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

        // калькуляция wpm и accuracy
        double minutes = timeSeconds / 60.0;
        int wpm = (int) ((correctChars / 5.0) / minutes);
        int accuracy = (int) ((correctWords * 100.0) / words.size());

        updateWordsDisplay();

        // показ резов
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
        updateKeyboardLayout();
        root.requestFocus();
    }
    
    private VBox createKeyboard() {
        VBox keyboard = new VBox(5);
        keyboard.setAlignment(Pos.CENTER);
        keyboard.setPadding(new Insets(20, 0, 10, 0));
        
        String[][] layout = isRussian ? RU_LAYOUT : EN_LAYOUT;
        double[] offsets = {0, 15, 35}; 
        
        for (int row = 0; row < layout.length; row++) {
            HBox rowBox = new HBox(5);
            rowBox.setAlignment(Pos.CENTER);
            rowBox.setPadding(new Insets(0, 0, 0, offsets[row]));
            
            for (String key : layout[row]) {
                Label keyLabel = createKeyLabel(key);
                keyLabels.put(key.toUpperCase(), keyLabel);
                rowBox.getChildren().add(keyLabel);
            }
            keyboard.getChildren().add(rowBox);
        }
        
        // пробел
        HBox spaceRow = new HBox();
        spaceRow.setAlignment(Pos.CENTER);
        Label spaceLabel = new Label("");
        spaceLabel.setPrefWidth(250);
        spaceLabel.setPrefHeight(35);
        spaceLabel.setStyle(getKeyStyle(false));
        keyLabels.put("SPACE", spaceLabel);
        spaceRow.getChildren().add(spaceLabel);
        keyboard.getChildren().add(spaceRow);
        
        return keyboard;
    }
    
    private Label createKeyLabel(String key) {
        Label label = new Label(key);
        label.setFont(Font.font("Consolas", 14));
        label.setPrefWidth(40);
        label.setPrefHeight(40);
        label.setAlignment(Pos.CENTER);
        label.setStyle(getKeyStyle(false));
        return label;
    }
    
    //цвета клавы
    private String getKeyStyle(boolean pressed) {
        if (pressed) {
            return "-fx-background-color: " + ACCENT_COLOR + ";" +
                   "-fx-text-fill: white;" +
                   "-fx-background-radius: 5;" +
                   "-fx-border-color: " + ACCENT_LIGHT + ";" +
                   "-fx-border-radius: 5;" +
                   "-fx-border-width: 1;";
        } else {
            return "-fx-background-color: #505050ff;" +
                   "-fx-text-fill: #ffffffff;" +
                   "-fx-background-radius: 5;" +
                   "-fx-border-color: #333333;" +
                   "-fx-border-radius: 5;" +
                   "-fx-border-width: 1;";
        }
    }
    
    private void highlightKey(KeyCode code, String text, boolean pressed) {
        String keyToHighlight = null;
        
        if (code == KeyCode.SPACE) {
            keyToHighlight = "SPACE";
        } else if (text != null && !text.isEmpty()) {
            keyToHighlight = text.toUpperCase();
        }
        
        if (keyToHighlight != null && keyLabels.containsKey(keyToHighlight)) {
            keyLabels.get(keyToHighlight).setStyle(getKeyStyle(pressed));
        }
    }
    
    private void updateKeyboardLayout() {
        keyLabels.clear();
        keyboardBox.getChildren().clear();
        
        String[][] layout = isRussian ? RU_LAYOUT : EN_LAYOUT;
        double[] offsets = {0, 15, 35};
        
        for (int row = 0; row < layout.length; row++) {
            HBox rowBox = new HBox(5);
            rowBox.setAlignment(Pos.CENTER);
            rowBox.setPadding(new Insets(0, 0, 0, offsets[row]));
            
            for (String key : layout[row]) {
                Label keyLabel = createKeyLabel(key);
                keyLabels.put(key.toUpperCase(), keyLabel);
                rowBox.getChildren().add(keyLabel);
            }
            keyboardBox.getChildren().add(rowBox);
        }
        
        // пробел
        HBox spaceRow = new HBox();
        spaceRow.setAlignment(Pos.CENTER);
        Label spaceLabel = new Label("");
        spaceLabel.setPrefWidth(250);
        spaceLabel.setPrefHeight(35);
        spaceLabel.setStyle(getKeyStyle(false));
        keyLabels.put("SPACE", spaceLabel);
        spaceRow.getChildren().add(spaceLabel);
        keyboardBox.getChildren().add(spaceRow);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
