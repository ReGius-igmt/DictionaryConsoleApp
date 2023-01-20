package ru.regiuss.practice.dictionary;

@FunctionalInterface
public interface WordValidator {
    boolean isInvalid(String word);
}
