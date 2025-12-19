package com.typer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int wpm;
    private int accuracy;
    private double timeSeconds;
    private int correctWords;
    private int totalWords;
    private boolean isRussian;
    private LocalDateTime timestamp;
    
    public TestResult(int wpm, int accuracy, double timeSeconds, int correctWords, int totalWords, boolean isRussian) {
        this.wpm = wpm;
        this.accuracy = accuracy;
        this.timeSeconds = timeSeconds;
        this.correctWords = correctWords;
        this.totalWords = totalWords;
        this.isRussian = isRussian;
        this.timestamp = LocalDateTime.now();
    }
    
    public int getWpm() {
        return wpm;
    }
    
    public int getAccuracy() {
        return accuracy;
    }
    
    public double getTimeSeconds() {
        return timeSeconds;
    }
    
    public int getCorrectWords() {
        return correctWords;
    }
    
    public int getTotalWords() {
        return totalWords;
    }
    
    public boolean isRussian() {
        return isRussian;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getFormattedDate() {
        if (timestamp == null) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return timestamp.format(formatter);
    }
    
    public String getLanguage() {
        return isRussian ? "RU" : "EN";
    }
}
