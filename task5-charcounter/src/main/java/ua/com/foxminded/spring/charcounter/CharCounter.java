package ua.com.foxminded.spring.charcounter;

import java.util.LinkedHashMap;

public class CharCounter implements Cache {

	@Override
	public LinkedHashMap<Character, Integer> getNumberOfUniqueCharacters(String input) {
		checkExceptions(input);
		LinkedHashMap<Character, Integer> mapOfUniqueCharacters = new LinkedHashMap<Character, Integer>();
		
		for (Character symbol : input.toCharArray()) {
			  mapOfUniqueCharacters.put(
			    symbol,
			    (mapOfUniqueCharacters.get(symbol) == null) ? 1 : mapOfUniqueCharacters.get(symbol) + 1);
			}
		
		return mapOfUniqueCharacters;		
	}

	private void checkExceptions(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Got null at the entrance");
		}
	}
}
