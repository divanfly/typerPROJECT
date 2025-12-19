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

// гайд по клавиатуре
public class Keyboard extends VBox {
    
    Map<String, StackPane> keys = new HashMap<>();
    Map<String, String> colors = new HashMap<>();
    boolean rus;
    
    public Keyboard(boolean r) {
        rus = r;
        initc();
        build();
    }
    
    void initc() {
        // левая рука
        colors.put("1", "#FF6B6B"); colors.put("!", "#FF6B6B");
        colors.put("2", "#FFA500"); colors.put("@", "#FFA500");
        colors.put("3", "#FFD700"); colors.put("#", "#FFD700");
        colors.put("4", "#90EE90"); colors.put("$", "#90EE90");
        colors.put("5", "#90EE90"); colors.put("%", "#90EE90");
        
        colors.put("Q", "#FF6B6B"); colors.put("Й", "#FF6B6B");
        colors.put("A", "#FF6B6B"); colors.put("Ф", "#FF6B6B");
        colors.put("Z", "#FF6B6B"); colors.put("Я", "#FF6B6B");
        colors.put("`", "#FF6B6B"); colors.put("Ё", "#FF6B6B");
        
        colors.put("W", "#FFA500"); colors.put("Ц", "#FFA500");
        colors.put("S", "#FFA500"); colors.put("Ы", "#FFA500");
        colors.put("X", "#FFA500"); colors.put("Ч", "#FFA500");
        
        colors.put("E", "#FFD700"); colors.put("У", "#FFD700");
        colors.put("D", "#FFD700"); colors.put("В", "#FFD700");
        colors.put("C", "#FFD700"); colors.put("С", "#FFD700");
        
        colors.put("R", "#90EE90"); colors.put("К", "#90EE90");
        colors.put("F", "#90EE90"); colors.put("А", "#90EE90");
        colors.put("V", "#90EE90"); colors.put("М", "#90EE90");
        colors.put("T", "#90EE90"); colors.put("Е", "#90EE90");
        colors.put("G", "#90EE90"); colors.put("П", "#90EE90");
        colors.put("B", "#90EE90"); colors.put("И", "#90EE90");
        
        // правая рука
        colors.put("6", "#9370DB"); colors.put("^", "#9370DB");
        colors.put("7", "#9370DB"); colors.put("&", "#9370DB");
        
        colors.put("Y", "#9370DB"); colors.put("Н", "#9370DB");
        colors.put("H", "#9370DB"); colors.put("Р", "#9370DB");
        colors.put("N", "#9370DB"); colors.put("Т", "#9370DB");
        colors.put("U", "#87CEEB"); colors.put("Г", "#87CEEB");
        colors.put("J", "#87CEEB"); colors.put("О", "#87CEEB");
        colors.put("M", "#87CEEB"); colors.put("Ь", "#87CEEB");
        
        colors.put("8", "#ADD8E6"); colors.put("*", "#ADD8E6");
        colors.put("I", "#ADD8E6"); colors.put("Ш", "#ADD8E6");
        colors.put("K", "#ADD8E6"); colors.put("Л", "#ADD8E6");
        colors.put(",", "#ADD8E6"); colors.put("Б", "#ADD8E6");
        colors.put("<", "#ADD8E6");
        
        colors.put("9", "#CCFF00"); colors.put("(", "#CCFF00");
        colors.put("O", "#CCFF00"); colors.put("Щ", "#CCFF00");
        colors.put("L", "#CCFF00"); colors.put("Д", "#CCFF00");
        colors.put(".", "#CCFF00"); colors.put("Ю", "#CCFF00");
        colors.put(">", "#CCFF00");
        
        colors.put("0", "#CCFF00"); colors.put(")", "#CCFF00");
        colors.put("-", "#CCFF00"); colors.put("_", "#CCFF00");
        colors.put("=", "#CCFF00"); colors.put("+", "#CCFF00");
        colors.put("P", "#CCFF00"); colors.put("З", "#CCFF00");
        colors.put("[", "#CCFF00"); colors.put("Х", "#CCFF00");
        colors.put("]", "#CCFF00"); colors.put("Ъ", "#CCFF00");
        colors.put(";", "#CCFF00"); colors.put("Ж", "#CCFF00");
        colors.put(":", "#CCFF00");
        colors.put("'", "#CCFF00"); colors.put("Э", "#CCFF00");
        colors.put("\"", "#CCFF00");
        colors.put("/", "#CCFF00");
        colors.put("?", "#CCFF00");
        colors.put("\\", "#CCFF00");
        colors.put("|", "#CCFF00");
    }
    
    void build() {
        setAlignment(Pos.CENTER);
        setSpacing(15);
        setPadding(new Insets(20));
        
        VBox kb = mkb();
        HBox h = mh();
        
        getChildren().addAll(kb, h);
    }
    
    VBox mkb() {
        VBox kb = new VBox(5);
        kb.setAlignment(Pos.CENTER);
        
        String[][][] layout = rus ? new String[][][] {
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
            HBox r = new HBox(5);
            r.setAlignment(Pos.CENTER);
            
            for (String[] k : row) {
                String main = k[0];
                String alt = k.length > 1 ? k[1] : "";
                
                StackPane p = mk(main, alt);
                keys.put(main.toUpperCase(), p);
                if (!alt.isEmpty()) keys.put(alt.toUpperCase(), p);
                
                r.getChildren().add(p);
            }
            
            kb.getChildren().add(r);
        }
        
        // пробел
        HBox sr = new HBox();
        sr.setAlignment(Pos.CENTER);
        StackPane sp = msp();
        keys.put(" ", sp);
        sr.getChildren().add(sp);
        kb.getChildren().add(sr);
        
        return kb;
    }
    
    StackPane mk(String main, String alt) {
        StackPane p = new StackPane();
        p.setPrefSize(55, 55);
        p.setMinSize(55, 55);
        p.setMaxSize(55, 55);
        
        String c = colors.getOrDefault(main, "#CCCCCC");
        
        Rectangle bg = new Rectangle(55, 55);
        bg.setArcWidth(8);
        bg.setArcHeight(8);
        bg.setFill(Color.web(c));
        bg.setStroke(Color.web("#333333"));
        bg.setStrokeWidth(2);
        
        VBox box = new VBox(2);
        box.setAlignment(Pos.CENTER);
        
        if (!alt.isEmpty()) {
            Label a = new Label(alt);
            a.setFont(Font.font("Consolas", 10));
            a.setStyle("-fx-text-fill: #000000; -fx-font-weight: normal;");
            box.getChildren().add(a);
        }
        
        Label m = new Label(main);
        m.setFont(Font.font("Consolas", 16));
        m.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold;");
        box.getChildren().add(m);
        
        p.getChildren().addAll(bg, box);
        return p;
    }
    
    StackPane msp() {
        StackPane p = new StackPane();
        p.setPrefSize(400, 55);
        p.setMinSize(400, 55);
        
        Rectangle bg = new Rectangle(400, 55);
        bg.setArcWidth(8);
        bg.setArcHeight(8);
        bg.setFill(Color.web("#D3D3D3"));
        bg.setStroke(Color.web("#333333"));
        bg.setStrokeWidth(2);
        
        Label l = new Label("SPACE");
        l.setFont(Font.font("Consolas", 14));
        l.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold;");
        
        p.getChildren().addAll(bg, l);
        return p;
    }
    
    HBox mh() {
        HBox h = new HBox(100);
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(30, 0, 0, 0));
        
        VBox left = mhand(true);
        VBox right = mhand(false);
        
        h.getChildren().addAll(left, right);
        return h;
    }
    
    VBox mhand(boolean left) {
        VBox hand = new VBox(10);
        hand.setAlignment(Pos.CENTER);
        
        HBox fingers = new HBox(5);
        fingers.setAlignment(Pos.CENTER);
        
        String[] cols = left ? 
            new String[]{"#FF6B6B", "#FFA500", "#FFD700", "#90EE90"} : 
            new String[]{"#9370DB", "#87CEEB", "#ADD8E6", "#CCFF00"};
        
        String[] names = left ?
            new String[]{"Pinky", "Ring", "Middle", "Index"} :
            new String[]{"Index", "Middle", "Ring", "Pinky"};
        
        for (int i = 0; i < 4; i++) {
            VBox f = mfing(cols[i], names[i]);
            fingers.getChildren().add(f);
        }
        
        Label lbl = new Label(left ? "LEFT HAND" : "RIGHT HAND");
        lbl.setFont(Font.font("Consolas", 12));
        lbl.setStyle("-fx-font-weight: bold;");
        
        hand.getChildren().addAll(lbl, fingers);
        return hand;
    }
    
    VBox mfing(String col, String name) {
        VBox f = new VBox(5);
        f.setAlignment(Pos.CENTER);
        
        Rectangle r = new Rectangle(30, 80);
        r.setArcWidth(15);
        r.setArcHeight(15);
        r.setFill(Color.web(col));
        r.setStroke(Color.web("#333333"));
        r.setStrokeWidth(2);
        
        Label n = new Label(name);
        n.setFont(Font.font("Consolas", 9));
        
        f.getChildren().addAll(r, n);
        return f;
    }
    
    public void hl(String key) {
        String up = key.toUpperCase();
        StackPane p = keys.get(up);
        
        if (p != null) {
            Rectangle bg = (Rectangle) p.getChildren().get(0);
            Color orig = (Color) bg.getFill();
            
            // анимка
            ScaleTransition sc = new ScaleTransition(Duration.millis(100), p);
            sc.setToX(0.95);
            sc.setToY(0.95);
            sc.setAutoReverse(true);
            sc.setCycleCount(2);
            
            FillTransition fl = new FillTransition(Duration.millis(100), bg);
            fl.setToValue(orig.brighter());
            fl.setAutoReverse(true);
            fl.setCycleCount(2);
            
            ParallelTransition pt = new ParallelTransition(sc, fl);
            pt.play();
        }
    }
}
