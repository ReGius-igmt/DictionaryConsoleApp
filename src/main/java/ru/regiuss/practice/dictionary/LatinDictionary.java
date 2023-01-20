package ru.regiuss.practice.dictionary;

import java.io.File;
import java.util.regex.Pattern;

public class LatinDictionary extends ValidateDictionary {

    public LatinDictionary() {
        super(
                new File("latin.txt"),
                key -> key == null || key.length() != 4 || !Pattern.matches("[A-Za-z]+", key)
        );
    }

    @Override
    public void setValidator(WordValidator validator) {
        throw new UnsupportedOperationException();
    }
}
