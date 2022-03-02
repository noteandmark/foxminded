package ua.com.foxminded.spring.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Formatter {

	private static final String MARKS = "\"";
	private static final String HYPHEN = " - ";

	public StringBuilder format(LinkedHashMap<Character, Integer> resultMap) {
		StringBuilder stringResult = new StringBuilder();
		for (Map.Entry entry : resultMap.entrySet()) {
			stringResult.append(MARKS).append(entry.getKey()).append(MARKS).append(HYPHEN)
					.append(entry.getValue()).append("\n");
		}

		return stringResult;
	}

}
