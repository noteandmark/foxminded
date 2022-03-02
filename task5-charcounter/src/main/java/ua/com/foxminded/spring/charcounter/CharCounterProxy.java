package ua.com.foxminded.spring.charcounter;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CharCounterProxy implements Cache {

	private Cache charCounter = new CharCounter();

	public void setCharCounter(Cache charCounter) {
		this.charCounter = charCounter;
	}

	private HashMap<String, LinkedHashMap<Character, Integer>> stringCache = new HashMap<String, LinkedHashMap<Character, Integer>>();

	@Override
	public LinkedHashMap<Character, Integer> getNumberOfUniqueCharacters(String input) {
		
		if (!stringCache.containsKey(input)) {
			stringCache.put(input, charCounter.getNumberOfUniqueCharacters(input));
		}
		return stringCache.get(input);
				
	}

}
