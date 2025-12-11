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
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.effect.DropShadow;

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
    private BorderPane root;
    private VBox centerContent;
    
    // клава
    private VBox keyboardBox;
    private Map<String, Label> keyLabels = new HashMap<>();
    
    // профиль
    private ProfileService profileService;
    private VBox mainContent;
    private VBox profileContent;
    private boolean showingProfile = false;
    private ToggleButton profileButton;

    

    
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
        profileService = new ProfileService();
        words = generateWords();

        root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // верхняя панель
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(20, 30, 10, 30));
        topBar.setAlignment(Pos.CENTER_LEFT);

        // выбор языка (слева)
        HBox langSelector = new HBox(10);
        langSelector.setAlignment(Pos.CENTER_LEFT);

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
        
        // кнопка профиля (справа)
        HBox profileBox = new HBox();
        profileBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(profileBox, Priority.ALWAYS);
        
        profileButton = new ToggleButton("👤 Профиль");
        profileButton.setFont(Font.font("Consolas", 14));
        profileButton.setPrefWidth(120);
        profileButton.setPrefHeight(35);
        profileButton.setStyle(
                "-fx-background-color: " + ACCENT_COLOR + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );
        profileButton.setOnAction(e -> toggleProfile());
        
        profileBox.getChildren().add(profileButton);
        topBar.getChildren().addAll(langSelector, profileBox);
        root.setTop(topBar);

        // тайтл
        Label title = new Label("typer");
        title.setFont(Font.font("Consolas", 48));
        title.setTextFill(Color.web(ACCENT_COLOR));
        
        // Тень для заголовка
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.web(ACCENT_COLOR, 0.3));
        titleShadow.setRadius(10);
        title.setEffect(titleShadow);

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

        // главный контент
        mainContent = new VBox(30);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(20));
        mainContent.getChildren().addAll(title, wordsDisplay, instructionLabel, statsLabel, resultBox, keyboardBox);
        
        // контент профиля
        profileContent = createProfileContent();
        profileContent.setVisible(false);
        
        // центральный контейнер
        centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER);
        centerContent.getChildren().add(mainContent);

        root.setCenter(centerContent);

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
                        
                        // Плавное исчезновение инструкции
                        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), instructionLabel);
                        fadeOut.setFromValue(1.0);
                        fadeOut.setToValue(0.0);
                        fadeOut.setOnFinished(e -> instructionLabel.setText(""));
                        fadeOut.play();
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
        
        // Тень для кнопок
        DropShadow buttonShadow = new DropShadow();
        buttonShadow.setColor(Color.web("#000000", 0.2));
        buttonShadow.setRadius(3);
        buttonShadow.setOffsetY(2);
        button.setEffect(buttonShadow);
        
        if (selected) {
            button.setStyle(
                    "-fx-background-color: " + ACCENT_COLOR + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
            );
            
            // Пульсация выбранной кнопки
            ScaleTransition pulse = new ScaleTransition(Duration.millis(200), button);
            pulse.setToX(1.1);
            pulse.setToY(1.1);
            pulse.setAutoReverse(true);
            pulse.setCycleCount(2);
            pulse.play();
        } else {
            button.setStyle(
                    "-fx-background-color: #505050ff;" +
                    "-fx-text-fill: #ffffffff;" +
                    "-fx-background-radius: 5;" +
                    "-fx-border-color: #333333;" +
                    "-fx-border-radius: 5;" +
                    "-fx-cursor: hand;"
            );
            
            // Возврат к нормальному размеру
            ScaleTransition reset = new ScaleTransition(Duration.millis(150), button);
            reset.setToX(1.0);
            reset.setToY(1.0);
            reset.play();
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
                        
                        // Плавное появление напечатанных символов
                        if (charIdx == currentInput.length() - 1) {
                            charText.setOpacity(0);
                            charText.setScaleX(0.5);
                            charText.setScaleY(0.5);
                            
                            FadeTransition fade = new FadeTransition(Duration.millis(100), charText);
                            fade.setFromValue(0);
                            fade.setToValue(1);
                            
                            ScaleTransition scale = new ScaleTransition(Duration.millis(100), charText);
                            scale.setFromX(0.5);
                            scale.setFromY(0.5);
                            scale.setToX(1.0);
                            scale.setToY(1.0);
                            scale.setInterpolator(Interpolator.EASE_OUT);
                            
                            ParallelTransition parallel = new ParallelTransition(fade, scale);
                            parallel.play();
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

        for (int i = 0; i < words.size(); i++) {
            if (wordResults.get(i)) {
                correctWords++;
                correctChars += words.get(i).length();
            }
        }

        // калькуляция wpm и accuracy
        double minutes = timeSeconds / 60.0;
        int wpm = (int) ((correctChars / 5.0) / minutes);
        int accuracy = (int) ((correctWords * 100.0) / words.size());
        
        // сохранение результата
        TestResult result = new TestResult(wpm, accuracy, timeSeconds, correctWords, words.size(), isRussian);
        profileService.saveResult(result);

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
        
        // Плавное появление результатов
        resultBox.setOpacity(0);
        resultBox.setVisible(true);
        resultBox.setScaleX(0.8);
        resultBox.setScaleY(0.8);
        
        FadeTransition fadeIn = new FadeTransition(Duration.millis(400), resultBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(400), resultBox);
        scaleIn.setFromX(0.8);
        scaleIn.setFromY(0.8);
        scaleIn.setToX(1.0);
        scaleIn.setToY(1.0);
        scaleIn.setInterpolator(Interpolator.EASE_OUT);
        
        ParallelTransition parallelTransition = new ParallelTransition(fadeIn, scaleIn);
        parallelTransition.play();
        
        // Анимация цифры WPM
        Timeline wpmAnimation = new Timeline();
        final int finalWpm = wpm;
        for (int i = 0; i <= 20; i++) {
            final int step = i;
            wpmAnimation.getKeyFrames().add(
                new KeyFrame(Duration.millis(i * 25), e -> {
                    int currentWpm = (int)(finalWpm * step / 20.0);
                    wpmValue.setText(String.valueOf(currentWpm));
                })
            );
        }
        wpmAnimation.setDelay(Duration.millis(200));
        wpmAnimation.play();

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

        // Плавное исчезновение результатов
        if (resultBox.isVisible()) {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), resultBox);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                resultBox.setVisible(false);
                resultBox.getChildren().clear();
            });
            fadeOut.play();
        } else {
            resultBox.setVisible(false);
            resultBox.getChildren().clear();
        }
        
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
        
        // Тень для клавиш
        DropShadow keyShadow = new DropShadow();
        keyShadow.setColor(Color.web("#000000", 0.2));
        keyShadow.setRadius(3);
        keyShadow.setOffsetY(2);
        label.setEffect(keyShadow);
        
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
            Label key = keyLabels.get(keyToHighlight);
            
            // Плавная анимация подсветки клавиши
            if (pressed) {
                // Анимация нажатия
                ScaleTransition scaleDown = new ScaleTransition(Duration.millis(50), key);
                scaleDown.setToX(0.95);
                scaleDown.setToY(0.95);
                
                FadeTransition fadeIn = new FadeTransition(Duration.millis(50), key);
                fadeIn.setFromValue(1.0);
                fadeIn.setToValue(1.0);
                
                key.setStyle(getKeyStyle(true));
                scaleDown.play();
            } else {
                // Анимация отпускания
                ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), key);
                scaleUp.setToX(1.0);
                scaleUp.setToY(1.0);
                
                Timeline colorTransition = new Timeline(
                    new KeyFrame(Duration.millis(150), e -> key.setStyle(getKeyStyle(false)))
                );
                
                scaleUp.play();
                colorTransition.play();
            }
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
    
    private VBox createProfileContent() {
        VBox profile = new VBox(25);
        profile.setAlignment(Pos.CENTER);
        profile.setPadding(new Insets(20));
        
        Label profileTitle = new Label(isRussian ? "Профиль" : "Profile");
        profileTitle.setFont(Font.font("Consolas", 42));
        profileTitle.setTextFill(Color.web(ACCENT_COLOR));
        
        // Тень для заголовка
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.web(ACCENT_COLOR, 0.3));
        titleShadow.setRadius(10);
        profileTitle.setEffect(titleShadow);
        
        profile.getChildren().add(profileTitle);
        
        return profile;
    }
    
    private void updateProfileContent() {
        profileContent.getChildren().clear();
        
        Label profileTitle = new Label(isRussian ? "Профиль" : "Profile");
        profileTitle.setFont(Font.font("Consolas", 42));
        profileTitle.setTextFill(Color.web(ACCENT_COLOR));
        
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.web(ACCENT_COLOR, 0.3));
        titleShadow.setRadius(10);
        profileTitle.setEffect(titleShadow);
        
        profileContent.getChildren().add(profileTitle);
        
        // === РЕКОРДЫ ===
        Label recordsLabel = new Label(isRussian ? "🏆 Рекорды WPM" : "🏆 WPM Records");
        recordsLabel.setFont(Font.font("Consolas", 28));
        recordsLabel.setTextFill(Color.web(ACCENT_COLOR));
        recordsLabel.setPadding(new Insets(30, 0, 20, 0));
        profileContent.getChildren().add(recordsLabel);
        
        // Рекорды WPM по языкам
        HBox languageRecords = new HBox(50);
        languageRecords.setAlignment(Pos.CENTER);
        languageRecords.setPadding(new Insets(10, 0, 20, 0));
        
        VBox enRecords = createBestWpmCard(false);
        VBox ruRecords = createBestWpmCard(true);
        
        languageRecords.getChildren().addAll(enRecords, ruRecords);
        profileContent.getChildren().add(languageRecords);
        
        // Разделитель
        javafx.scene.shape.Line separator = new javafx.scene.shape.Line();
        separator.setStartX(0);
        separator.setEndX(600);
        separator.setStroke(Color.web(ACCENT_LIGHT));
        separator.setStrokeWidth(2);
        HBox separatorBox = new HBox(separator);
        separatorBox.setAlignment(Pos.CENTER);
        separatorBox.setPadding(new Insets(10, 0, 10, 0));
        profileContent.getChildren().add(separatorBox);
        
        // === ИСТОРИЯ ===
        Label historyLabel = new Label(isRussian ? "📜 История тестов" : "📜 Test History");
        historyLabel.setFont(Font.font("Consolas", 28));
        historyLabel.setTextFill(Color.web(ACCENT_COLOR));
        historyLabel.setPadding(new Insets(20, 0, 15, 0));
        profileContent.getChildren().add(historyLabel);
        
        List<TestResult> recentResults = profileService.getRecentResults(15);
        
        if (recentResults.isEmpty()) {
            Label noResults = new Label(isRussian ? "Результатов пока нет" : "No results yet");
            noResults.setFont(Font.font("Consolas", 16));
            noResults.setTextFill(Color.web(TEXT_PENDING));
            noResults.setPadding(new Insets(20));
            profileContent.getChildren().add(noResults);
        } else {
            VBox historyList = new VBox(6);
            historyList.setAlignment(Pos.CENTER);
            historyList.setMaxWidth(1100);
            historyList.setPadding(new Insets(5, 10, 10, 10));
            
            // История построчно
            for (int i = 0; i < recentResults.size(); i++) {
                TestResult result = recentResults.get(i);
                HBox historyItem = createHistoryItem(result, i + 1);
                historyList.getChildren().add(historyItem);
            }
            
            // ScrollPane для истории
            javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(historyList);
            scrollPane.setFitToWidth(true);
            scrollPane.setMaxHeight(450);
            scrollPane.setPrefHeight(450);
            scrollPane.setMaxWidth(1120);
            scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: " + BG_COLOR + ";" +
                "-fx-border-color: transparent;"
            );
            scrollPane.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED);
            
            // Центрирование ScrollPane
            HBox scrollContainer = new HBox(scrollPane);
            scrollContainer.setAlignment(Pos.CENTER);
            
            profileContent.getChildren().add(scrollContainer);
        }
    }
    
    private HBox createHistoryItem(TestResult result, int index) {
        HBox item = new HBox(10);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(10, 15, 10, 15));
        item.setMaxWidth(1080);
        item.setPrefHeight(50);
        item.setStyle(
            "-fx-background-color: rgba(80, 80, 80, 0.2);" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: rgba(102, 155, 188, 0.25);" +
            "-fx-border-radius: 8;" +
            "-fx-border-width: 1;"
        );
        
        // Номер
        Label numLabel = new Label(String.format("%d.", index));
        numLabel.setFont(Font.font("Consolas", 13));
        numLabel.setTextFill(Color.web(TEXT_PENDING));
        numLabel.setPrefWidth(30);
        numLabel.setAlignment(Pos.CENTER_RIGHT);
        
        // Дата и время
        Label dateLabel = new Label(result.getFormattedDate());
        dateLabel.setFont(Font.font("Consolas", 13));
        dateLabel.setTextFill(Color.web(TEXT_PENDING));
        dateLabel.setPrefWidth(120);
        dateLabel.setAlignment(Pos.CENTER_LEFT);
        
        // Язык
        Label langLabel = new Label(result.getLanguage());
        langLabel.setFont(Font.font("Consolas", 12));
        langLabel.setTextFill(Color.web(ACCENT_LIGHT));
        langLabel.setPrefWidth(35);
        langLabel.setAlignment(Pos.CENTER);
        langLabel.setStyle(
            "-fx-background-color: rgba(7, 89, 133, 0.2);" +
            "-fx-background-radius: 4;" +
            "-fx-padding: 3 6 3 6;"
        );
        
        // WPM
        Label wpmTitleLabel = new Label("WPM:");
        wpmTitleLabel.setFont(Font.font("Consolas", 11));
        wpmTitleLabel.setTextFill(Color.web(TEXT_PENDING));
        
        Label wpmValue = new Label(String.valueOf(result.getWpm()));
        wpmValue.setFont(Font.font("Consolas", 15));
        wpmValue.setTextFill(Color.web(TEXT_CORRECT));
        wpmValue.setPrefWidth(40);
        wpmValue.setAlignment(Pos.CENTER_RIGHT);
        
        // Точность
        Label accTitleLabel = new Label(isRussian ? "Точн.:" : "Acc:");
        accTitleLabel.setFont(Font.font("Consolas", 11));
        accTitleLabel.setTextFill(Color.web(TEXT_PENDING));
        
        Label accValue = new Label(result.getAccuracy() + "%");
        accValue.setFont(Font.font("Consolas", 15));
        accValue.setTextFill(Color.web(TEXT_CORRECT));
        accValue.setPrefWidth(45);
        accValue.setAlignment(Pos.CENTER_RIGHT);
        
        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        // Время
        Label timeLabel = new Label(String.format("%.1fs", result.getTimeSeconds()));
        timeLabel.setFont(Font.font("Consolas", 11));
        timeLabel.setTextFill(Color.web(TEXT_PENDING));
        timeLabel.setPrefWidth(50);
        timeLabel.setAlignment(Pos.CENTER_RIGHT);
        
        // Слова
        Label wordsLabel = new Label(String.format("%d/%d", result.getCorrectWords(), result.getTotalWords()));
        wordsLabel.setFont(Font.font("Consolas", 11));
        wordsLabel.setTextFill(Color.web(TEXT_PENDING));
        wordsLabel.setPrefWidth(50);
        wordsLabel.setAlignment(Pos.CENTER_RIGHT);
        
        item.getChildren().addAll(
            numLabel, dateLabel, langLabel, 
            wpmTitleLabel, wpmValue, 
            accTitleLabel, accValue, 
            spacer, timeLabel, wordsLabel
        );
        
        return item;
    }
    
    private VBox createBestWpmCard(boolean russian) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20, 25, 20, 25));
        card.setStyle(
            "-fx-background-color: rgba(80, 80, 80, 0.25);" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: " + ACCENT_COLOR + ";" +
            "-fx-border-radius: 12;" +
            "-fx-border-width: 2;"
        );
        card.setPrefWidth(280);
        card.setPrefHeight(200);
        
        // Язык
        Label langLabel = new Label(russian ? "RU" : "EN");
        langLabel.setFont(Font.font("Consolas", 22));
        langLabel.setTextFill(Color.web(ACCENT_LIGHT));
        
        TestResult bestWpm = profileService.getBestWpm(russian);
        int totalTests = profileService.getTotalTests(russian);
        
        // Spacer
        Region spacer1 = new Region();
        VBox.setVgrow(spacer1, Priority.ALWAYS);
        spacer1.setMinHeight(3);
        
        // WPM значение
        Label wpmValue = new Label(bestWpm != null ? String.valueOf(bestWpm.getWpm()) : "-");
        wpmValue.setFont(Font.font("Consolas", 52));
        wpmValue.setTextFill(Color.web(ACCENT_COLOR));
        
        // WPM лейбл
        Label wpmLabel = new Label("WPM");
        wpmLabel.setFont(Font.font("Consolas", 14));
        wpmLabel.setTextFill(Color.web(TEXT_PENDING));
        
        // Spacer
        Region spacer2 = new Region();
        VBox.setVgrow(spacer2, Priority.ALWAYS);
        spacer2.setMinHeight(3);
        
        // Точность
        Label accuracyLabel = new Label(bestWpm != null ? 
            (isRussian ? "Точность: " : "Accuracy: ") + bestWpm.getAccuracy() + "%" : "-");
        accuracyLabel.setFont(Font.font("Consolas", 12));
        accuracyLabel.setTextFill(Color.web(TEXT_PENDING));
        
        // Дата
        Label dateLabel = new Label(bestWpm != null ? bestWpm.getFormattedDate() : "");
        dateLabel.setFont(Font.font("Consolas", 10));
        dateLabel.setTextFill(Color.web(TEXT_PENDING));
        dateLabel.setOpacity(0.6);
        
        // Всего тестов
        Label testsLabel = new Label((isRussian ? "Тестов: " : "Tests: ") + totalTests);
        testsLabel.setFont(Font.font("Consolas", 10));
        testsLabel.setTextFill(Color.web(TEXT_PENDING));
        testsLabel.setOpacity(0.6);
        
        card.getChildren().addAll(langLabel, spacer1, wpmValue, wpmLabel, spacer2, accuracyLabel, dateLabel, testsLabel);
        
        return card;
    }
    
    private void toggleProfile() {
        showingProfile = !showingProfile;
        
        if (showingProfile) {
            // Переход к профилю
            updateProfileContent();
            
            FadeTransition fadeOutMain = new FadeTransition(Duration.millis(200), mainContent);
            fadeOutMain.setFromValue(1.0);
            fadeOutMain.setToValue(0.0);
            fadeOutMain.setOnFinished(e -> {
                centerContent.getChildren().clear();
                centerContent.getChildren().add(profileContent);
                profileContent.setVisible(true);
                
                FadeTransition fadeInProfile = new FadeTransition(Duration.millis(300), profileContent);
                fadeInProfile.setFromValue(0.0);
                fadeInProfile.setToValue(1.0);
                fadeInProfile.play();
            });
            fadeOutMain.play();
            
            profileButton.setSelected(true);
            profileButton.setText(isRussian ? "← Назад" : "← Back");
        } else {
            // Возврат к тесту
            FadeTransition fadeOutProfile = new FadeTransition(Duration.millis(200), profileContent);
            fadeOutProfile.setFromValue(1.0);
            fadeOutProfile.setToValue(0.0);
            fadeOutProfile.setOnFinished(e -> {
                centerContent.getChildren().clear();
                centerContent.getChildren().add(mainContent);
                mainContent.setVisible(true);
                
                FadeTransition fadeInMain = new FadeTransition(Duration.millis(300), mainContent);
                fadeInMain.setFromValue(0.0);
                fadeInMain.setToValue(1.0);
                fadeInMain.play();
            });
            fadeOutProfile.play();
            
            profileButton.setSelected(false);
            profileButton.setText(isRussian ? "👤 Профиль" : "👤 Profile");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
