package exercise;

import java.util.Arrays;

// BEGIN
public class ReversedSequence implements CharSequence {
    private char[] lineArray;

    public ReversedSequence(String line) {
        this.lineArray = reverse(line.toCharArray());
    }

    @Override
    public String toString() {
        return new String(lineArray);
    }

    @Override
    public int length() {
        return lineArray.length;
    }

    @Override
    public char charAt(int index) {
        return lineArray[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        char[] subArray = new char[end - start];
        int counter = 0;
        for (int i = start; i < end; i++) {
            subArray[counter++] = lineArray[i];
        }
        return new String(subArray);
    }

    public char[] reverse(char[] inputArray) {
        char[] reversedArray = new char[inputArray.length];
        int counter = 0;
        for (int i = inputArray.length - 1; i >= 0; i--) {
            reversedArray[counter++] = inputArray[i];
        }
        return reversedArray;
    }
}
// END
