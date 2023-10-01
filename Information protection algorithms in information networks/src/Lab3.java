import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.text.DecimalFormat;
public class Lab3 {
    public static Map<Character, String[]> createSubstitutionTable() {
        Map<Character, String[]> substitutionTable = new HashMap<>();

        substitutionTable.put(' ', new String[]{"864", "128", "299", "626", "349", "723", "329", "215", "696", "363", "152", "579", "297", "606", "212", "409"});
        substitutionTable.put('а', new String[]{"313", "483", "256", "676", "662", "480", "137", "520", "834", "906", "444"});
        substitutionTable.put('б', new String[]{"140", "687", "473", "178", "716"});
        substitutionTable.put('в', new String[]{"848", "970", "160", "519", "619", "105", "470"});
        substitutionTable.put('г', new String[]{"446", "246", "880"});
        substitutionTable.put('д', new String[]{"760", "817", "441", "319", "846"});
        substitutionTable.put('е', new String[]{"988", "954", "336", "351", "540", "650", "320", "933", "274"});
        substitutionTable.put('ж', new String[]{"397"});
        substitutionTable.put('з', new String[]{"875", "741", "786", "869"});
        substitutionTable.put('и', new String[]{"566", "106", "176", "891", "854", "549"});
        substitutionTable.put('й', new String[]{"985", "404", "807"});
        substitutionTable.put('к', new String[]{"789", "438", "412", "981"});
        substitutionTable.put('л', new String[]{"194", "638", "507", "977", "575"});
        substitutionTable.put('м', new String[]{"983", "306", "310"});
        substitutionTable.put('н', new String[]{"663", "523", "443", "982", "347", "181", "189"});
        substitutionTable.put('о', new String[]{"872", "798", "371", "857", "472", "428", "379", "403", "568", "935"});
        substitutionTable.put('п', new String[]{"368", "195", "131", "802"});
        substitutionTable.put('р', new String[]{"979", "117", "353", "382", "562"});
        substitutionTable.put('с', new String[]{"632", "694", "207", "757"});
        substitutionTable.put('т', new String[]{"625", "286", "484", "141", "125", "678"});
        substitutionTable.put('у', new String[]{"746", "601", "338", "121"});
        substitutionTable.put('ф', new String[]{"607"});
        substitutionTable.put('х', new String[]{"683"});
        substitutionTable.put('ц', new String[]{"358"});
        substitutionTable.put('ч', new String[]{"348", "645"});
        substitutionTable.put('ш', new String[]{"435"});
        substitutionTable.put('щ', new String[]{"452"});
        substitutionTable.put('ь', new String[]{"961", "518", "972"});
        substitutionTable.put('ю', new String[]{"804"});
        substitutionTable.put('я', new String[]{"664", "738"});
        substitutionTable.put('є', new String[]{"419"});
        substitutionTable.put('і', new String[]{"357", "691", "890", "978", "586", "445", "577", "541", "877", "285"});
        substitutionTable.put('ї', new String[]{"262"});

        return substitutionTable;
    }

    public static String decrypt(String ciphertext, Map<Character, String[]> substitutionTable) {
        StringBuilder plaintext = new StringBuilder();
        Map<String, Character> reverseSubstitutionTable = new HashMap<>();

        // Створюємо зворотню таблицю замін для ефективного розшифрування
        for (Map.Entry<Character, String[]> entry : substitutionTable.entrySet()) {
            char character = entry.getKey();
            String[] codes = entry.getValue();
            for (String code : codes) {
                reverseSubstitutionTable.put(code, character);
            }
        }

        int i = 0;
        while (i < ciphertext.length()) {
            // Якщо можливо, витягнемо трицифровий код
            if (i + 2 < ciphertext.length()) {
                String code = ciphertext.substring(i, i + 3);
                i += 3;
                if (reverseSubstitutionTable.containsKey(code)) {
                    plaintext.append(reverseSubstitutionTable.get(code));
                } else {
                    plaintext.append(code);
                }
            } else if (i + 1 < ciphertext.length()) {
                // Якщо не можливо витягнути трицифровий код, витягнемо одну цифру
                String code = ciphertext.substring(i, i + 1);
                i += 1;
                if (reverseSubstitutionTable.containsKey(code)) {
                    plaintext.append(reverseSubstitutionTable.get(code));
                } else {
                    plaintext.append(code);
                }
            } else {
                // Якщо лишилася остання цифра
                String code = ciphertext.substring(i);
                if (reverseSubstitutionTable.containsKey(code)) {
                    plaintext.append(reverseSubstitutionTable.get(code));
                } else {
                    plaintext.append(code);
                }
                break;
            }
        }

        return plaintext.toString();
    }

    public static Map<Character, Integer> calculateLetterFrequency(String text) {
        Map<Character, Integer> frequency = new HashMap<>();
        text = text.toLowerCase();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }
        }

        return frequency;
    }

    public static Map<Character, Integer> calculateDigitFrequency(String text) {
        Map<Character, Integer> frequency = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }
        }

        return frequency;
    }

    public static void main(String[] args) {
        Map<Character, String[]> substitutionTable = createSubstitutionTable();

        // Шлях до файлу з зашифрованим текстом
        String filePath = "homophonic_encrypted.txt";

        try {
            // Зчитуємо вміст зашифрованого файлу
            StringBuilder fileContents = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line);
            }
            reader.close();

            // Розшифровуємо криптограму і виводимо розшифрований текст
            String decryptedText = decrypt(fileContents.toString(), substitutionTable);
            System.out.println("Розшифрований текст: " + decryptedText);

            // Обчислюємо статистику літер у вихідному тексті
            Map<Character, Integer> letterFrequency = calculateLetterFrequency(decryptedText);
            TreeMap<Character, Integer> sortedLetterFrequency = new TreeMap<>(letterFrequency);

            int totalLetters = decryptedText.length();
            System.out.println("\nСтатистика використання літер у вихідному тексті:");
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            for (Map.Entry<Character, Integer> entry : sortedLetterFrequency.entrySet()) {
                char letter = entry.getKey();
                int frequency = entry.getValue();
                double percentage = (double) frequency / totalLetters * 100;
                System.out.println(letter + ": " + frequency + " (" + decimalFormat.format(percentage) + "%)");
            }

            // Обчислюємо статистику цифр у зашифрованому тексті
            Map<Character, Integer> digitFrequency = calculateDigitFrequency(fileContents.toString());
            int totalDigits = fileContents.length();
            System.out.println("\nСтатистика використання цифр у зашифрованому тексті:");
            for (Map.Entry<Character, Integer> entry : digitFrequency.entrySet()) {
                char digit = entry.getKey();
                int frequency = entry.getValue();
                double percentage = (double) frequency / totalDigits * 100;
                System.out.println(digit + ": " + frequency + " (" + decimalFormat.format(percentage) + "%)");
            }
        } catch (IOException e) {
            System.err.println("Помилка при зчитуванні або обробці файлу: " + e.getMessage());
        }
    }
}
