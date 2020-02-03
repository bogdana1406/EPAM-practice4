package ua.nure.panchenko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {

    private static final String ENCODING = "Cp1251";
    private static final String FILE_NAME = "part4.txt";
    private static final String REGEXP = "\\p{javaUpperCase}.*?\\.";

    private Matcher matcher;

    public Part4(String str) {
        String[] lines = str.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();

        for (String line: lines) {
            sb.append(line + " ");
            sb.delete(sb.length() - 1, sb.length());
            Pattern p = Pattern.compile(REGEXP);
            setMatcher(p.matcher(sb));

        }
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public final void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public static String getString() {
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

    @Override
    public Iterator<String> iterator() {
        return new ParserIterator(getMatcher());
    }

    private static class ParserIterator implements Iterator<String> {

        private Matcher matcher;

        private boolean hasNext;

        public ParserIterator(Matcher matcher) {
            setMatcher(matcher);
        }

        public Matcher getMatcher() {
            return this.matcher;
        }

        public final void setMatcher(Matcher matcher) {
            this.matcher = matcher;
        }

        @Override
        public boolean hasNext() {
            boolean find = getMatcher().find();
            if (find) {
                hasNext = true;
            }
            return find;
        }

        @Override
        public String next() {
            if (!hasNext) {
                throw new NoSuchElementException();
            }
            hasNext = false;
            return getMatcher().group();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

        String string = getString();
        Part4 parser = new Part4(string);
        for (String str : parser) {
            System.out.println(str);
        }
    }
}
