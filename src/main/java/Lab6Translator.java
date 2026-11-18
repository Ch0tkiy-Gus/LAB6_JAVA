import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

class Translator {
    private HashMap<String, String> dictionary;

    public Translator() {
        dictionary = new HashMap<>();
    }

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
    }

    public String translate(String phrase) {
        String[] words = phrase.split(" ");
        String result = "";

        for (String word : words) {
            String cleanWord = word.toLowerCase();
            if (dictionary.containsKey(cleanWord)) {
                result += dictionary.get(cleanWord) + " ";
            } else {
                result += word + " "; 
            }
        }
        return result.trim();
    }
}

public class Lab6Translator {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        Translator translator = new Translator();

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("this", "це");
        translator.addWord("is", "є");
        translator.addWord("simple", "просте");
        translator.addWord("task", "завдання");
        translator.addWord("student", "студент");

        System.out.println("Базовий словник завантажено.");

        while (true) {
            System.out.print("\nДодати нове слово до словника? (1 - так, 0 - ні): ");
            String choice = scanner.nextLine();
            
            if (choice.equals("0")) {
                break;
            } else if (choice.equals("1")) {
                System.out.print("Введіть слово англійською: ");
                String eng = scanner.nextLine();
                System.out.print("Введіть переклад українською: ");
                String ukr = scanner.nextLine();
                translator.addWord(eng, ukr);
                System.out.println("Слово успішно додано!");
            } else {
                System.out.println("Невірний вибір.");
            }
        }

        System.out.print("\nВведіть фразу англійською для перекладу: ");
        String phrase = scanner.nextLine();

        String translatedPhrase = translator.translate(phrase);
        System.out.println("Переклад: " + translatedPhrase);
    }
}