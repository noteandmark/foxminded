package ua.com.foxminded.spring.integerdivision;

import java.util.Scanner;

public class IntegerDivisionApp {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Input dividend");
		int dividend = input.nextInt();
		System.out.println("Input divisor");
		int divisor = input.nextInt();

		Division result = new Division();
		DivisionResult dataresult = result.getResultOfDivision(dividend, divisor);
		Formatter formatter = new Formatter();

		System.out.println(formatter.classicFormat(dataresult));

		System.out.println("Press enter to exit");
		String end = new Scanner(System.in).nextLine();

	}

}