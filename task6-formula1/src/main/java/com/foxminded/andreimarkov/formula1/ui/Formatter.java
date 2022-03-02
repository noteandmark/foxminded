package com.foxminded.andreimarkov.formula1.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.foxminded.andreimarkov.formula1.domain.Racer;

public class Formatter {
	private int position = 1;

	public String format(List<Racer> racers) {
		
		return racers.stream()
				.map(this::buildQualifyingTable)
				.collect(Collectors.joining(""));
	}
	
	private StringBuilder buildQualifyingTable(Racer r) {
		StringBuilder resultString = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");	
		
		resultString.append(String.format("%-3s", position++ + "."))
				.append(String.format("%-20s%s%s", r.getName(), "|", " "))
				.append(String.format("%-30s%s%s", r.getTeam(), "|", " "))
				.append(dateFormat.format(convertLapTimeMillisToCalendarTime(r).getTime()))
				.append("\n");
				
		if (position == 16) {
			resultString.append(drawLongLine()).append("\n");
		}
		
		return resultString;		
	}

	private String drawLongLine() {
			String s = "-";
			int n = 65;
			String sRepeat = IntStream.range(0, n).mapToObj(i -> s).collect(Collectors.joining(""));
			return sRepeat;
	}

	private Calendar convertLapTimeMillisToCalendarTime(Racer r) {
		long millis = r.getLapTime();
		int seconds=(int) ((millis/1000)%60);
		long minutes=((millis-seconds)/1000)/60;		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal;
	}

}
