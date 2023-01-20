package ru.regiuss.practice.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ValidateDictionary implements Dictionary {

    private Map<String, String> values;
    private WordValidator validator;

    public ValidateDictionary(WordValidator validator) {
        this.values = new HashMap<>();
        this.validator = validator;
    }

    public ValidateDictionary(File file, WordValidator validator) {
        this.validator = validator;
        try {
            read(file);
        } catch (Exception e) {
            System.out.printf("Не удалось считать файл [%s] - %s", file.getName(), e.getMessage());
            values = new HashMap<>();
        }
    }

    @Override
    public void read(File file) throws IOException {
        int count = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) count++;
        }
        if(count < 1) return;
        values = new HashMap<>(count << 1);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for(String line; (line = reader.readLine()) != null; ) {
                String[] d = line.split(" ", 2);
                if(d.length < 2 || validator.isInvalid(d[0])) continue;
                values.put(d[0], d[1]);
            }
        }
    }

    @Override
    public boolean remove(String key) {
        return values.remove(key) != null;
    }

    @Override
    public String search(String key) {
        return values.get(key);
    }

    @Override
    public void add(String key, String value) {
        if(validator.isInvalid(key)) throw new IllegalArgumentException("invalid key - " + key);
        values.put(key, value);
    }

    public WordValidator getValidator() {
        return validator;
    }

    public void setValidator(WordValidator validator) {
        this.validator = validator;
    }

    @Override
    public Map<String, String> getValues() {
        return values;
    }
}
