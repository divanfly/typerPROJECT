package com.typer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.animation.*;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class KeyboardGuide extends VBox {
    
    private Map<String, StackPane> keyButtons = new HashMap<>();
    private Map<String, String> fingerColors = new HashMap<>();
    private boolean isRussian;
    
    public KeyboardGuide(boolean isRussian) {
        this.isRussian = isRussian;
        setupColors();
        createKeyboardVisualization();
    }
    
    private void setupColors() {
        // Левая рука
        fingerColors.put("1", "#FF6B6B"); fingerColors.put("!", "#FF6B6B"); // Мизинец левый - красный
        fingerColors.put("2", "#FFA500"); fingerColors.put("@", "#FFA500"); // Безымянный левый - оранжевый
        fingerColors.put("3", "#FFD700"); fingerColors.put("#", "#FFD700"); // Средний левый - желтый
        fingerColors.put("4", "#90EE90"); fingerColors.put("$", "#90EE90"); // Указательный левый - зеленый
        fingerColors.put("5", "#90EE90"); fingerColors.put("%", "#90EE90");
        
        fingerColors.put("Q", "#FF6B6B"); fingerColors.put("Й", "#FF6B6B");
        fingerColors.put("A", "#FF6B6B"); fingerColors.put("Ф", "#FF6B6B");
        fingerColors.put("Z", "#FF6B6B"); fingerColors.put("Я", "#FF6B6B");
        fingerColors.put("`", "#FF6B6B"); fingerColors.put("Ё", "#FF6B6B");
        
        fingerColors.put("W", "#FFA500"); fingerColors.put("Ц", "#FFA500");
        fingerColors.put("S", "#FFA500"); fingerColors.put("Ы", "#FFA500");
        fingerColors.put("X", "#FFA500"); fingerColors.put("Ч", "#FFA500");
        
        fingerColors.put("E", "#FFD700"); fingerColors.put("У", "#FFD700");
        fingerColors.put("D", "#FFD700"); fingerColors.put("В", "#FFD700");
        fingerColors.put("C", "#FFD700"); fingerColors.put("С", "#FFD700");
        
        fingerColors.put("R", "#90EE90"); fingerColors.put("К", "#90EE90");
        fingerColors.put("F", "#90EE90"); fingerColors.put("А", "#90EE90");
        fingerColors.put("V", "#90EE90"); fingerColors.put("М", "#90EE90");
        fingerColors.put("T", "#90EE90"); fingerColors.put("Е", "#90EE90");
        fingerColors.put("G", "#90EE90"); fingerColors.put("П", "#90EE90");
        fingerColors.put("B", "#90EE90"); fingerColors.put("И", "#90EE90");
        
        // Правая рука
        fingerColors.put("6", "#9370DB"); fingerColors.put("^", "#9370DB");
        fingerColors.put("7", "#9370DB"); fingerColors.put("&", "#9370DB");
        
        fingerColors.put("Y", "#9370DB"); fingerColors.put("Н", "#9370DB");
        fingerColors.put("H", "#9370DB"); fingerColors.put("Р", "#9370DB");
        fingerColors.put("N", "#9370DB"); fingerColors.put("Т", "#9370DB");
        fingerColors.put("U", "#87CEEB"); fingerColors.put("Г", "#87CEEB");
        fingerColors.put("J", "#87CEEB"); fingerColors.put("О", "#87CEEB");
        fingerColors.put("M", "#87CEEB"); fingerColors.put("Ь", "#87CEEB");
        
        fingerColors.put("8", "#ADD8E6"); fingerColors.put("*", "#ADD8E6");
        fingerColors.put("I", "#ADD8E6"); fingerColors.put("Ш", "#ADD8E6");
        fingerColors.put("K", "#ADD8E6"); fingerColors.put("Л", "#ADD8E6");
        fingerColors.put(",", "#ADD8E6"); fingerColors.put("Б", "#ADD8E6");
        fingerColors.put("<", "#ADD8E6");
        
        fingerColors.put("9", "#CCFF00"); fingerColors.put("(", "#CCFF00");
        fingerColors.put("O", "#CCFF00"); fingerColors.put("Щ", "#CCFF00");
        fingerColors.put("L", "#CCFF00"); fingerColors.put("Д", "#CCFF00");
        fingerColors.put(".", "#CCFF00"); fingerColors.put("Ю", "#CCFF00");
        fingerColors.put(">", "#CCFF00");
        
        fingerColors.put("0", "#CCFF00"); fingerColors.put(")", "#CCFF00");
        fingerColors.put("-", "#CCFF00"); fingerColors.put("_", "#CCFF00");
        fingerColors.put("=", "#CCFF00"); fingerColors.put("+", "#CCFF00");
        fingerColors.put("P", "#CCFF00"); fingerColors.put("З", "#CCFF00");
        fingerColors.put("[", "#CCFF00"); fingerColors.put("Х", "#CCFF00");
        fingerColors.put("]", "#CCFF00"); fingerColors.put("Ъ", "#CCFF00");
        fingerColors.put(";", "#CCFF00"); fingerColors.put("Ж", "#CCFF00");
        fingerColors.put(":", "#CCFF00");
        fingerColors.put("'", "#CCFF00"); fingerColors.put("Э", "#CCFF00");
        fingerColors.put("\"", "#CCFF00");
        fingerColors.put("/", "#CCFF00");
        fingerColors.put("?", "#CCFF00");
        fingerColors.put("\\", "#CCFF00");
        fingerColors.put("|", "#CCFF00");
    }
    
    private void createKeyboardVisualization() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        
        // Клавиатура
        VBox keyboard = createKeyboard();
        
        // Руки
        HBox hands = createHands();
        
        this.getChildren().addAll(keyboard, hands);
    }
    
    private VBox createKeyboard() {
        VBox keyboard = new VBox(5);
        keyboard.setAlignment(Pos.CENTER);
        
        String[][][] layout = isRussian ? new String[][][] {
            {{"Ё", "`"}, {"1", "!"}, {"2", "\""}, {"3", "№"}, {"4", ";"}, {"5", "%"}, {"6", ":"}, {"7", "?"}, {"8", "*"}, {"9", "("}, {"0", ")"}, {"-", "_"}, {"=", "+"}},
            {{"Й", "Q"}, {"Ц", "W"}, {"У", "E"}, {"К", "R"}, {"Е", "T"}, {"Н", "Y"}, {"Г", "U"}, {"Ш", "I"}, {"Щ", "O"}, {"З", "P"}, {"Х", "["}, {"Ъ", "]"}},
            {{"Ф", "A"}, {"Ы", "S"}, {"В", "D"}, {"А", "F"}, {"П", "G"}, {"Р", "H"}, {"О", "J"}, {"Л", "K"}, {"Д", "L"}, {"Ж", ";"}, {"Э", "'"}},
            {{"Я", "Z"}, {"Ч", "X"}, {"С", "C"}, {"М", "V"}, {"И", "B"}, {"Т", "N"}, {"Ь", "M"}, {"Б", ","}, {"Ю", "."}}
        } : new String[][][] {
            {{"`", "~"}, {"1", "!"}, {"2", "@"}, {"3", "#"}, {"4", "$"}, {"5", "%"}, {"6", "^"}, {"7", "&"}, {"8", "*"}, {"9", "("}, {"0", ")"}, {"-", "_"}, {"=", "+"}},
            {{"Q", "Й"}, {"W", "Ц"}, {"E", "У"}, {"R", "К"}, {"T", "Е"}, {"Y", "Н"}, {"U", "Г"}, {"I", "Ш"}, {"O", "Щ"}, {"P", "З"}, {"[", "Х"}, {"]", "Ъ"}},
            {{"A", "Ф"}, {"S", "Ы"}, {"D", "В"}, {"F", "А"}, {"G", "П"}, {"H", "Р"}, {"J", "О"}, {"K", "Л"}, {"L", "Д"}, {";", "Ж"}, {"'", "Э"}},
            {{"Z", "Я"}, {"X", "Ч"}, {"C", "С"}, {"V", "М"}, {"B", "И"}, {"N", "Т"}, {"M", "Ь"}, {",", "Б"}, {".", "Ю"}}
        };
        
        for (String[][] row : layout) {
            HBox rowBox = new HBox(5);
            rowBox.setAlignment(Pos.CENTER);
            
            for (String[] keyData : row) {
                String mainKey = keyData[0];
                String altKey = keyData.length > 1 ? keyData[1] : "";
                
                StackPane keyPane = createKey(mainKey, altKey);
                keyButtons.put(mainKey.toUpperCase(), keyPane);
                if (!altKey.isEmpty()) {
                    keyButtons.put(altKey.toUpperCase(), keyPane);
                }
                
                rowBox.getChildren().add(keyPane);
            }
            
            keyboard.getChildren().add(rowBox);
        }
        
        // Пробел
        HBox spaceRow = new HBox();
        spaceRow.setAlignment(Pos.CENTER);
        StackPane spaceKey = createSpaceBar();
        keyButtons.put(" ", spaceKey);
        spaceRow.getChildren().add(spaceKey);
        keyboard.getChildren().add(spaceRow);
        
        return keyboard;
    }
    
    private StackPane createKey(String mainText, String altText) {
        StackPane keyPane = new StackPane();
        keyPane.setPrefSize(55, 55);
        keyPane.setMinSize(55, 55);
        keyPane.setMaxSize(55, 55);
        
        String color = fingerColors.getOrDefault(mainText, "#CCCCCC");
        
        Rectangle bg = new Rectangle(55, 55);
        bg.setArcWidth(8);
        bg.setArcHeight(8);
        bg.setFill(Color.web(color));
        bg.setStroke(Color.web("#333333"));
        bg.setStrokeWidth(2);
        
        VBox textBox = new VBox(2);
        textBox.setAlignment(Pos.CENTER);
        
        if (!altText.isEmpty()) {
            Label altLabel = new Label(altText);
            altLabel.setFont(Font.font("Consolas", 10));
            altLabel.setStyle("-fx-text-fill: #000000; -fx-font-weight: normal;");
            textBox.getChildren().add(altLabel);
        }
        
        Label mainLabel = new Label(mainText);
        mainLabel.setFont(Font.font("Consolas", 16));
        mainLabel.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold;");
        textBox.getChildren().add(mainLabel);
        
        keyPane.getChildren().addAll(bg, textBox);
        return keyPane;
    }
    
    private StackPane createSpaceBar() {
        StackPane keyPane = new StackPane();
        keyPane.setPrefSize(400, 55);
        keyPane.setMinSize(400, 55);
        
        Rectangle bg = new Rectangle(400, 55);
        bg.setArcWidth(8);
        bg.setArcHeight(8);
        bg.setFill(Color.web("#D3D3D3"));
        bg.setStroke(Color.web("#333333"));
        bg.setStrokeWidth(2);
        
        Label label = new Label("SPACE");
        label.setFont(Font.font("Consolas", 14));
        label.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold;");
        
        keyPane.getChildren().addAll(bg, label);
        return keyPane;
    }
    
    private HBox createHands() {
        HBox hands = new HBox(100);
        hands.setAlignment(Pos.CENTER);
        hands.setPadding(new Insets(30, 0, 0, 0));
        
        // Левая рука
        VBox leftHand = createHand(true);
        
        // Правая рука
        VBox rightHand = createHand(false);
        
        hands.getChildren().addAll(leftHand, rightHand);
        return hands;
    }
    
    private VBox createHand(boolean isLeft) {
        VBox hand = new VBox(10);
        hand.setAlignment(Pos.CENTER);
        
        HBox fingers = new HBox(5);
        fingers.setAlignment(Pos.CENTER);
        
        String[] colors = isLeft ? 
            new String[]{"#FF6B6B", "#FFA500", "#FFD700", "#90EE90"} : 
            new String[]{"#9370DB", "#87CEEB", "#ADD8E6", "#CCFF00"};
        
        String[] names = isLeft ?
            new String[]{"Pinky", "Ring", "Middle", "Index"} :
            new String[]{"Index", "Middle", "Ring", "Pinky"};
        
        for (int i = 0; i < 4; i++) {
            VBox finger = createFinger(colors[i], names[i]);
            fingers.getChildren().add(finger);
        }
        
        Label handLabel = new Label(isLeft ? "LEFT HAND" : "RIGHT HAND");
        handLabel.setFont(Font.font("Consolas", 12));
        handLabel.setStyle("-fx-font-weight: bold;");
        
        hand.getChildren().addAll(handLabel, fingers);
        return hand;
    }
    
    private VBox createFinger(String color, String name) {
        VBox finger = new VBox(5);
        finger.setAlignment(Pos.CENTER);
        
        // Палец
        Rectangle fingerRect = new Rectangle(30, 80);
        fingerRect.setArcWidth(15);
        fingerRect.setArcHeight(15);
        fingerRect.setFill(Color.web(color));
        fingerRect.setStroke(Color.web("#333333"));
        fingerRect.setStrokeWidth(2);
        
        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Consolas", 9));
        
        finger.getChildren().addAll(fingerRect, nameLabel);
        return finger;
    }
    
    public void highlightKey(String key) {
        String upperKey = key.toUpperCase();
        StackPane keyPane = keyButtons.get(upperKey);
        
        if (keyPane != null) {
            Rectangle bg = (Rectangle) keyPane.getChildren().get(0);
            Color originalColor = (Color) bg.getFill();
            
            // Анимация нажатия
            ScaleTransition scale = new ScaleTransition(Duration.millis(100), keyPane);
            scale.setToX(0.95);
            scale.setToY(0.95);
            scale.setAutoReverse(true);
            scale.setCycleCount(2);
            
            // Подсветка
            FillTransition fill = new FillTransition(Duration.millis(100), bg);
            fill.setToValue(originalColor.brighter());
            fill.setAutoReverse(true);
            fill.setCycleCount(2);
            
            ParallelTransition parallel = new ParallelTransition(scale, fill);
            parallel.play();
        }
    }
}
