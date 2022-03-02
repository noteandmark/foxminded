package com.foxminded.andreimarkov.formula1.ui;

import com.foxminded.andreimarkov.formula1.service.RacerService;

public class Formula1 {

	public static void main(String[] args) {
		String abbreviations = "./src/main/resources/abbreviations.txt";
		String start = "./src/main/resources/start.log";
		String end = "./src/main/resources/end.log";
		RacerService racerService = new RacerService(abbreviations, start, end);
		Formatter formatter = new Formatter();
		System.out.println(formatter.format((racerService.getRacers())));
	}
}
