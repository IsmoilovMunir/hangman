package com.gridnine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Words {

    public static String getRandomWord(String fileName) {
        try (InputStream is = Words.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                System.out.println("Файл не найден в ресурсах: " + fileName);
                return "";
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<String> words = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    words.add(line.trim().toLowerCase());
                }
            }

            Random random = new Random();
            return words.get(random.nextInt(words.size()));

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
