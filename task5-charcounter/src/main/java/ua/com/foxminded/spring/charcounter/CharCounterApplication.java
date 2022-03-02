package ua.com.foxminded.spring.charcounter;

import java.util.Scanner;

public class CharCounterApplication {

	public static void main(String[] args) {
		Cache charCounter = new CharCounterProxy();
		Formatter formatter = new Formatter();
		Scanner inputConsole = new Scanner(System.in);
		String input;
		String output;
		String exit = "exit";
		
		System.out.println("Input strings. To exit - type [exit]");
		while (true) {
			input = inputConsole.nextLine();
			if (input.equals(exit)) {
				break;
			}
			output = formatter.format(charCounter.getNumberOfUniqueCharacters(input)).toString();
			System.out.println(output);
			System.out.println("Input next string");
		}
		inputConsole.close();
	}
}
