package ua.com.foxminded.anagram.reversers;

import java.util.StringJoiner;

public class TextReverser {

	public String reverseText(String source) {

		if (source == null) {
			throw new IllegalArgumentException("Got null at the entrance");
		}
		String[] words = source.split(" ");
		StringJoiner text = new StringJoiner(" ");
		for (String word : words) {
			text.add(this.reverseWord(word));
		}
		return text.toString();
	}

	private String reverseWord(String word) {
		char[] reverseArray = word.toCharArray();
		int start = 0;
		int end = reverseArray.length - 1;
		char temp;
		while (start < end) {
			if (!Character.isLetter(reverseArray[start])) {
				start++;
				continue;
			}
			if (!Character.isLetter(reverseArray[end])) {
				end--;
				continue;
			}
			temp = reverseArray[start];
			reverseArray[start] = reverseArray[end];
			reverseArray[end] = temp;
			start++;
			end--;
		}
		return new String(reverseArray);
	}
}
