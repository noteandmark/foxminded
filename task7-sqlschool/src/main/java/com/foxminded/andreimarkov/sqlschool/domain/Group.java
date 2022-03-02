package com.foxminded.andreimarkov.sqlschool.domain;

import java.util.Objects;

public class Group {

	private int id;
	private String name;

	public Group() {
		
	}
	
	public Group(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getGroupId() {
		return id;
	}

	public void setGroupId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return name;
	}

	public void setGroupName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [group_id=" + id + ", group_name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
}
