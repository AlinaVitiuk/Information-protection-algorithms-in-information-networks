import java.io.*;

public class Lab5 {
    public static void main(String[] args) {
        // 1. Запропонувати свою таблицю-ключ для шифру Плейфера (українська абетка)
        char[][] keyTable = {
                {'у', 'ч', 'в', 'г', 'д', 'е', 'є', 'ж'},
                {'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м'},
                {'н', 'о', 'п', 'р', 'с', 'т', 'а', 'ф'},
                {'х', 'ц', 'б', 'ш', 'щ', 'ю', 'я', 'а'}
        };

        // 2. Введення відкритого тексту
        String plaintext = "чаймійулюбленийнапій";

        // 3. Шифрування тексту
        String ciphertext = encrypt(plaintext, keyTable);

        // 4. Запис відкритого тексту у файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("plaintextlab5.txt"))) {
            writer.write(plaintext);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. Запис криптограми у файл для майбутньої лабораторної роботи No6
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("playfair_ciphertextlab5.txt"))) {
            writer.write(ciphertext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для шифрування тексту з використанням таблиці ключа
    public static String encrypt(String plaintext, char[][] keyTable) {
        plaintext = plaintext.toLowerCase().replaceAll("[^а-яіїєїa-z]", "");
        if (plaintext.length() % 2 != 0) {
            plaintext += 'x';
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            char firstChar = plaintext.charAt(i);
            char secondChar = plaintext.charAt(i + 1);
            char encryptedFirstChar = ' ';
            char encryptedSecondChar = ' ';

            int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 8; col++) {
                    if (keyTable[row][col] == firstChar) {
                        row1 = row;
                        col1 = col;
                    }
                    if (keyTable[row][col] == secondChar) {
                        row2 = row;
                        col2 = col;
                    }
                }
            }

            if (row1 == row2) {
                encryptedFirstChar = keyTable[row1][(col1 + 1) % 8];
                encryptedSecondChar = keyTable[row2][(col2 + 1) % 8];
            } else if (col1 == col2) {
                encryptedFirstChar = keyTable[(row1 + 1) % 4][col1];
                encryptedSecondChar = keyTable[(row2 + 1) % 4][col2];
            } else {
                encryptedFirstChar = keyTable[row1][col2];
                encryptedSecondChar = keyTable[row2][col1];
            }

            ciphertext.append(encryptedFirstChar);
            ciphertext.append(encryptedSecondChar);
        }

        return ciphertext.toString();
    }
}
