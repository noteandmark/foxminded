package com.foxminded.andreimarkov.sqlschool.dao.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.foxminded.andreimarkov.sqlschool.domain.Group;
import com.foxminded.andreimarkov.sqlschool.dao.AbstractDao;
import com.foxminded.andreimarkov.sqlschool.dao.ConnectionJdbc;

public class GroupDao extends AbstractDao<Group> {
	private static final String CREATE_GROUP = "INSERT INTO school.groups (group_name) VALUES (?)";
	private static final String READ_GROUP_WITH_N_STUDENTS = "SELECT school.groups.group_id, school.groups.group_name\r\n"
			+ "FROM school.groups INNER JOIN school.students\r\n"
			+ "ON school.groups.group_id = school.students.group_id\r\n"
			+ "GROUP BY school.groups.group_id, school.groups.group_name\r\n"
			+ "HAVING count(*) <= ?\r\n"
			+ "ORDER BY school.groups.group_id";
	private static final String DELETE_GROUP = "DELETE FROM school.groups WHERE group_id = ?";
	private static final String UPDATE_GROUP = "UPDATE school.groups set group_name = ? WHERE group_id = ?";
	private static final String READ_GROUP_BY_ID = "SELECT school.groups.group_id, school.groups.group_name\r\n"
			+ "FROM school.groups\r\n"
			+ "WHERE school.groups.group_id = ?";
	private ConnectionJdbc connectionJdbc;
	
	public GroupDao() {
		try {
			connectionJdbc =  new ConnectionJdbc();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Group create(Group group) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_GROUP,
						Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, group.getGroupName());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				group.setGroupId(generatedKeys.getInt(1));
			}
		}
		return group;
	}
	
	@Override
	public Optional<Group> read(int groupId) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_GROUP_BY_ID)) {	
			statement.setInt(1, groupId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return Optional.of(mapToGroup(resultSet));
			}
		}	
		return Optional.empty();
	}

	public List<Group> getGroupsWithCountStudent(int countStudents) throws SQLException {
		List<Group> groupsList = new ArrayList<>();		
		
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_GROUP_WITH_N_STUDENTS)) {	
			statement.setInt(1, countStudents);			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				groupsList.add(mapToGroup(resultSet));
			}
		}	
		return groupsList;
	}

	@Override
	public Group update(Group group) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP)) {		
			statement.setString(1, group.getGroupName());								
			statement.setInt(2, group.getGroupId());				
			statement.executeUpdate();
		}
		return group;
	}

	@Override
	public void delete(Group group) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP)) {		
			statement.setInt(1, group.getGroupId());
			statement.executeUpdate();
		}
	}

	private Group mapToGroup(ResultSet resultSet) throws SQLException {
		return new Group(resultSet.getInt(1), resultSet.getString(2));
	}
	
}
