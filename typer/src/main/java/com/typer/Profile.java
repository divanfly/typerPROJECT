package com.typer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// сервис профиля юзера
public class Profile {
    static final String dir = System.getProperty("user.home") + "/.typer";
    static final String file = dir + "/results.dat";
    
    List<Result> list;
    
    public Profile() {
        list = new ArrayList<>();
        checkdir();
        load();
    }
    
    void checkdir() {
        try {
            Path p = Paths.get(dir);
            if (!Files.exists(p)) Files.createDirectories(p);
        } catch (IOException e) {
            System.err.println("err dir: " + e.getMessage());
        }
    }
    
    public void add(Result r) {
        list.add(r);
        save();
    }
    
    void save() {
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file))) {
            o.writeObject(list);
        } catch (IOException e) {
            System.err.println("err save: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    void load() {
        File f = new File(file);
        if (!f.exists()) return;
        
        try (ObjectInputStream i = new ObjectInputStream(new FileInputStream(f))) {
            list = (List<Result>) i.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("err load: " + e.getMessage());
            list = new ArrayList<>();
        }
    }
    
    public List<Result> all() { return new ArrayList<>(list); }
    
    public Result best(boolean rus) {
        return list.stream()
                .filter(r -> r.rus() == rus)
                .max(Comparator.comparingInt(Result::wpm))
                .orElse(null);
    }
    
    public Result bestacc(boolean rus) {
        return list.stream()
                .filter(r -> r.rus() == rus)
                .max(Comparator.comparingInt(Result::acc))
                .orElse(null);
    }
    
    public double avgwpm(boolean rus) {
        return list.stream()
                .filter(r -> r.rus() == rus)
                .mapToInt(Result::wpm)
                .average().orElse(0.0);
    }
    
    public double avgacc(boolean rus) {
        return list.stream()
                .filter(r -> r.rus() == rus)
                .mapToInt(Result::acc)
                .average().orElse(0.0);
    }
    
    public List<Result> recent(int n) {
        return list.stream()
                .filter(r -> r.date() != null)
                .sorted(Comparator.comparing(Result::date).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
    
    public int count() { return list.size(); }
    
    public int count(boolean rus) {
        return (int) list.stream().filter(r -> r.rus() == rus).count();
    }
}
