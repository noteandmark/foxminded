package com.foxminded.andreimarkov.sqlschool.dao.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import com.foxminded.andreimarkov.sqlschool.dao.ConnectionJdbc;
import com.foxminded.andreimarkov.sqlschool.ui.ServletMenu;

public class ResourseReader {
	
	private ConnectionJdbc connectionJdbc;
	private static final String MESSAGE_FILENAME_IS_NULL = "Filename is null";
	private static final String MASK_MESSAGE_FILE_NOT_FOUND = "File \"%s\" not found";

	public Properties readProperties() throws FileNotFoundException, IOException, URISyntaxException {

		Properties properties = new Properties();
		
		InputStream stream = ServletMenu.class.getClassLoader().getResourceAsStream("config.properties"); 
		properties.load(stream);
				
		return properties;
	}
	
	public List<String> readTxtDataFiles(String filename) throws IllegalArgumentException {
		if (Objects.isNull(filename)) {
			throw new IllegalArgumentException(MESSAGE_FILENAME_IS_NULL);
		}
		List<String> fileContents = new ArrayList<>();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
			fileContents = bufferedReader.lines().collect(Collectors.toList());

		} catch (IOException | NullPointerException e) {
			throw new IllegalArgumentException(String.format(MASK_MESSAGE_FILE_NOT_FOUND, filename), e);
		}
		return fileContents;

	}
}