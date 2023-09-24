package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    protected  App() {
    }
    public static boolean scrabble(final String symbols, final String word) {
        List<Character> symbolsChars = toCharList(symbols);
        List<Character> wordChars = toCharList(word);
        for (char curChar: wordChars
             ) {
            if (symbolsChars.contains(curChar)) {
                symbolsChars.remove(symbolsChars.indexOf(curChar));
            } else {
                return false;
            }
        }
        return true;
    }

    public static List<Character> toCharList(final String symbols) {
        List<Character> chars = new ArrayList<Character>();
        for (char c : symbols.toLowerCase().toCharArray()) {
            chars.add(c);
        }
        return  chars;
    }
}
//END
