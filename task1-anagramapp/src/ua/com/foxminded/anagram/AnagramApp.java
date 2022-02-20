package ua.com.foxminded.anagram;

import java.util.Scanner;
import ua.com.foxminded.anagram.reversers.TextReverser;

public class AnagramApp {

	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);		
		String source = input.nextLine();		
		TextReverser reverser = new TextReverser();				
		System.out.println(reverser.reverseText(source));
		input.close();
	}

}