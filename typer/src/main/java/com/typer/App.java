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

// главное приложение
public class App extends Application {

    // цвета
    static final String bg = "#FDF0D5";
    static final String acc = "#075985ff";
    static final String accl = "#669bbcff";
    static final String green = "#1275aaff";
    static final String red = "#9c0000ff";
    static final String gray = "#616161ff";
    static final String cursor = "#154766";

    static final String[] enw = {
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

    static final String[] ruw = {
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

    static final int cnt = 10;

    String[] pool = enw;
    boolean rus = false;

    List<String> words;
    int idx = 0;
    StringBuilder inp = new StringBuilder();
    List<Boolean> results = new ArrayList<>();
    long start = 0;
    boolean started = false;
    boolean done = false;

    TextFlow display;
    Label stats;
    Label info;
    VBox rbox;
    ToggleButton enbtn;
    ToggleButton rubtn;
    BorderPane root;
    VBox center;
    
    // клава
    VBox kbox;
    Map<String, Label> klabs = new HashMap<>();
    
    // профиль
    Profile prof;
    VBox main;
    VBox pvw;
    VBox lvw;
    boolean sprof = false;
    boolean slearn = false;
    javafx.scene.control.Button pbtn;
    javafx.scene.control.Button lbtn;
    Keyboard kg;
    
    // раскладки клавы
    static final String[][] enl = {
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
        {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
        {"Z", "X", "C", "V", "B", "N", "M"}
    };
    
    static final String[][] rul = {
        {"Й", "Ц", "У", "К", "Е", "Н", "Г", "Ш", "Щ", "З", "Х", "Ъ"},
        {"Ф", "Ы", "В", "А", "П", "Р", "О", "Л", "Д", "Ж", "Э"},
        {"Я", "Ч", "С", "М", "И", "Т", "Ь", "Б", "Ю"}
    };

    @Override
    public void start(Stage stage) {
        prof = new Profile();
        words = gen();

        root = new BorderPane();
        root.setStyle("-fx-background-color: " + bg + ";");

        // верхняя панель
        HBox top = new HBox();
        top.setPadding(new Insets(20, 30, 10, 30));
        top.setAlignment(Pos.CENTER_LEFT);

        // выбор языка (слева)
        HBox lang = new HBox(10);
        lang.setAlignment(Pos.CENTER_LEFT);

        ToggleGroup grp = new ToggleGroup();

        enbtn = new ToggleButton("EN");
        enbtn.setToggleGroup(grp);
        enbtn.setSelected(true);
        stbtn(enbtn, true);

        rubtn = new ToggleButton("RU");
        rubtn.setToggleGroup(grp);
        stbtn(rubtn, false);

        enbtn.setOnAction(e -> {
            if (!started) {
                rus = false;
                pool = enw;
                stbtn(enbtn, true);
                stbtn(rubtn, false);
                restart();
            } else {
                enbtn.setSelected(!rus);
                rubtn.setSelected(rus);
            }
        });

        rubtn.setOnAction(e -> {
            if (!started) {
                rus = true;
                pool = ruw;
                stbtn(enbtn, false);
                stbtn(rubtn, true);
                restart();
            } else {
                enbtn.setSelected(!rus);
                rubtn.setSelected(rus);
            }
        });

        lang.getChildren().addAll(enbtn, rubtn);
        
        // кнопки справа (Learning и Profile)
        HBox btns = new HBox(10);
        btns.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(btns, Priority.ALWAYS);
        
        lbtn = new javafx.scene.control.Button("📚 Learning");
        lbtn.setFont(Font.font("Consolas", 14));
        lbtn.setPrefWidth(120);
        lbtn.setPrefHeight(35);
        lbtn.setStyle(
                "-fx-background-color: " + acc + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );
        lbtn.setOnAction(e -> tlearn());
        
        pbtn = new javafx.scene.control.Button("👤 Profile");
        pbtn.setFont(Font.font("Consolas", 14));
        pbtn.setPrefWidth(120);
        pbtn.setPrefHeight(35);
        pbtn.setStyle(
                "-fx-background-color: " + acc + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );
        pbtn.setOnAction(e -> tprof());
        
        btns.getChildren().addAll(lbtn, pbtn);
        top.getChildren().addAll(lang, btns);
        root.setTop(top);

        // тайтл
        Label title = new Label("typer");
        title.setFont(Font.font("Consolas", 48));
        title.setTextFill(Color.web(acc));
        
        // Тень для заголовка
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(acc, 0.3));
        shadow.setRadius(10);
        title.setEffect(shadow);

        // отображ. слов
        display = new TextFlow();
        display.setMaxWidth(800);
        display.setLineSpacing(10);
        display.setStyle("-fx-padding: 20;");
        display.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // стата
        stats = new Label("");
        stats.setFont(Font.font("Consolas", 18));
        stats.setTextFill(Color.web(gray));

        // инструкции
        info = new Label(rus ? "начните печатать... (F1 - заново)" : "start typing... (F1 - restart)");
        info.setFont(Font.font("Consolas", 16));
        info.setTextFill(Color.web(gray));

        // резы
        rbox = new VBox(15);
        rbox.setAlignment(Pos.CENTER);
        rbox.setVisible(false);

        // отображ. клавы
        kbox = mkb();

        // главный контент
        main = new VBox(30);
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(20));
        main.getChildren().addAll(title, display, info, stats, rbox, kbox);
        
        // контент профиля
        pvw = mprof();
        pvw.setVisible(false);
        
        // контент обучения
        lvw = mlearn();
        lvw.setVisible(false);
        
        // центральный контейнер
        center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.getChildren().add(main);

        root.setCenter(center);

        updisp();

        Scene scene = new Scene(root, 1600, 900);

        scene.setOnKeyPressed(e -> {
            // подсветка клавы на странице обучения
            if (slearn && kg != null) {
                String k = e.getText();
                if (k.isEmpty() && e.getCode() == KeyCode.SPACE) k = " ";
                if (!k.isEmpty()) kg.hl(k);
                return;
            }
            
            // подсветка клавы
            hl(e.getCode(), e.getText(), true);
            
            // рестарт на ф1
            if (e.getCode() == KeyCode.F1) {
                e.consume();
                restart();
                return;
            }

            if (done) return;

            if (e.getCode() == KeyCode.BACK_SPACE) {
                if (inp.length() > 0) {
                    inp.deleteCharAt(inp.length() - 1);
                    updisp();
                }
            } else if (e.getCode() == KeyCode.SPACE) {
                if (inp.length() > 0) next();
            } else if (e.getCode() == KeyCode.TAB) {
                e.consume();
            }
        });
        
        scene.setOnKeyReleased(e -> hl(e.getCode(), e.getText(), false));
        
        root.setFocusTraversable(true);

        scene.setOnKeyTyped(e -> {
            if (done) return;

            String ch = e.getCharacter();
            if (ch.length() == 1 && !ch.equals(" ") && !ch.equals("\b") && !ch.equals("\r")) {
                char c = ch.charAt(0);
                if (Character.isLetter(c)) {
                    if (!started) {
                        started = true;
                        start = System.currentTimeMillis();
                        
                        FadeTransition fade = new FadeTransition(Duration.millis(300), info);
                        fade.setFromValue(1.0);
                        fade.setToValue(0.0);
                        fade.setOnFinished(ev -> info.setText(""));
                        fade.play();
                    }
                    inp.append(c);
                    
                    // авто завершение последнего слова
                    String word = words.get(idx);
                    if (idx == words.size() - 1 && inp.length() >= word.length()) {
                        next();
                    } else {
                        updisp();
                    }
                }
            }
        });

        stage.setTitle("Typer - Typing Test");
        stage.setScene(scene);
        stage.show();

        root.requestFocus();
    }

    //цвета кнопок
    void stbtn(ToggleButton btn, boolean sel) {
        btn.setFont(Font.font("Consolas", 14));
        btn.setPrefWidth(50);
        btn.setPrefHeight(30);
        
        // тени на кнопочки
        DropShadow sh = new DropShadow();
        sh.setColor(Color.web("#000000", 0.2));
        sh.setRadius(3);
        sh.setOffsetY(2);
        btn.setEffect(sh);
        
        if (sel) {
            btn.setStyle(
                    "-fx-background-color: " + acc + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
            );
            
            // пульсация
            ScaleTransition pulse = new ScaleTransition(Duration.millis(200), btn);
            pulse.setToX(1.1);
            pulse.setToY(1.1);
            pulse.setAutoReverse(true);
            pulse.setCycleCount(2);
            pulse.play();
        } else {
            btn.setStyle(
                    "-fx-background-color: #505050ff;" +
                    "-fx-text-fill: #ffffffff;" +
                    "-fx-background-radius: 5;" +
                    "-fx-border-color: #333333;" +
                    "-fx-border-radius: 5;" +
                    "-fx-cursor: hand;"
            );
            
            ScaleTransition reset = new ScaleTransition(Duration.millis(150), btn);
            reset.setToX(1.0);
            reset.setToY(1.0);
            reset.play();
        }
    }

    List<String> gen() {
        List<String> res = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < cnt; i++) {
            res.add(pool[rand.nextInt(pool.length)]);
        }
        return res;
    }

    void updisp() {
        display.getChildren().clear();

        for (int w = 0; w < words.size(); w++) {
            String word = words.get(w);

            if (w < idx) {
                // выполненные слова
                boolean ok = results.get(w);
                Text t = new Text(word + " ");
                t.setFont(Font.font("Consolas", 28));
                t.setFill(ok ? Color.web(green) : Color.web(red));
                if (!ok) t.setUnderline(true);
                
                display.getChildren().add(t);
            } else if (w == idx) {
                // андерлайн
                for (int i = 0; i < word.length(); i++) {
                    Text t = new Text(String.valueOf(word.charAt(i)));
                    t.setFont(Font.font("Consolas", 28));

                    if (i < inp.length()) {
                        if (inp.charAt(i) == word.charAt(i)) {
                            t.setFill(Color.web(green)); 
                        } else {
                            t.setFill(Color.web(red)); 
                        }

                        if (i == inp.length() - 1) {
                            t.setOpacity(0);
                            t.setScaleX(0.5);
                            t.setScaleY(0.5);
                            
                            FadeTransition fade = new FadeTransition(Duration.millis(100), t);
                            fade.setFromValue(0);
                            fade.setToValue(1);
                            
                            ScaleTransition scale = new ScaleTransition(Duration.millis(100), t);
                            scale.setFromX(0.5);
                            scale.setFromY(0.5);
                            scale.setToX(1.0);
                            scale.setToY(1.0);
                            scale.setInterpolator(Interpolator.EASE_OUT);
                            
                            ParallelTransition p = new ParallelTransition(fade, scale);
                            p.play();
                        }
                    } else if (i == inp.length()) {
                        t.setFill(Color.web(gray));
                        t.setStyle("-fx-underline: true; -fx-stroke: " + cursor + "; -fx-stroke-width: 0.5;");
                        t.setUnderline(true);
                    } else {
                        t.setFill(Color.web(gray)); 
                    }
                    display.getChildren().add(t);
                }

                // ошибки
                if (inp.length() > word.length()) {
                    String extra = inp.substring(word.length());
                    Text t = new Text(extra);
                    t.setFont(Font.font("Consolas", 28));
                    t.setFill(Color.web(red));
                    display.getChildren().add(t);
                }

                Text sp = new Text(" ");
                sp.setFont(Font.font("Consolas", 28));
                display.getChildren().add(sp);
            } else {
                // ожидаемые слова
                Text t = new Text(word + " ");
                t.setFont(Font.font("Consolas", 28));
                t.setFill(Color.web(gray));
                display.getChildren().add(t);
            }
        }
    }

    void next() {
        String typed = inp.toString();
        String expected = words.get(idx);
        boolean ok = typed.equals(expected);
        results.add(ok);

        idx++;
        inp.setLength(0);

        if (idx >= words.size()) {
            finish();
        } else {
            updisp();
        }
    }

    void finish() {
        done = true;
        long end = System.currentTimeMillis();
        double time = (end - start) / 1000.0;

        int correct = 0;
        int chars = 0;

        for (int i = 0; i < words.size(); i++) {
            if (results.get(i)) {
                correct++;
                chars += words.get(i).length();
            }
        }

        // калькуляция wpm и accuracy
        double mins = time / 60.0;
        int wpm = (int) ((chars / 5.0) / mins);
        int accuracy = (int) ((correct * 100.0) / words.size());
        
        // сохранение результата
        Result r = new Result(wpm, accuracy, time, correct, words.size(), rus);
        prof.add(r);

        updisp();

        // показ резов
        rbox.getChildren().clear();

        Label wl = new Label("WPM");
        wl.setFont(Font.font("Consolas", 18));
        wl.setTextFill(Color.web(gray));

        Label wv = new Label(String.valueOf(wpm));
        wv.setFont(Font.font("Consolas", 64));
        wv.setTextFill(Color.web(accl));

        Label al = new Label(rus ? "точность" : "accuracy");
        al.setFont(Font.font("Consolas", 18));
        al.setTextFill(Color.web(gray));

        Label av = new Label(accuracy + "%");
        av.setFont(Font.font("Consolas", 48));
        av.setTextFill(Color.web(accl));

        Label tl = new Label(String.format(rus ? "время: %.1fс" : "time: %.1fs", time));
        tl.setFont(Font.font("Consolas", 18));
        tl.setTextFill(Color.web(gray));

        Label cl = new Label(String.format(rus ? "слова: %d/%d" : "words: %d/%d", correct, words.size()));
        cl.setFont(Font.font("Consolas", 18));
        cl.setTextFill(Color.web(gray));

        Label rl = new Label(rus ? "F1 - начать заново" : "F1 - restart");
        rl.setFont(Font.font("Consolas", 14));
        rl.setTextFill(Color.web(gray));

        HBox row = new HBox(50);
        row.setAlignment(Pos.CENTER);

        VBox wb = new VBox(5);
        wb.setAlignment(Pos.CENTER);
        wb.getChildren().addAll(wl, wv);

        VBox ab = new VBox(5);
        ab.setAlignment(Pos.CENTER);
        ab.getChildren().addAll(al, av);

        row.getChildren().addAll(wb, ab);

        rbox.getChildren().addAll(row, tl, cl, rl);
        
        rbox.setOpacity(0);
        rbox.setVisible(true);
        rbox.setScaleX(0.8);
        rbox.setScaleY(0.8);
        
        FadeTransition fi = new FadeTransition(Duration.millis(400), rbox);
        fi.setFromValue(0);
        fi.setToValue(1);
        
        ScaleTransition si = new ScaleTransition(Duration.millis(400), rbox);
        si.setFromX(0.8);
        si.setFromY(0.8);
        si.setToX(1.0);
        si.setToY(1.0);
        si.setInterpolator(Interpolator.EASE_OUT);
        
        ParallelTransition pt = new ParallelTransition(fi, si);
        pt.play();
        
        // анимка wpm
        Timeline anim = new Timeline();
        final int fw = wpm;
        for (int i = 0; i <= 20; i++) {
            final int step = i;
            anim.getKeyFrames().add(
                new KeyFrame(Duration.millis(i * 25), e -> {
                    int cur = (int)(fw * step / 20.0);
                    wv.setText(String.valueOf(cur));
                })
            );
        }
        anim.setDelay(Duration.millis(200));
        anim.play();

        info.setText("");
    }

    void restart() {
        words = gen();
        idx = 0;
        inp.setLength(0);
        results.clear();
        started = false;
        done = false;
        start = 0;

        if (rbox.isVisible()) {
            FadeTransition fo = new FadeTransition(Duration.millis(200), rbox);
            fo.setFromValue(1);
            fo.setToValue(0);
            fo.setOnFinished(e -> {
                rbox.setVisible(false);
                rbox.getChildren().clear();
            });
            fo.play();
        } else {
            rbox.setVisible(false);
            rbox.getChildren().clear();
        }
        
        info.setText(rus ? "начните печатать... (F1 - заново)" : "start typing... (F1 - restart)");
        stats.setText("");

        updisp();
        upkb();
        root.requestFocus();
    }
    
    VBox mkb() {
        VBox kb = new VBox(5);
        kb.setAlignment(Pos.CENTER);
        kb.setPadding(new Insets(20, 0, 10, 0));
        
        String[][] layout = rus ? rul : enl;
        double[] off = {0, 15, 35}; 
        
        for (int r = 0; r < layout.length; r++) {
            HBox row = new HBox(5);
            row.setAlignment(Pos.CENTER);
            row.setPadding(new Insets(0, 0, 0, off[r]));
            
            for (String k : layout[r]) {
                Label lbl = mkey(k);
                klabs.put(k.toUpperCase(), lbl);
                row.getChildren().add(lbl);
            }
            kb.getChildren().add(row);
        }
        
        // пробел
        HBox sr = new HBox();
        sr.setAlignment(Pos.CENTER);
        Label sp = new Label("");
        sp.setPrefWidth(250);
        sp.setPrefHeight(35);
        sp.setStyle(kstyle(false));
        klabs.put("SPACE", sp);
        sr.getChildren().add(sp);
        kb.getChildren().add(sr);
        
        return kb;
    }
    
    Label mkey(String k) {
        Label lbl = new Label(k);
        lbl.setFont(Font.font("Consolas", 14));
        lbl.setPrefWidth(40);
        lbl.setPrefHeight(40);
        lbl.setAlignment(Pos.CENTER);
        lbl.setStyle(kstyle(false));
        
        // тени для клавиш
        DropShadow sh = new DropShadow();
        sh.setColor(Color.web("#000000", 0.2));
        sh.setRadius(3);
        sh.setOffsetY(2);
        lbl.setEffect(sh);
        
        return lbl;
    }
    
    //цвета клавы
    String kstyle(boolean pressed) {
        if (pressed) {
            return "-fx-background-color: " + acc + ";" +
                   "-fx-text-fill: white;" +
                   "-fx-background-radius: 5;" +
                   "-fx-border-color: " + accl + ";" +
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
    
    void hl(KeyCode code, String txt, boolean pressed) {
        String k = null;
        
        if (code == KeyCode.SPACE) {
            k = "SPACE";
        } else if (txt != null && !txt.isEmpty()) {
            k = txt.toUpperCase();
        }
        
        if (k != null && klabs.containsKey(k)) {
            Label lbl = klabs.get(k);
            
            // анимка подсветки клавы
            if (pressed) {
                ScaleTransition sd = new ScaleTransition(Duration.millis(50), lbl);
                sd.setToX(0.95);
                sd.setToY(0.95);
                
                FadeTransition fi = new FadeTransition(Duration.millis(50), lbl);
                fi.setFromValue(1.0);
                fi.setToValue(1.0);
                
                lbl.setStyle(kstyle(true));
                sd.play();
            } else {
                ScaleTransition su = new ScaleTransition(Duration.millis(100), lbl);
                su.setToX(1.0);
                su.setToY(1.0);
                
                Timeline ct = new Timeline(
                    new KeyFrame(Duration.millis(150), e -> lbl.setStyle(kstyle(false)))
                );
                
                su.play();
                ct.play();
            }
        }
    }
    
    void upkb() {
        klabs.clear();
        kbox.getChildren().clear();
        
        String[][] layout = rus ? rul : enl;
        double[] off = {0, 15, 35};
        
        for (int r = 0; r < layout.length; r++) {
            HBox row = new HBox(5);
            row.setAlignment(Pos.CENTER);
            row.setPadding(new Insets(0, 0, 0, off[r]));
            
            for (String k : layout[r]) {
                Label lbl = mkey(k);
                klabs.put(k.toUpperCase(), lbl);
                row.getChildren().add(lbl);
            }
            kbox.getChildren().add(row);
        }
        
        // пробел
        HBox sr = new HBox();
        sr.setAlignment(Pos.CENTER);
        Label sp = new Label("");
        sp.setPrefWidth(250);
        sp.setPrefHeight(35);
        sp.setStyle(kstyle(false));
        klabs.put("SPACE", sp);
        sr.getChildren().add(sp);
        kbox.getChildren().add(sr);
    }
    
    VBox mprof() {
        VBox v = new VBox(25);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20));
        
        Label t = new Label(rus ? "Профиль" : "Profile");
        t.setFont(Font.font("Consolas", 42));
        t.setTextFill(Color.web(acc));
        
        DropShadow sh = new DropShadow();
        sh.setColor(Color.web(acc, 0.3));
        sh.setRadius(10);
        t.setEffect(sh);
        
        v.getChildren().add(t);
        
        return v;
    }
    
    void upprof() {
        pvw.getChildren().clear();
        
        Label t = new Label(rus ? "Профиль" : "Profile");
        t.setFont(Font.font("Consolas", 42));
        t.setTextFill(Color.web(acc));
        
        DropShadow sh = new DropShadow();
        sh.setColor(Color.web(acc, 0.3));
        sh.setRadius(10);
        t.setEffect(sh);
        
        pvw.getChildren().add(t);
        
        // рекорды
        Label rec = new Label(rus ? "🏆 Рекорды WPM" : "🏆 WPM Records");
        rec.setFont(Font.font("Consolas", 28));
        rec.setTextFill(Color.web(acc));
        rec.setPadding(new Insets(30, 0, 20, 0));
        pvw.getChildren().add(rec);
        
        // рекорды по языкам
        HBox cards = new HBox(50);
        cards.setAlignment(Pos.CENTER);
        cards.setPadding(new Insets(10, 0, 20, 0));
        
        VBox en = mcard(false);
        VBox ru = mcard(true);
        
        cards.getChildren().addAll(en, ru);
        pvw.getChildren().add(cards);
        
        // разделить
        javafx.scene.shape.Line line = new javafx.scene.shape.Line();
        line.setStartX(0);
        line.setEndX(600);
        line.setStroke(Color.web(accl));
        line.setStrokeWidth(2);
        HBox lb = new HBox(line);
        lb.setAlignment(Pos.CENTER);
        lb.setPadding(new Insets(10, 0, 10, 0));
        pvw.getChildren().add(lb);
        
        // история
        Label hist = new Label(rus ? "📜 История тестов" : "📜 Test History");
        hist.setFont(Font.font("Consolas", 28));
        hist.setTextFill(Color.web(acc));
        hist.setPadding(new Insets(20, 0, 15, 0));
        pvw.getChildren().add(hist);
        
        List<Result> recent = prof.recent(15);
        
        if (recent.isEmpty()) {
            Label no = new Label(rus ? "Результатов пока нет" : "No results yet");
            no.setFont(Font.font("Consolas", 16));
            no.setTextFill(Color.web(gray));
            no.setPadding(new Insets(20));
            pvw.getChildren().add(no);
        } else {
            VBox list = new VBox(6);
            list.setAlignment(Pos.CENTER);
            list.setMaxWidth(1100);
            list.setPadding(new Insets(5, 10, 10, 10));
            
            for (int i = 0; i < recent.size(); i++) {
                Result r = recent.get(i);
                HBox item = mhist(r, i + 1);
                list.getChildren().add(item);
            }
            
            // скролл
            javafx.scene.control.ScrollPane scroll = new javafx.scene.control.ScrollPane(list);
            scroll.setFitToWidth(true);
            scroll.setMaxHeight(450);
            scroll.setPrefHeight(450);
            scroll.setMaxWidth(1120);
            scroll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: " + bg + ";" +
                "-fx-border-color: transparent;"
            );
            scroll.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED);
            
            HBox sc = new HBox(scroll);
            sc.setAlignment(Pos.CENTER);
            
            pvw.getChildren().add(sc);
        }
    }
    
    HBox mhist(Result r, int n) {
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
        
        // нум
        Label num = new Label(String.format("%d.", n));
        num.setFont(Font.font("Consolas", 13));
        num.setTextFill(Color.web(gray));
        num.setPrefWidth(30);
        num.setAlignment(Pos.CENTER_RIGHT);
        
        // дата
        Label date = new Label(r.fdate());
        date.setFont(Font.font("Consolas", 13));
        date.setTextFill(Color.web(gray));
        date.setPrefWidth(120);
        date.setAlignment(Pos.CENTER_LEFT);
        
        // язык
        Label lang = new Label(r.lang());
        lang.setFont(Font.font("Consolas", 12));
        lang.setTextFill(Color.web(accl));
        lang.setPrefWidth(35);
        lang.setAlignment(Pos.CENTER);
        lang.setStyle(
            "-fx-background-color: rgba(7, 89, 133, 0.2);" +
            "-fx-background-radius: 4;" +
            "-fx-padding: 3 6 3 6;"
        );
        
        // впм
        Label wt = new Label("WPM:");
        wt.setFont(Font.font("Consolas", 11));
        wt.setTextFill(Color.web(gray));
        
        Label wv = new Label(String.valueOf(r.wpm()));
        wv.setFont(Font.font("Consolas", 15));
        wv.setTextFill(Color.web(green));
        wv.setPrefWidth(40);
        wv.setAlignment(Pos.CENTER_RIGHT);
        
        // точность
        Label at = new Label(rus ? "Точн.:" : "Acc:");
        at.setFont(Font.font("Consolas", 11));
        at.setTextFill(Color.web(gray));
        
        Label av = new Label(r.acc() + "%");
        av.setFont(Font.font("Consolas", 15));
        av.setTextFill(Color.web(green));
        av.setPrefWidth(45);
        av.setAlignment(Pos.CENTER_RIGHT);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label time = new Label(String.format("%.1fs", r.time()));
        time.setFont(Font.font("Consolas", 11));
        time.setTextFill(Color.web(gray));
        time.setPrefWidth(50);
        time.setAlignment(Pos.CENTER_RIGHT);
        
        Label words = new Label(String.format("%d/%d", r.correct(), r.total()));
        words.setFont(Font.font("Consolas", 11));
        words.setTextFill(Color.web(gray));
        words.setPrefWidth(50);
        words.setAlignment(Pos.CENTER_RIGHT);
        
        item.getChildren().addAll(
            num, date, lang, 
            wt, wv, 
            at, av, 
            spacer, time, words
        );
        
        return item;
    }
    
    VBox mcard(boolean russian) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20, 25, 20, 25));
        card.setStyle(
            "-fx-background-color: rgba(80, 80, 80, 0.25);" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: " + acc + ";" +
            "-fx-border-radius: 12;" +
            "-fx-border-width: 2;"
        );
        card.setPrefWidth(280);
        card.setPrefHeight(200);
        
        // язык
        Label lang = new Label(russian ? "RU" : "EN");
        lang.setFont(Font.font("Consolas", 22));
        lang.setTextFill(Color.web(accl));
        
        Result best = prof.best(russian);
        int total = prof.count(russian);
        
        Region s1 = new Region();
        VBox.setVgrow(s1, Priority.ALWAYS);
        s1.setMinHeight(3);
        
        Label wv = new Label(best != null ? String.valueOf(best.wpm()) : "-");
        wv.setFont(Font.font("Consolas", 52));
        wv.setTextFill(Color.web(acc));
        
        Label wl = new Label("WPM");
        wl.setFont(Font.font("Consolas", 14));
        wl.setTextFill(Color.web(gray));
        
        Region s2 = new Region();
        VBox.setVgrow(s2, Priority.ALWAYS);
        s2.setMinHeight(3);
        
        Label al = new Label(best != null ? 
            (rus ? "Точность: " : "Accuracy: ") + best.acc() + "%" : "-");
        al.setFont(Font.font("Consolas", 12));
        al.setTextFill(Color.web(gray));
        
        Label dl = new Label(best != null ? best.fdate() : "");
        dl.setFont(Font.font("Consolas", 10));
        dl.setTextFill(Color.web(gray));
        dl.setOpacity(0.6);
        
        Label tl = new Label((rus ? "Тестов: " : "Tests: ") + total);
        tl.setFont(Font.font("Consolas", 10));
        tl.setTextFill(Color.web(gray));
        tl.setOpacity(0.6);
        
        card.getChildren().addAll(lang, s1, wv, wl, s2, al, dl, tl);
        
        return card;
    }
    
    VBox mlearn() {
        VBox v = new VBox(30);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20));
        
        Label t = new Label("Learning");
        t.setFont(Font.font("Consolas", 42));
        t.setTextFill(Color.web(acc));
        
        DropShadow sh = new DropShadow();
        sh.setColor(Color.web(acc, 0.3));
        sh.setRadius(10);
        t.setEffect(sh);
        
        v.getChildren().add(t);
        
        // Визуализация клавиатуры с руками
        kg = new Keyboard(rus);
        
        javafx.scene.control.ScrollPane scroll = new javafx.scene.control.ScrollPane(kg);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: " + bg + ";");
        scroll.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        v.getChildren().add(scroll);
        
        return v;
    }
    
    void tlearn() {
        if (sprof) tprof();
        
        slearn = !slearn;
        
        if (slearn) {
            // обучение
            FadeTransition fo = new FadeTransition(Duration.millis(200), main);
            fo.setFromValue(1.0);
            fo.setToValue(0.0);
            fo.setOnFinished(e -> {
                center.getChildren().clear();
                center.getChildren().add(lvw);
                lvw.setVisible(true);
                
                // пересоздать клаву для языка
                lvw.getChildren().remove(1);
                kg = new Keyboard(rus);
                javafx.scene.control.ScrollPane scroll = new javafx.scene.control.ScrollPane(kg);
                scroll.setFitToWidth(true);
                scroll.setStyle("-fx-background-color: transparent; -fx-background: " + bg + ";");
                scroll.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED);
                lvw.getChildren().add(scroll);
                
                FadeTransition fi = new FadeTransition(Duration.millis(300), lvw);
                fi.setFromValue(0.0);
                fi.setToValue(1.0);
                fi.play();
            });
            fo.play();
            
            lbtn.setText("← Back");
            lbtn.setStyle(
                    "-fx-background-color: " + accl + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
            );
        } else {
            // возврат к тесту
            FadeTransition fo = new FadeTransition(Duration.millis(200), lvw);
            fo.setFromValue(1.0);
            fo.setToValue(0.0);
            fo.setOnFinished(e -> {
                center.getChildren().clear();
                center.getChildren().add(main);
                main.setVisible(true);
                
                FadeTransition fi = new FadeTransition(Duration.millis(300), main);
                fi.setFromValue(0.0);
                fi.setToValue(1.0);
                fi.play();
            });
            fo.play();
            
            lbtn.setText("📚 Learning");
            lbtn.setStyle(
                    "-fx-background-color: " + acc + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
            );
        }
    }
    
    void tprof() {
        if (slearn) tlearn();
        
        sprof = !sprof;
        
        if (sprof) {
            // профиль
            upprof();
            
            FadeTransition fo = new FadeTransition(Duration.millis(200), main);
            fo.setFromValue(1.0);
            fo.setToValue(0.0);
            fo.setOnFinished(e -> {
                center.getChildren().clear();
                center.getChildren().add(pvw);
                pvw.setVisible(true);
                
                FadeTransition fi = new FadeTransition(Duration.millis(300), pvw);
                fi.setFromValue(0.0);
                fi.setToValue(1.0);
                fi.play();
            });
            fo.play();
            
            pbtn.setText("← Back");
            pbtn.setStyle(
                    "-fx-background-color: " + accl + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
            );
        } else {
            // возврат к тесту
            FadeTransition fo = new FadeTransition(Duration.millis(200), pvw);
            fo.setFromValue(1.0);
            fo.setToValue(0.0);
            fo.setOnFinished(e -> {
                center.getChildren().clear();
                center.getChildren().add(main);
                main.setVisible(true);
                
                FadeTransition fi = new FadeTransition(Duration.millis(300), main);
                fi.setFromValue(0.0);
                fi.setToValue(1.0);
                fi.play();
            });
            fo.play();
            
            pbtn.setText("👤 Profile");
            pbtn.setStyle(
                    "-fx-background-color: " + acc + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
