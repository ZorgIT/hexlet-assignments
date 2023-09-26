package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    protected App() {
    }
    public static Map<String, Integer> getWordCount(final String sentence) {
        if (sentence.isEmpty()) {
            return new HashMap<>();
        }
        String[] words = sentence.split(" ");
        Map<String, Integer> wordsCount = new HashMap<>();
        for (String w: words
             ) {
            /*
                alternate solve:
                int wordCounter = (int) map.getOrDefault(word, 0);
                wordCounter += 1;
                wordsCount.put(w, wordCount);
             */
            var i = (wordsCount.containsKey(w)) ? wordsCount.get(w) + 1 : 1;
            wordsCount.put(w, i);
        }
        return wordsCount;
    }

    public static String toString(final Map<String, Integer> wordsCount) {
        if (wordsCount.isEmpty()) {
            return "{}";
        }
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (Map.Entry<String, Integer> e: wordsCount.entrySet()
             ) {
            String line = "  " + e.getKey() + ": " + e.getValue() + "\n";
            result.append(line);
        }
        result.append("}");

        return result.toString();
    }
}
//END
