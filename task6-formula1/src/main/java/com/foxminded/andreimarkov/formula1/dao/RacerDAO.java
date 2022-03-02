package com.foxminded.andreimarkov.formula1.dao;

import java.util.List;

interface RacerDAO {
	List<String> readLinesFromFile(String fileName);
}