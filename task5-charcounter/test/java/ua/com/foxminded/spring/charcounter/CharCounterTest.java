package ua.com.foxminded.spring.charcounter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharCounterTest {

	private CharCounter charCounter;

	@Test
	void getResult_whenGetWords_thenReturnResult() {
		charCounter = new CharCounter();
		LinkedHashMap<Character, Integer> expected = new LinkedHashMap<Character, Integer>();

		expected.put('h', 1);
		expected.put('e', 1);
		expected.put('l', 3);
		expected.put('o', 2);
		expected.put(' ', 1);
		expected.put('w', 1);
		expected.put('r', 1);
		expected.put('d', 1);
		expected.put('!', 1);
		LinkedHashMap<Character, Integer> actual = charCounter.getNumberOfUniqueCharacters("hello world!");
		assertEquals(expected, actual);
	}

	@Test
	void getResult_whenGetEmptyString_thenReturnEmptyString() {
		LinkedHashMap<Character, Integer> expected = new LinkedHashMap<Character, Integer>();
		LinkedHashMap<Character, Integer> actual = charCounter.getNumberOfUniqueCharacters("");
		assertEquals(expected, actual);
	}

	@Test
	void getResult_whenInputIsNull_thenThrowIllegalArgumentException() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> charCounter.getNumberOfUniqueCharacters(null));
		assertEquals("Got null at the entrance", exception.getMessage());
	}

}
