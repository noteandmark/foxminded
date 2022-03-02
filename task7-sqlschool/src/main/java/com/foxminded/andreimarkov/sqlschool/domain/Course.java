package com.foxminded.andreimarkov.sqlschool.domain;

import java.util.Objects;

public class Course {

	private int id;
	private String name;
	private String description;

	public Course(String name, String description) {	
		this.name = name;
		this.description = description;
	}
	
	public Course(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getCourseId() {
		return id;
	}

	public void setCourseId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return name;
	}

	public void setCourseName(String name) {
		this.name = name;
	}

	public String getCourseDescription() {
		return description;
	}

	public void setCourseDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + id + ", course_name=" + name + ", course_description="
				+ description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(name, other.name);
	}
	
}
