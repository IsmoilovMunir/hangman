package com.gridnine;

import com.gridnine.excaption.EmptyWordListException;
import com.gridnine.excaption.WordFileNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Words {
    private static final Random RANDOM = new Random();

    public static String getRandomWord(String fileName) throws RuntimeException {
        InputStream is = Words.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new WordFileNotFoundException("Файл не найден в ресурсах: " + fileName + ". Проверьте путь к файлу!");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<String> words = new ArrayList<>();
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!line.trim().isEmpty()) {
                words.add(line.trim().toLowerCase());
            }
        }

        if (words.isEmpty()) {
            throw new EmptyWordListException("Файл пуст или не содержит слов: " + fileName);
        }
        return words.get(RANDOM.nextInt(words.size()));
    }
}
