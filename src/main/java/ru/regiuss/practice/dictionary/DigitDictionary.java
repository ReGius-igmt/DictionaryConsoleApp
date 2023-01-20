package ru.regiuss.practice.dictionary;

import java.io.File;
import java.util.regex.Pattern;

public class DigitDictionary extends ValidateDictionary {

    public DigitDictionary() {
        super(
                new File("digit.txt"),
                key -> key == null || key.length() != 5 || !Pattern.matches("[0-9]+", key)
        );
    }

    @Override
    public void setValidator(WordValidator validator) {
        throw new UnsupportedOperationException();
    }
}
