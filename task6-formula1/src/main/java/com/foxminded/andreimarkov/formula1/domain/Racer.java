package com.foxminded.andreimarkov.formula1.domain;

import java.time.LocalTime;
import java.util.Objects;

public class Racer {

	private String abbreviation;
	private String name;
	private String team;
	private LocalTime startTime;
	private LocalTime endTime;
	private long lapTime;

	
	
	public long getLapTime() {
		return lapTime;
	}

	public void setLapTime(long lapTime) {
		this.lapTime = lapTime;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Racer(String abbreviation, String name, String team, LocalTime startTime, LocalTime endTime,
			long lapTime) {
		super();
		this.abbreviation = abbreviation;
		this.name = name;
		this.team = team;
		this.startTime = startTime;
		this.endTime = endTime;
		this.lapTime = lapTime;
	}

	public Racer() {
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(abbreviation, endTime, lapTime, name, startTime, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Racer other = (Racer) obj;
		return Objects.equals(abbreviation, other.abbreviation) && Objects.equals(endTime, other.endTime)
				&& lapTime == other.lapTime && Objects.equals(name, other.name)
				&& Objects.equals(startTime, other.startTime) && Objects.equals(team, other.team);
	}

	@Override
	public String toString() {
		return "Racer [abbreviation=" + abbreviation + ", name=" + name + ", team=" + team + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", lapTime=" + lapTime + "]";
	}

}
