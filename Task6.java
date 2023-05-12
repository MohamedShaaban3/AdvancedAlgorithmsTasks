import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpellChecker {
    private Set<String> lexicon;
    private Map<String, List<String>> suggestions;

    public SpellChecker(Set<String> words) {
        lexicon = new HashSet<>(words);
        suggestions = new HashMap<>();
    }

    public List<String> check(String word) {
        if (lexicon.contains(word)) {
            return Collections.singletonList(word);
        }

        List<String> candidates = new ArrayList<>();

        // Delete one character at a time
        for (int i = 0; i < word.length(); i++) {
            String candidate = word.substring(0, i) + word.substring(i+1);
            if (lexicon.contains(candidate)) {
                candidates.add(candidate);
            }
        }

        // insert alphabet between substrings
        for (int i = 0; i <= word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String candidate = word.substring(0, i) + c + word.substring(i);
                if (lexicon.contains(candidate)) {
                    candidates.add(candidate);
                }
            }
        }

        // replace characters
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String candidate = word.substring(0, i) + c + word.substring(i+1);
                if (lexicon.contains(candidate)) {
                    candidates.add(candidate);
                }
            }
        }

        // switching characters
        for (int i = 0; i < word.length()-1; i++) {
            String candidate = word.substring(0, i) + word.charAt(i+1) + word.charAt(i) + word.substring(i+2);
            if (lexicon.contains(candidate)) {
                candidates.add(candidate);
            }
        }

        suggestions.put(word, candidates);
        return candidates;
    }

    public Map<String, List<String>> getSuggestions() {
        return suggestions;
    }
    public static void main(String[] args) {
    Set<String> lexicon = new HashSet<String>();
    lexicon.add("apple");
    lexicon.add("banana");
    lexicon.add("cherry");
    lexicon.add("date");

    SpellChecker spellChecker = new SpellChecker(lexicon);

    String word1 = "apple";
    String word2 = "aple";
    String word3 = "bannana";
    String word4 = "datee";
    String word5 = "chery";

    List<String> suggestions1 = spellChecker.check(word1);
    System.out.println("Suggestions for " + word1 + ": " + suggestions1);

    List<String> suggestions2 = spellChecker.check(word2);
    System.out.println("Suggestions for " + word2 + ": " + suggestions2);

    List<String> suggestions3 = spellChecker.check(word3);
    System.out.println("Suggestions for " + word3 + ": " + suggestions3);

    List<String> suggestions4 = spellChecker.check(word4);
    System.out.println("Suggestions for " + word4 + ": " + suggestions4);

    List<String> suggestions5 = spellChecker.check(word5);
    System.out.println("Suggestions for " + word5 + ": " + suggestions5);
}
}


