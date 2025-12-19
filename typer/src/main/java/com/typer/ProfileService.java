package com.typer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileService {
    private static final String DATA_DIR = System.getProperty("user.home") + "/.typer";
    private static final String RESULTS_FILE = DATA_DIR + "/results.dat";
    
    private List<TestResult> results;
    
    public ProfileService() {
        this.results = new ArrayList<>();
        ensureDataDirectory();
        loadResults();
    }
    
    private void ensureDataDirectory() {
        try {
            Path dataPath = Paths.get(DATA_DIR);
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }
        } catch (IOException e) {
            System.err.println("Ошибка создания директории данных: " + e.getMessage());
        }
    }
    
    public void saveResult(TestResult result) {
        results.add(result);
        saveResults();
    }
    
    private void saveResults() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESULTS_FILE))) {
            oos.writeObject(results);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения результатов: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadResults() {
        File file = new File(RESULTS_FILE);
        if (!file.exists()) {
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            results = (List<TestResult>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки результатов: " + e.getMessage());
            results = new ArrayList<>();
        }
    }
    
    public List<TestResult> getAllResults() {
        return new ArrayList<>(results);
    }
    
    public TestResult getBestWpm(boolean russian) {
        return results.stream()
                .filter(r -> r.isRussian() == russian)
                .max(Comparator.comparingInt(TestResult::getWpm))
                .orElse(null);
    }
    
    public TestResult getBestAccuracy(boolean russian) {
        return results.stream()
                .filter(r -> r.isRussian() == russian)
                .max(Comparator.comparingInt(TestResult::getAccuracy))
                .orElse(null);
    }
    
    public double getAverageWpm(boolean russian) {
        return results.stream()
                .filter(r -> r.isRussian() == russian)
                .mapToInt(TestResult::getWpm)
                .average()
                .orElse(0.0);
    }
    
    public double getAverageAccuracy(boolean russian) {
        return results.stream()
                .filter(r -> r.isRussian() == russian)
                .mapToInt(TestResult::getAccuracy)
                .average()
                .orElse(0.0);
    }
    
    public List<TestResult> getRecentResults(int count) {
        return results.stream()
                .filter(r -> r.getTimestamp() != null)
                .sorted(Comparator.comparing(TestResult::getTimestamp).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }
    
    public int getTotalTests() {
        return results.size();
    }
    
    public int getTotalTests(boolean russian) {
        return (int) results.stream()
                .filter(r -> r.isRussian() == russian)
                .count();
    }
}
