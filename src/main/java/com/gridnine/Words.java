package com.gridnine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Words {

    public static String getRandomWord(String fileName) throws IOException {
        try (InputStream is = Words.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new IOException("Файл не найден в ресурсах: " + fileName + ". Проверьте путь к файлу!");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<String> words = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    words.add(line.trim().toLowerCase());
                }
            }

            if (words.isEmpty()) {
                throw new IOException("Файл пуст или не содержит слов: " + fileName);
            }
            return words.get(ThreadLocalRandom.current().nextInt(words.size()));
        }
    }
}
