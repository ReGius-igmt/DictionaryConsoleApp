package ru.regiuss.practice.dictionary;

import java.util.Scanner;

public class Main {

    private final Dictionary[] dictionaries;
    private short dictionaryIndex;
    private Scanner scanner;

    public Main() {
        this.dictionaries = new Dictionary[] {new LatinDictionary(), new DigitDictionary()};
    }

    public void run() {
        try(Scanner scanner = new Scanner(System.in)) {
            this.scanner = scanner;
            selectDictionary();
            while (dictionaryIndex < 2) {
                selectAction();
            }
        }
    }

    private void selectAction() {
        short action = readShort(
                "Выберите действие:\n1) Просмотр словаря\n2) Удаление\n3) Поиск\n4) Добавление\n5) Назад\nОтвет: ",
                (short) 5
        );
        switch (action) {
            case 1:
                System.out.println("Содержимое словаря: ");
                dictionaries[dictionaryIndex].getValues()
                        .forEach((key, value) -> System.out.printf("%s - %s%n", key, value));
                break;
            case 2:
                System.out.print("Введите ключ для удалчения: ");
                if(dictionaries[dictionaryIndex].remove(scanner.nextLine()))
                    System.out.println("Запись удалена");
                else System.out.println("Запись не существует");
                break;
            case 3:
                System.out.print("Введите ключ для поиска: ");
                String value = dictionaries[dictionaryIndex].search(scanner.nextLine());
                System.out.println("Значение: " + (value == null ? "Не найдено" : value));
                break;
            case 4:
                System.out.print("Введите ключ и значение через пробел: ");
                String[] d = scanner.nextLine().split(" ", 2);
                if(d.length < 2) {
                    System.out.println("Значение не указано");
                    break;
                }
                try {
                    dictionaries[dictionaryIndex].add(d[0], d[1]);
                    System.out.println("Запись успешно добавлена");
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка, ключ не соответсвует спецификации словаря");
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
                break;
            case 5:
                selectDictionary();
                break;
        }
    }

    private void selectDictionary() {
        dictionaryIndex = readShort(
                "Выберите словарь:\n1) Латинский (4 символа латинской раскладки)\n2) Цифровой (5 символов только цифры)\n3) Выход\nОтвет: ",
                (short) 3
        );
        dictionaryIndex--;
    }

    private short readShort(String text, short max) {
        short value = 0;
        do {
            System.out.print(text);
            try {
                value = Short.parseShort(scanner.nextLine());
            } catch (Exception ignored) {}
        } while (value < 1 || value > max);
        return value;
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
