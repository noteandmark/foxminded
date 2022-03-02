package com.foxminded.andreimarkov.formula1.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.foxminded.andreimarkov.formula1.dao.RacerDAOImpl;
import com.foxminded.andreimarkov.formula1.domain.Racer;

public class RacerService {
	private String abbreviationsFile;
	private String startFile;
	private String endFile;
	private RacerDAOImpl dao = new RacerDAOImpl();

	public RacerService(String abbreviationsFile, String startFile, String endFile) {
		this.abbreviationsFile = abbreviationsFile;
		this.startFile = startFile;
		this.endFile = endFile;
	}
	
	public List<Racer> getRacers() {
			
		List<String> listFileAbbreviatios = dao.readLinesFromFile(abbreviationsFile);
		List<String> listStartFile = dao.readLinesFromFile(startFile);
		List<String> listEndFile = dao.readLinesFromFile(endFile);				
		Map<String,String> startTimeMap = getTimeFromFile(listStartFile,1,0);
		Map<String,String> endTimeMap = getTimeFromFile(listEndFile,1,0);	
		Map<String,String> startDateMap = getTimeFromFile(listStartFile,0,3);
		Map<String,String> endDateMap = getTimeFromFile(listEndFile,0,3);
		
		
		return listFileAbbreviatios.stream()
				.map(s -> s.split("_"))			
				.map(this::buildRacerFromAbbreviation)
				.peek(r -> setRacerStartTime(r,startTimeMap))
				.peek(r -> setRacerEndTime(r,endTimeMap))
				.peek(r -> calculateDuration(r, startDateMap, endDateMap))
				.sorted(Comparator.comparing(Racer::getLapTime))
				.collect(Collectors.toList());
	}
	
	private Racer setRacerStartTime(Racer r, Map<String, String> startTimeMap) {
		LocalTime startTime = LocalTime.parse(startTimeMap.get(r.getAbbreviation()));
		r.setStartTime(startTime);
		return r;
	}
	
	private Racer setRacerEndTime(Racer r, Map<String, String> endTimeMap) {
		LocalTime endTime = LocalTime.parse(endTimeMap.get(r.getAbbreviation()));
		r.setEndTime(endTime);
		return r;
	}
	
	private Racer calculateDuration(Racer r, Map<String, String> startDateMap, Map<String, String> endDateMap) {
		LocalTime startTime = r.getStartTime();
		LocalTime endTime = r.getEndTime();
		LocalDate startDate = LocalDate.parse(startDateMap.get(r.getAbbreviation()));
		LocalDate endDate = LocalDate.parse(endDateMap.get(r.getAbbreviation()));
		LocalDateTime lapStartTime = startTime.atDate(startDate);
		LocalDateTime lapEndTime = endTime.atDate(endDate);
		Duration duration = Duration.between(lapStartTime, lapEndTime);
		long lapTime = duration.toMillis();
		r.setLapTime(lapTime);
		return r;	
	}
	
	private Racer buildRacerFromAbbreviation(String[] abbr) {
		Racer racer = new Racer();
		String abbreviation = abbr[0];
		String name = abbr[1];
		String team = abbr[2];
		racer.setAbbreviation(abbreviation);
		racer.setName(name);
		racer.setTeam(team);
		return racer;	
	}
	
	public RacerDAOImpl getDao() {
		return dao;
	}

	public void setDao(RacerDAOImpl dao) {
		this.dao = dao;
	}

	private Map<String, String> getTimeFromFile(List<String> file, int index, int numPosition) {
		return file.stream()
				.map(line -> line.split("_"))
				.collect(Collectors.toMap(k -> k[0].substring(0, 3), k -> k[index].substring(numPosition)));
	}


	
}
