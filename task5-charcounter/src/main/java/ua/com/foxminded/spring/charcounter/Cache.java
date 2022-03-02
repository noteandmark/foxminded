package ua.com.foxminded.spring.charcounter;

import java.util.LinkedHashMap;

public interface Cache {

	public LinkedHashMap<Character, Integer> getNumberOfUniqueCharacters(String input);

}
