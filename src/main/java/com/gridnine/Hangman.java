package com.gridnine;

public class Hangman {
    private final char[][] hangmans = new char[7][7];
    private int mistakes = 0;
    private static final int MAX_MISTAKES = 6;
    private final String word;
    private final char[] progress;

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

    public char hangMethod() {
        for (int i = 0; i < hangmans.length; i++) {
            for (int j = 0; j < hangmans[i].length; j++) {
                hangmans[i][j] = ' ';
            }
        }
        hangmans[0][1] = '+';
        hangmans[0][2] = '-';
        hangmans[0][3] = '-';
        hangmans[0][4] = '-';
        hangmans[0][5] = '+';
        for (int i = 1; i <= 5; i++) {
            hangmans[i][1] = '|';
        }
        for (int j = 0; j < 6; j++) {
            hangmans[6][j] = '_';
        }

        drawBodyParts();

        for (char[] ch : hangmans) {
            for (char c : ch) {
                System.out.print(c);
            }
            System.out.println();
        }
        return ' ';
    }

    public void drawBodyParts() {
        if (mistakes >= 1) hangmans[1][5] = 'O';  // голова
        if (mistakes >= 2) hangmans[2][5] = '|';  // тело
        if (mistakes >= 3) hangmans[2][4] = '/';  // левая рука
        if (mistakes >= 4) hangmans[2][6] = '\\'; // правая рука
        if (mistakes >= 5) hangmans[3][4] = '/';  // левая нога
        if (mistakes >= 6) hangmans[3][6] = '\\';

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
