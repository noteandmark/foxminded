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

import com.foxminded.andreimarkov.sqlschool.dao.AbstractDao;
import com.foxminded.andreimarkov.sqlschool.dao.ConnectionJdbc;
import com.foxminded.andreimarkov.sqlschool.domain.Course;
import com.foxminded.andreimarkov.sqlschool.domain.Student;

public class StudentDao extends AbstractDao<Student> {

	private static final String CREATE_STUDENT = "INSERT INTO school.students (first_name, last_name) VALUES (?,?);";
	private static final String UPDATE_STUDENT = "UPDATE school.students set group_id = ?, first_name = ?, last_name = ? WHERE school.students.student_id = ?";
	private static final String FIND_STUDENTS_BY_COURSE = "SELECT school.students.student_id, school.students.group_id, school.students.first_name, school.students.last_name\r\n"
			+ "FROM school.students\r\n" + "INNER JOIN school.students_courses\r\n"
			+ "ON school.students.student_id = school.students_courses.student_id\r\n" + "INNER JOIN school.courses\r\n"
			+ "ON school.students_courses.course_id = school.courses.course_id\r\n"
			+ "WHERE school.courses.course_name = ?";

	private static final String DELETE_STUDENT = "DELETE FROM school.students WHERE student_id = ?";
	private static final String ADD_STUDENT_TO_COURSE = "INSERT INTO school.students_courses (student_id,course_id) VALUES (?,?)";
	private static final String READ_STUDENT_BY_ID = "SELECT school.students.student_id, school.students.group_id, school.students.first_name, school.students.last_name\r\n"
			+ "FROM school.students\r\n" + "WHERE school.students.student_id = ?";
	private static final String DELETE_STUDENT_FROM_COURSE = "DELETE FROM school.students_courses\r\n"
			+ "WHERE (school.students_courses.student_id = ? AND school.students_courses.course_id = ?);";
	private ConnectionJdbc connectionJdbc;

	public StudentDao() {
		try {
			connectionJdbc =  new ConnectionJdbc();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Student create(Student student) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_STUDENT,
						Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				student.setStudentId(generatedKeys.getInt(1));			
			}
		}
		return student;
	}

	@Override
	public Optional<Student> read(int student_id) throws SQLException {
		Student student = new Student("","");
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_STUDENT_BY_ID)) {
			statement.setInt(1, student_id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return Optional.of(mapToStudent(resultSet));
			}
		}
		return Optional.empty();
	}

	public List<Student> findStudentByCourse(String courseName) throws SQLException {
		List<Student> studentList = new ArrayList<>();
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_STUDENTS_BY_COURSE)) {
			statement.setString(1, courseName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				studentList.add(mapToStudent(resultSet));
			}
		}
		return studentList;
	}

	public void addStudentToCourse(Student student, Course course) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_STUDENT_TO_COURSE)) {
			statement.setInt(1, student.getStudentId());
			statement.setInt(2, course.getCourseId());
			statement.executeUpdate();
		}
	}

	@Override
	public Student update(Student student) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT)) {
			statement.setInt(1, student.getGroupId());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getStudentId());
			statement.executeUpdate();
		}
		return student;
	}

	@Override
	public void delete(Student student) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT)) {
			statement.setInt(1, student.getStudentId());
			statement.executeUpdate();
		}
	}

	public void removeStudentFromCourse(Student student, Course course) throws SQLException {
		try (Connection connection = connectionJdbc.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_FROM_COURSE)) {
			statement.setInt(1, student.getStudentId());
			statement.setInt(2, course.getCourseId());
			statement.executeUpdate();
		}		
	}
	
	private Student mapToStudent(ResultSet resultSet) throws SQLException {
		return new Student(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
	}

}
