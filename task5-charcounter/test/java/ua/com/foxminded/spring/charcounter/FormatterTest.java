package ua.com.foxminded.spring.charcounter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

class FormatterTest {

	private Formatter formatter = new Formatter();
	private static final String MARKS = "\"";
	private static final String HYPHEN = " - ";

	@Test
	void format_whenString_thenResult() {
		StringBuilder expected = new StringBuilder();
		StringBuilder actual = new StringBuilder();
		LinkedHashMap<Character, Integer> resultMap = new LinkedHashMap<Character, Integer>();
		resultMap.put('h', 1);
		resultMap.put('e', 1);
		resultMap.put('l', 3);
		resultMap.put('o', 2);
		resultMap.put(' ', 1);
		resultMap.put('w', 1);
		resultMap.put('r', 1);
		resultMap.put('d', 1);
		resultMap.put('!', 1);
				
		resultMap.forEach((key,value) -> expected.append(MARKS + key + MARKS + HYPHEN + value + "\n"));

		actual = formatter.format(resultMap);
		assertEquals(expected.toString(), actual.toString());
	}

}
