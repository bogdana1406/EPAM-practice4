package ua.nure.panchenko.practice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String ENCODING = "CP1251";

    public static String getInput(String fileName) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(fileName));
            res = new String(bytes, ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    private static String inverseWord(String word) {
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (!Character.isLetter(arr[i])) {
                    continue;
                }
                if (Character.isUpperCase(arr[i])) {
                    arr[i] = Character.toLowerCase(arr[i]);
                } else {
                    arr[i] = Character.toUpperCase(arr[i]);
                }
            }
            return new String(arr);
    }

    public static void convertWords(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split(System.lineSeparator());
        for (String line: lines) {
            String[] words = line.split(" ");
            for (String word: words) {
                boolean isMatch = Pattern.matches("[\\P{L}]*[\\p{L}]{4,}[\\P{L}$]*", word);
                if (isMatch) {
                    word = inverseWord(word);
                } else {
                    Pattern pattern = Pattern.compile("[\\p{L}]+[\\P{L}$]*");
                    Matcher matcher = pattern.matcher(word);
                    while (matcher.find()) {
                        String partWord = matcher.group();
                        boolean isLingth = Pattern.matches("[\\p{L}]{4,}[\\P{L}$]*", partWord);
                        if (isLingth) {
                            partWord = inverseWord(partWord);
                        }
                        sb.append(partWord);
                        word = word.substring(partWord.length());
                    }
                }
                sb.append(word + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString().substring(0, sb.length() - 1));
    }

    public static void main(String[] args) {
        String input = Part1.getInput("part1.txt");
        Part1.convertWords(input);
    }
}
