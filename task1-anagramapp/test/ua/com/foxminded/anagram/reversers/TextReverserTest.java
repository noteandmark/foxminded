package ua.com.foxminded.anagram.reversers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextReverserTest {

	private TextReverser reverser = new TextReverser();
	
	@Test
	void testOneWordLine() {
		assertEquals("dcba", reverser.reverseText("abcd"));
	}
	
	@Test
	void testTwoWordsLine() {
		assertEquals("dcba hgfe", reverser.reverseText("abcd efgh"));
	}
	
	@Test
	void testTwoWordsLineWithNonLetterSymbol() {
		assertEquals("d1cba hgf!e", reverser.reverseText("a1bcd efg!h"));
	}	
	
	@Test
	void testEmptyString() {
		assertEquals("", reverser.reverseText(""));
	}	
	
	@Test
	void testLineWithOneSymbol() {
		assertEquals("a", reverser.reverseText("a"));
	}	
	
	@Test
	void testTwoWordsLineWithOneSymbol() {
		assertEquals("a b", reverser.reverseText("a b"));
	}	
	
	@Test
	void testOnlyNumbers() {
		assertEquals("0123456789", reverser.reverseText("0123456789"));
	}	
	
	@Test
	void testLineWithSeveralWhiteSpaces() {
		assertEquals("", reverser.reverseText("   "));
	}	
	
	@Test
	void testWordOfCharacters() {
		assertEquals("!\"¹;%:?*()-+`[]{}\\|/,.<>", reverser.reverseText("!\"¹;%:?*()-+`[]{}\\|/,.<>"));
	}	
	
	@Test
	void testNull() {	
		assertThrows(IllegalArgumentException.class, () -> reverser.reverseText(null));
	}	
	
	
	
}
