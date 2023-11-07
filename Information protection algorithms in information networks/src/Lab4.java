import java.io.*;
import java.util.*;

public class Lab4 {
    public static void main(String[] args) {
        // 1. Визначення алфавіту (українська абетка)
        String alphabet = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя";
        int N = alphabet.length();

        // 2. Визначення номерів літер
        Map<Character, Integer> letterToNumber = new HashMap<>();
        for (int i = 0; i < N; i++) {
            letterToNumber.put(alphabet.charAt(i), i);
        }

        // 3. Введення парольної фрази (хобі)
        String password = "робитифото";

        // 4. Формування гами
        List<Integer> gamma = new ArrayList<>();
        for (char letter : password.toCharArray()) {
            gamma.add(letterToNumber.get(letter));
        }

        // Зчитування відкритого тексту з файлу
        StringBuilder plaintextBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("plaintextlab4.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                plaintextBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String plaintext = plaintextBuilder.toString();

        // 5. Перетворення в нижній регістр і видалення розділових знаків і пробілів
        plaintext = plaintext.toLowerCase().replaceAll("[^а-яіїєїa-z]", "");

        // 6. Шифрування
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            int gammaChar = gamma.get(i % gamma.size()); // "продовження" гами
            int encryptedCharIndex = (letterToNumber.get(plainChar) + gammaChar) % N;
            char encryptedChar = alphabet.charAt(encryptedCharIndex);
            ciphertext.append(encryptedChar);
        }

        // 7. Запис криптограми у файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("encryptedlab4.txt"))) {
            writer.write(ciphertext.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Зашифрований текст: " + ciphertext.toString());

        // 8. Дешифрування
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char encryptedChar = ciphertext.charAt(i);
            int gammaChar = gamma.get(i % gamma.size()); // "продовження" гами
            int decryptedCharIndex = (letterToNumber.get(encryptedChar) - gammaChar + N) % N;
            char decryptedChar = alphabet.charAt(decryptedCharIndex);
            decryptedText.append(decryptedChar);
        }

        // 9. Запис розшифрованого тексту у файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("decryptedlab4.txt"))) {
            writer.write(decryptedText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Розшифрований текст: " + decryptedText.toString());
    }
}
