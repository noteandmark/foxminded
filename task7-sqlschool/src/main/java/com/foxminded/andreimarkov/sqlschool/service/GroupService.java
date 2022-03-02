package com.foxminded.andreimarkov.sqlschool.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.andreimarkov.sqlschool.dao.impl.GroupDao;
import com.foxminded.andreimarkov.sqlschool.domain.Group;

public class GroupService {

	private GroupDao groupDao = new GroupDao();

	public List<Group> getGroupsWithCountStudent(String studentCount) {
		List<Group> result = new ArrayList<>();
		int count = Integer.parseInt(studentCount);
		try {
			groupDao.getGroupsWithCountStudent(count).stream().forEach(g -> result.add(g));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
