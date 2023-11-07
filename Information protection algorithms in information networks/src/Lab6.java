import java.io.*;

public class Lab6 {
    public static void main(String[] args) {
        // 1. Зчитування таблиці-ключа, яку ви використовували для шифрування
        char[][] keyTable = {
                {'у', 'ч', 'в', 'г', 'д', 'е', 'є', 'ж'},
                {'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м'},
                {'н', 'о', 'п', 'р', 'с', 'т', 'а', 'ф'},
                {'х', 'ц', 'б', 'ш', 'щ', 'ю', 'я', 'а'}
        };

        // 2. Зчитування криптограми з файлу, отриманої в лабораторній роботі No5
        String ciphertext = readCiphertextFromFile("playfair_ciphertextlab5.txt");

        // 3. Дешифрування криптограми
        String decryptedText = decrypt(ciphertext, keyTable);

        // 4. Виведення отриманого відкритого тексту
        System.out.println("Дешифрований текст: " + decryptedText);

        // 5. Перевірка на співпадіння з відкритим текстом про свій любимий напиток
        String favoriteDrinkText = "чаймійулюбленийнапій"; // Ваш відкритий текст
        if (decryptedText.equals(favoriteDrinkText)) {
            System.out.println("Відкритий текст співпадає з вашим улюбленим напитком.");
        } else {
            System.out.println("Відкритий текст не співпадає з вашим улюбленим напитком.");
        }
    }

    // Метод для дешифрування тексту з використанням таблиці ключа
    public static String decrypt(String ciphertext, char[][] keyTable) {
        StringBuilder decryptedText = new StringBuilder();

        // Перетворюємо текст у нижній регістр
        ciphertext = ciphertext.toLowerCase();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char firstChar = ciphertext.charAt(i);
            char secondChar = ciphertext.charAt(i + 1);
            char decryptedFirstChar = ' ';
            char decryptedSecondChar = ' ';

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
                decryptedFirstChar = keyTable[row1][(col1 + 7) % 8]; // Зворотнє зміщення на 1 позицію вліво
                decryptedSecondChar = keyTable[row2][(col2 + 7) % 8];
            } else if (col1 == col2) {
                decryptedFirstChar = keyTable[(row1 + 3) % 4][col1]; // Зворотнє зміщення на 1 позицію вгору
                decryptedSecondChar = keyTable[(row2 + 3) % 4][col2];
            } else {
                decryptedFirstChar = keyTable[row1][col2];
                decryptedSecondChar = keyTable[row2][col1];
            }

            decryptedText.append(decryptedFirstChar);
            decryptedText.append(decryptedSecondChar);
        }

        return decryptedText.toString();
    }

    // Метод для зчитування криптограми з файлу
    public static String readCiphertextFromFile(String filename) {
        StringBuilder ciphertext = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ciphertext.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ciphertext.toString();
    }
}
