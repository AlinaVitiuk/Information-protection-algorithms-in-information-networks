import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Lab1 {
    public static void main(String[] args) {
        String alphabet_ua = "АБВГДЕЄЖЗИІЇКЛМНОПРСТУФХЦЧШЩЬЮЯабвгдеєжзиіїклмнопрстуфхцчшщьюя";
        String alphabet_eng = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        int shift = 13; // Кількість позицій для зсуву

        String plaintext_ua = "ВулицяБогданаХмельницькогоуЛьвовірозташованавцентрімістаїмаєбагатоважливихісторичнихбудівельнавчальнихзакладівтацікавихмагазинівтаварівщокожензможензнайтисобіщосьцікаве";
        String plaintext_eng = "Bogdana Khmelnitskoho Street, located in the heart of Lviv, Ukraine, is a picturesque and historically rich thoroughfare that beautifully encapsulates the essence of this enchanting city. Steeped in history and adorned with architectural marvels, this street is a must-visit for anyone exploring Lviv.";

        //створення вхідних файлів, якщо їх не знайдено
        try {
            // Створюємо файл "input_ua" і записуємо український текст
            FileWriter uaFileWriter = new FileWriter("input_ua.txt");
            writeToFile("input_ua.txt", plaintext_ua);

            // Створюємо файл "input_eng" і записуємо англійський текст
            FileWriter engFileWriter = new FileWriter("input_eng.txt");
            writeToFile("input_eng.txt", plaintext_eng);

        } catch (IOException e) {
            System.out.println("Помилка при створенні або записі файлів: " + e.getMessage());
        }

        //Український алфавіт
        System.out.println("УКРАЇНСЬКИЙ АЛФАВІТ: ");
        System.out.println("Текст для шифрування: " + plaintext_ua);

        String encryptedText_ua = encrypt(plaintext_ua, alphabet_ua, shift);
        String decryptedText_ua = decrypt(encryptedText_ua, alphabet_ua, shift);

        writeToFile("encrypted_ua.txt", encryptedText_ua);
        writeToFile("decrypted_ua.txt", decryptedText_ua);

        System.out.println("Зашифрований текст: " + encryptedText_ua);
        System.out.println("Розшифрований текст: " + decryptedText_ua);
        System.out.println(" ");

        //Англійський алфавіт
        System.out.println("АНГЛІЙСЬКИЙ АЛФАВІТ: ");
        System.out.println("Текст для шифрування: " + plaintext_eng);

        String encryptedText_eng = encrypt(plaintext_eng, alphabet_eng, shift);
        String decryptedText_eng = decrypt(encryptedText_eng, alphabet_eng, shift);

        writeToFile("encrypted_eng.txt", encryptedText_eng);
        writeToFile("decrypted_eng.txt", decryptedText_eng);

        System.out.println("Зашифрований текст: " + encryptedText_eng);
        System.out.println("Розшифрований текст: " + decryptedText_eng);
    }

    public static String readFromFile(String filePath) {
        StringBuilder text = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні з файлу '" + filePath + "': " + e.getMessage());
        }

        return text.toString();
    }

    public static void writeToFile(String filePath, String text) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл '" + filePath + "': " + e.getMessage());
        }
    }
    public static String encrypt(String text, String alphabet, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            int index = alphabet.indexOf(c);
            if (index != -1) {
                int newIndex = (index + shift) % alphabet.length();
                char newChar = alphabet.charAt(newIndex);
                encryptedText.append(newChar);
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, String alphabet, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (char c : encryptedText.toCharArray()) {
            int index = alphabet.indexOf(c);
            if (index != -1) {
                int newIndex = (index - shift + alphabet.length()) % alphabet.length();
                char newChar = alphabet.charAt(newIndex);
                decryptedText.append(newChar);
            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }
}
