package ua.nure.panchenko.practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

	private static final String FILE_NAME = "resources";
	private static final String ENCODING = "CP1251";

	public void output() {
		Scanner sc = new Scanner(System.in,ENCODING);
		while (sc.hasNext()) {
			try {
				String[] arr = sc.nextLine().split(" ");
				if ("stop".equalsIgnoreCase(arr[0])) {
					break;
				}
				if (arr.length != 2) {
					throw new ArrayIndexOutOfBoundsException();
				}
				Locale locale = new Locale(arr[1].toLowerCase(Locale.ENGLISH));
				ResourceBundle rb = ResourceBundle.getBundle(FILE_NAME, locale);
				System.out.println(rb.getString(arr[0]));
			} catch (MissingResourceException | ArrayIndexOutOfBoundsException e) {
				System.out.println("No such values");
				continue;
			}
		}
		sc.close();
	}

	public static void main(String[] args) {
		new Part5().output();
	}
}
