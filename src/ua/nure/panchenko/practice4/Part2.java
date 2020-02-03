package ua.nure.panchenko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Scanner;

public class Part2 {

    private static final  String ENCODING = "CP1251";
    private static final  String FILE_START = "part2.txt";
    private static final  String FILE_FINISH = "part2_sorted.txt";
    private static final  int N = 10;
    private static final  int MAX = 50;

    private static SecureRandom random = new SecureRandom();

    private static String getRandomArr() {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        while (count < N) {
            sb.append(random.nextInt(MAX) + " ");
            count++;
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    private static void writeStartFile() {
        File file = new File(FILE_START);

        if (!file.exists()) {
            try (PrintWriter pr = new PrintWriter(file, ENCODING)) {
                pr.write(getRandomArr());
            } catch (FileNotFoundException e) {
                System.out.println(FILE_START + " cannot be written");
            } catch (UnsupportedEncodingException e) {
                System.out.println("Unsupported encoding");
            }
        }
    }

    private static String sortArr(String str) {
        String[] arr = str.split(" ");
        int[] arr2 = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            arr2[i] = Integer.valueOf(arr[i]);
        }

        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2.length - 1; j++) {
                if (arr2[j] > arr2[j + 1]) {
                    int temp = arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i: arr2) {
            sb.append(i + " ");
        }
        return sb.toString();
    }

    private static void writeFihishFile() {
        try (Scanner sc = new Scanner(new File(FILE_START), ENCODING);
             PrintWriter pr = new PrintWriter(new File(FILE_FINISH), ENCODING)){
            while (sc.hasNextLine()) {
                pr.write(sortArr(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_START + " or " + FILE_FINISH
                    + " cannot be written");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported encoding");
        }
    }

    private static String output() {
        String str = null;
        try (Scanner sc = new Scanner(new File(FILE_START), ENCODING);
             Scanner sc1 = new Scanner(new File(FILE_FINISH), ENCODING)) {
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append("input  ==> " + sc.nextLine() + System.lineSeparator());
            }
            while (sc1.hasNextLine()) {
                sb.append("output ==> " + sc1.nextLine());
            }
            str = sb.toString().substring(0, sb.length() - 1);

        } catch (FileNotFoundException e) {
            System.out.println(FILE_START + "  or " + FILE_FINISH
                    + " not found");
        }
        return str;
    }

    public static void main(String[] args) {

        Part2.writeStartFile();
        Part2.writeFihishFile();
        System.out.println(Part2.output());
    }
}
