package com.foxminded.andreimarkov.formula1.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class RacerDAOImpl implements RacerDAO {

	@Override
	public List<String> readLinesFromFile(String fileName) {
		List<String> lines = new ArrayList<>();
		if (fileName == null) {
			throw new IllegalArgumentException("Check file input. Data cannot be null");
		} else {
			try (Stream<String> streamFromFiles = Files.lines(Paths.get(fileName))) {
				lines = streamFromFiles.collect(Collectors.toList());
			} catch (IOException e) {
				throw new IllegalArgumentException("Check file name. This file doesn't exist");
			}
			return lines;
		}
	}

}
