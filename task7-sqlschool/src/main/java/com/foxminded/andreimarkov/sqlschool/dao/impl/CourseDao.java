package com.foxminded.andreimarkov.sqlschool.dao.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.foxminded.andreimarkov.sqlschool.domain.Course;
import com.foxminded.andreimarkov.sqlschool.domain.Student;
import com.foxminded.andreimarkov.sqlschool.dao.AbstractDao;
import com.foxminded.andreimarkov.sqlschool.dao.ConnectionJdbc;

public class CourseDao extends AbstractDao<Course> {
	private static final String CREATE_COURSE = "INSERT INTO school.courses (course_name, course_description) VALUES (?,?)";
	private static final String INSERT_STUDENT_TO_COURSE = "INSERT INTO school.students_courses (student_id, course_id) VALUES (?,?)";
	private static final String DELETE_COURSE = "DELETE FROM school.courses WHERE (course_id = ?)";
	private static final String UPDATE_COURSE = "UPDATE school.courses set course_name = ?, course_description =? WHERE (course_id = ?)";
	private static final String READ_COURSE_BY_ID = "SELECT school.courses.course_id, school.courses.course_name, school.courses.course_description\r\n"
			+ "FROM school.courses\r\n" + "WHERE school.courses.course_id = ?";
	private ConnectionJdbc connectionJdbc;

	public CourseDao() {
		try {
			connectionJdbc =  new ConnectionJdbc();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Course create(Course course) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_COURSE,
						Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, course.getCourseName());
			statement.setString(2, course.getCourseDescription());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				course.setCourseId(generatedKeys.getInt(1));
			}
		}
		return course;
	}

	@Override
	public Optional<Course> read(int id) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_COURSE_BY_ID)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return Optional.of(mapToCourse(resultSet));
			}
		}
		return Optional.empty();
	}

	@Override
	public Course update(Course course) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_COURSE)) {
			statement.setString(1, course.getCourseName());
			statement.setString(2, course.getCourseDescription());
			statement.setInt(3, course.getCourseId());
			statement.executeUpdate();
		}
		return course;
	}

	@Override
	public void delete(Course course) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_COURSE)) {
			statement.setInt(1, course.getCourseId());
			statement.executeUpdate();
		}
	}

	public void assignStudentToCourse(Student student, Course course) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT_TO_COURSE)) {
			statement.setInt(1, student.getStudentId());
			statement.setInt(2, course.getCourseId());
			statement.executeUpdate();
		}
	}

	private Course mapToCourse(ResultSet resultSet) throws SQLException {
		return new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
	}

}
