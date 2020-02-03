package ua.nure.panchenko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String ENCODING = "CP1251";
    private static final String FILE_NAME = "part6.txt";

    private static final String REGEXP_FOR_LATIN = "\\p{Alpha}+\\p{L}*";
    private static final String REGEXP_FOR_CYRILLIC = "\\p{InCyrillic}+\\p{L}*";

    private static String getString() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(FILE_NAME), ENCODING);
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine() + System.lineSeparator());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NAME + " not found");
        }
        return sb.toString();
    }

    private static String latinValue() {
        StringBuilder sb = new StringBuilder();
        Pattern p1 = Pattern.compile(REGEXP_FOR_LATIN);
        Matcher m = p1.matcher(getString());
        while (m.find()) {
            sb.append(m.group() + " ");
        }
        if ("".equals(sb.toString())) {
            return sb.toString();
        } else {
            return sb.toString().substring(0, sb.length() - 1);
        }
    }

    private static String cyrillicValue() {
        StringBuilder sb = new StringBuilder();
        Pattern p2 = Pattern.compile(REGEXP_FOR_CYRILLIC);
        Matcher m2 = p2.matcher(getString());
        while (m2.find()) {
            sb.append(m2.group() + " ");
        }
        if ("".equals(sb.toString())) {
            return sb.toString();
        } else {
            return sb.toString().substring(0, sb.length() - 1);
        }

    }

    private static void input() {
        Scanner sc = new Scanner(System.in, ENCODING);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            if ("Latn".equals(str) || "latn".equals(str)) {
                System.out.println(str + ": " + latinValue());
            } else if ("Cyrl".equals(str) || "cyrl".equals(str)) {
                System.out.println(str + ": " + cyrillicValue());
            } else if ("stop".equals(str) || "Stop".equals(str)) {
                return;
            } else {
                System.out.println(str + ": " + "Incorrect input");
            }
        }
    }

    public static void main(String[] args) {
        Part6.input();
    }

}
