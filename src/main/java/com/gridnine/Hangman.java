package com.gridnine;

import java.util.Arrays;

public class Hangman {
    private final char[][] hangmans = new char[7][7];
    private int mistakes = 0;
    private static final int MAX_MISTAKES = 6;
    private final String word;
    private final char[] progress;

    // головы
    private static final int HEAD_X = 1;
    private static final int HEAD_Y = 5;
    //тело
    private static final int BODY_X = 2;
    private static final int BODY_Y = 5;
    // руки
    private static final int LEFT_ARM_X = 2;
    private static final int LEFT_ARM_Y = 4;
    private static final int RIGHT_ARM_X = 2;
    private static final int RIGHT_ARM_Y = 6;

    // ноги
    private static final int LEFT_LEG_X = 3;
    private static final int LEFT_LEG_Y = 4;
    private static final int RIGHT_LEG_X = 3;
    private static final int RIGHT_LEG_Y = 6;


    public Hangman(String word) {
        this.word = word.toLowerCase();
        this.progress = new char[word.length()];
        for (int i = 0; i < progress.length; i++) {
            progress[i] = '_';
        }
    }

    public boolean guessLetter(char letter) {
        letter = Character.toLowerCase(letter);
        boolean correct = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter && progress[i] == '_') {
                progress[i] = letter;
                correct = true;
            }
        }
        if (!correct) {
            addMistake();
        }
        return correct;
    }

    public boolean isWordGuessed() {
        for (char c : progress) {
            if (c == '_') return false;
        }
        return true;
    }

    public void hangMethod() {
        clearField();
        drawBase();
        drawBodyParts();
        printField();
    }

    public void clearField() {
        for (int i = 0; i < hangmans.length; i++) {
            for (int j = 0; j < hangmans[i].length; j++) {
                hangmans[i][j] = ' ';
            }
        }
    }

    public void drawBase() {
        hangmans[0][1] = '+';
        Arrays.fill(hangmans[0], 2, 5, '-');
        hangmans[0][5] = '+';
        for (int i = 1; i <= 5; i++) {
            hangmans[i][1] = '|';
        }
        for (int j = 0; j < 6; j++) {
            hangmans[6][j] = '_';
        }
        Arrays.fill(hangmans[6], '_');
    }

    public void drawBodyParts() {
        if (mistakes >= 1) hangmans[HEAD_X][HEAD_Y] = 'O';
        if (mistakes >= 2) hangmans[BODY_X][BODY_Y] = '|';
        if (mistakes >= 3) hangmans[LEFT_ARM_X][LEFT_ARM_Y] = '/';
        if (mistakes >= 4) hangmans[RIGHT_ARM_X][RIGHT_ARM_Y] = '\\';
        if (mistakes >= 5) hangmans[LEFT_LEG_X][LEFT_LEG_Y] = '/';
        if (mistakes >= 6) hangmans[RIGHT_LEG_X][RIGHT_LEG_Y] = '\\';

    }

    public void printField() {
        for (char[] ch : hangmans) {
            for (char c : ch) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public void addMistake() {
        if (mistakes < MAX_MISTAKES) {
            mistakes++;
        }
    }

    public boolean isGameOver() {
        return mistakes >= MAX_MISTAKES;
    }

    public int getMistakes() {
        return mistakes;
    }

    public String getWord() {
        return word;
    }

    public void printProgress() {
        for (char c : progress) {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println("Ошибок: " + getMistakes() + "/" + MAX_MISTAKES);
    }
}
