package ru.regiuss.practice.dictionary;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface Dictionary {
    void read(File file) throws IOException;
    boolean remove(String key);
    String search(String key);
    void add(String key, String value);
    Map<String, String> getValues();
}
