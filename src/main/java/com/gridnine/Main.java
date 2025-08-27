package com.gridnine;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=================");
            System.out.println(" 1. ▶ Старт игра");
            System.out.println(" 2. ❌ Завершить");
            System.out.print("Ваш выбор: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                startGame(scanner);
            } else if (choice.equals("2")) {
                System.out.println("Выход из программы...");
                break;
            } else {
                System.out.println("Неверный выбор, попробуй снова.");
            }
        }
    }

    private static void startGame(Scanner scanner) {

        try {
            String word = Words.getRandomWord("words.txt");

            Hangman game = new Hangman(word);

            while (!game.isGameOver() && !game.isWordGuessed()) {
                game.hangMethod();
                game.printProgress();
                System.out.println("Введите буквы");
                String input = scanner.nextLine();

                if (input.length() != 1) {
                    boolean hasLetter = false;
                    for (char ch : input.toCharArray()) {
                        if (Character.isLetter(ch)) {
                            hasLetter = true;
                            break;
                        }
                    }
                    if (!hasLetter) {
                        System.out.println("Добавь только буквы!");
                    } else {
                        System.out.println("Введите только одну букву!");
                    }
                    continue;
                }
                char ch = input.charAt(0);
                if (!Character.isLetter(ch)) {
                    System.out.println("Добавь только буквы!");
                    continue;
                }
                char letter = input.charAt(0);
                if (game.guessLetter(letter)) {
                    System.out.println("Правильно!");
                } else {
                    System.out.println("Неправильно ");
                }
            }

            game.hangMethod();
            if (game.isWordGuessed()) {
                System.out.println("Поздравляем! Вы угадали слово: " + game.getWord());
            } else {
                System.out.println("Игра окончена! Было загадано слово: " + game.getWord());
            }
        } catch (IOException e) {
            System.out.println("Ошибка" + e.getMessage());
        }
    }
}
