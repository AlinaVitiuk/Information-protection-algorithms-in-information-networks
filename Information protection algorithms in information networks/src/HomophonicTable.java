import java.io.*;
import java.util.*;
public class HomophonicTable {
    public static void main(String[] args) {
        Map<Character, Integer> letterFrequency = new HashMap<>();

        // Встановлюємо частоту використання літер та пробіла
        letterFrequency.put('а', 11);
        letterFrequency.put('б', 5);
        letterFrequency.put('в', 7);
        letterFrequency.put('г', 3);
        letterFrequency.put('д', 5);
        letterFrequency.put('е', 9);
        letterFrequency.put('є', 1);
        letterFrequency.put('ж', 1);
        letterFrequency.put('з', 4);
        letterFrequency.put('и', 6);
        letterFrequency.put('і', 10);
        letterFrequency.put('ї', 1);
        letterFrequency.put('й', 3);
        letterFrequency.put('к', 4);
        letterFrequency.put('л', 5);
        letterFrequency.put('м', 3);
        letterFrequency.put('н', 7);
        letterFrequency.put('о', 10);
        letterFrequency.put('п', 4);
        letterFrequency.put('р', 5);
        letterFrequency.put('с', 4);
        letterFrequency.put('т', 6);
        letterFrequency.put('у', 4);
        letterFrequency.put('ф', 1);
        letterFrequency.put('х', 1);
        letterFrequency.put('ц', 1);
        letterFrequency.put('ч', 2);
        letterFrequency.put('ш', 1);
        letterFrequency.put('щ', 1);
        letterFrequency.put('ь', 3);
        letterFrequency.put('ю', 1);
        letterFrequency.put('я', 2);
        letterFrequency.put(' ', 16);

        // Створюємо список літер та сортуємо його
        List<Character> sortedLetters = new ArrayList<>(letterFrequency.keySet());
        Collections.sort(sortedLetters);

        Map<Character, List<String>> letterToNumbers = new HashMap<>();

        // Ініціалізуємо список для кожної літери
        for (char letter : sortedLetters) {
            letterToNumbers.put(letter, new ArrayList<>());
        }

        Random random = new Random();

        // Генеруємо всі можливі трицифрові числа від 100 до 999 та перемішуємо їх
        List<Integer> allPossibleNumbers = new ArrayList<>();
        for (int i = 100; i <= 999; i++) {
            allPossibleNumbers.add(i);
        }
        Collections.shuffle(allPossibleNumbers);

        for (char letter : sortedLetters) {
            int frequency = letterFrequency.get(letter);
            List<String> numbers = letterToNumbers.get(letter);

            for (int i = 0; i < frequency; i++) {
                if (allPossibleNumbers.isEmpty()) {
                    // Якщо всі можливі числа вже використані, ви можете обробити цей випадок або
                    // обрати іншу логіку, наприклад, збільшити діапазон чисел.
                    System.out.println("Не вистачає унікальних чисел для літери " + letter);
                    break;
                }

                int randomNumber = allPossibleNumbers.remove(0);
                String formattedNumber = String.format("%03d", randomNumber); // Змінено форматування на трицифрове
                numbers.add(formattedNumber);
            }
        }

        // Виводимо результат у алфавітному порядку
        for (char letter : sortedLetters) {
            System.out.print("substitutionTable.put('" + Character.toLowerCase(letter) + "', new String[]{");
            List<String> numbers = letterToNumbers.get(letter);
            for (int i = 0; i < numbers.size(); i++) {
                System.out.print("\"" + numbers.get(i) + "\"");
                if (i < numbers.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("});");
        }
    }
}
