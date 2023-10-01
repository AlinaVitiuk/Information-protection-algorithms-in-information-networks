import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Lab2 {
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

    public static String encrypt(String plaintext, Map<Character, String[]> substitutionTable) {
        StringBuilder ciphertext = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            c = Character.toLowerCase(c);  // Перетворюємо у нижній регістр
            if (substitutionTable.containsKey(c)) {
                String[] codes = substitutionTable.get(c);
                int randomIndex = new Random().nextInt(codes.length);
                String randomCode = codes[randomIndex];
                ciphertext.append(randomCode);
            } else {
                ciphertext.append(c);
            }
        }

        return ciphertext.toString();
    }

    public static void main(String[] args) {
        Map<Character, String[]> substitutionTable = createSubstitutionTable();

        // Ваш відкритий текст
        String plaintext = "Житомир місто в Україні адміністративний центр Житомирської області Місто розташоване на північному заході України і має багатий історичний спадок Житомир відомий своєю культурною спадщиною архітектурними памятками і мальовничими пейзажами Житомир також є важливим економічним і транспортним центром регіону";

        // Зашифруємо текст
        String ciphertext = encrypt(plaintext, substitutionTable);

        // Запишемо зашифрований текст у файл
        String outputFileName = "homophonic_encrypted.txt";
        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write(ciphertext);
            System.out.println("Зашифрований текст записано у файл " + outputFileName);
        } catch (IOException e) {
            System.err.println("Помилка при записі у файл: " + e.getMessage());
        }
    }
}
