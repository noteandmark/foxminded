package ua.com.foxminded.spring.charcounter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

class CharCounterProxyTest {

	CharCounterProxy charCounterProxy = new CharCounterProxy();

	Cache charCounter = mock(CharCounter.class);

	@Test
	void getResult_whenTwoSameStrings_thenReturnFromCache() {

		charCounterProxy.setCharCounter(charCounter);

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

		charCounterProxy.getNumberOfUniqueCharacters("hello world!");
		charCounterProxy.getNumberOfUniqueCharacters("new string");
		charCounterProxy.getNumberOfUniqueCharacters("hello world!");

		when(charCounter.getNumberOfUniqueCharacters("hello world!")).thenReturn(expected);

		verify(charCounter, times(1)).getNumberOfUniqueCharacters("hello world!");

	}
}