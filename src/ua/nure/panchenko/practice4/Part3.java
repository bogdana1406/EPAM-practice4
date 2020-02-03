package ua.nure.panchenko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String ENCODING = "CP1251";
    private static final String FILE_NAME = "part3.txt";
    private static final String NO_VALUES = "No such values";

    private static final String REGEXP_FOR_INTEGER = "(^|\\s)(\\d+)(\\s|$)";
    private static final String REGEXP_FOR_DOUBLE = "(^|\\s)([\\d+]*\\.\\d+|\\d+\\.)(\\s|$)";
    private static final String REGEXP_FOR_CHAR = "(?i)(^|(?<=\\s))[a-zа-я]($|(?=\\s))";
    private static final String REGEXP_FOR_STRING = "[\\p{L}]{2,}";

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

    private static String integerValue() {
        StringBuilder sb = new StringBuilder();
        Pattern p1 = Pattern.compile(REGEXP_FOR_INTEGER);
        Matcher m = p1.matcher(getString());
        while (m.find()) {
            sb.append(m.group(2) + " ");
        }
        if ("".equals(sb.toString())) {
            return NO_VALUES;
        } else {
            return sb.toString().substring(0, sb.length() - 1);
        }
    }

    private static String doubleValue() {
        StringBuilder sb = new StringBuilder();
        Pattern p2 = Pattern.compile(REGEXP_FOR_DOUBLE);
        Matcher m2 = p2.matcher(getString());
        while (m2.find()) {
            sb.append(m2.group(2) + " ");
        }
        if ("".equals(sb.toString())) {
            return NO_VALUES;
        } else {
            return sb.toString().substring(0, sb.length() - 1);
        }

    }

    private static String charValue() {
        StringBuilder sb = new StringBuilder();
        Pattern p3 = Pattern.compile(REGEXP_FOR_CHAR);
        Matcher m3 = p3.matcher(getString());
        while (m3.find()) {
            sb.append(m3.group() + " ");
        }
        if ("".equals(sb.toString())) {
            return NO_VALUES;
        } else {
            return sb.toString().substring(0, sb.length() - 1);
        }

    }

    private static String stringValue() {
        StringBuilder sb = new StringBuilder();
        Pattern p4 = Pattern.compile(REGEXP_FOR_STRING);
        Matcher m4 = p4.matcher(getString());
        while (m4.find()) {
            sb.append(m4.group() + " ");
        }
        if ("".equals(sb.toString())) {
            return NO_VALUES;
        } else {
            return sb.toString().substring(0, sb.length() - 1);
        }

    }

    private static void input() {
        Scanner sc = new Scanner(System.in, ENCODING);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            switch (str) {
                case ("int"):
                    System.out.println(integerValue());
                    break;
                case ("double"):
                    System.out.println(doubleValue());
                    break;
                case ("char"):
                    System.out.println(charValue());
                    break;
                case ("String"):
                    System.out.println(stringValue());
                    break;
                case ("stop"):
                    return;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Part3.input();
    }

}
