package com.foxminded.andreimarkov.sqlschool.domain;

import java.util.Objects;

public class Student {

	private int id;
	private int groupId;
	private String firstName;
	private String lastName;

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Student(int id, int groupId, String firstName, String lastName) {
		this.id = id;
		this.groupId = groupId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getStudentId() {
		return id;
	}

	public void setStudentId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "\nStudent [student_id=" + id + ", group_id=" + groupId + ", first_name=" + firstName
				+ ", last_name=" + lastName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, groupId, lastName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(firstName, other.firstName) && groupId == other.groupId
				&& Objects.equals(lastName, other.lastName) && id == other.id;
	}
	
}
