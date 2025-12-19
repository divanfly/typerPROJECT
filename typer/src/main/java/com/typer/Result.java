package com.typer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// результ теста
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    
    int wpm;
    int acc;
    double time;
    int correct;
    int total;
    boolean rus;
    LocalDateTime date;
    
    public Result(int w, int a, double t, int c, int tot, boolean r) {
        wpm = w;
        acc = a;
        time = t;
        correct = c;
        total = tot;
        rus = r;
        date = LocalDateTime.now();
    }
    
    public int wpm() { return wpm; }
    public int acc() { return acc; }
    public double time() { return time; }
    public int correct() { return correct; }
    public int total() { return total; }
    public boolean rus() { return rus; }
    public LocalDateTime date() { return date; }
    
    public String fdate() {
        if (date == null) return "N/A";
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
    
    public String lang() { return rus ? "RU" : "EN"; }
}
