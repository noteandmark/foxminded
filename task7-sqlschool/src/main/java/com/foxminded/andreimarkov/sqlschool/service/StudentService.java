package com.foxminded.andreimarkov.sqlschool.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.foxminded.andreimarkov.sqlschool.dao.impl.CourseDao;
import com.foxminded.andreimarkov.sqlschool.dao.impl.StudentDao;
import com.foxminded.andreimarkov.sqlschool.domain.Course;
import com.foxminded.andreimarkov.sqlschool.domain.Student;

public class StudentService {

	private StudentDao studentDao = new StudentDao();
	private static final String STUDENT = "Student ";
	private CourseDao courseDao = new CourseDao();

	public List<Student> findStudentByCourse(String courseName) {
		List<Student> result = new ArrayList<>();
		try {
			studentDao.findStudentByCourse(courseName).stream().forEach(result::add);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Student addStudent(String firstName, String lastName) {
		Student student = new Student(firstName, lastName);
		try {
			studentDao.create(student);
			student = studentDao.read(student.getStudentId()).get();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	public String deleteStudent(int studentId) {
		String result = null;
		String firstName = null;
		String lastName = null;
		try {
			Student student = studentDao.read(studentId).get();
			firstName = student.getFirstName();
			lastName = student.getLastName();
			studentDao.delete(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result = STUDENT + firstName + " " + lastName + " was deleted";
		return result;
	}

	public String addStudentToCourse(int studentId, int courseId) {
		String result = null;
		String firstName = null;
		String lastName = null;
		String courseName = null;
		try {
			Student student = studentDao.read(studentId).get();
			firstName = student.getFirstName();
			lastName = student.getLastName();
			Course course = courseDao.read(courseId).get();
			courseName = course.getCourseName();
			studentDao.addStudentToCourse(student, course);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result = STUDENT + firstName + " " + lastName + " was added to course " + courseName;
		return result;
	}

	public String removeStudentFromCourse(int studentId, int courseId) {
		String result = null;
		String firstName = null;
		String lastName = null;
		String courseName = null;
		try {
			Student student = studentDao.read(studentId).get();
			firstName = student.getFirstName();
			lastName = student.getLastName();
			Course course = courseDao.read(courseId).get();
			courseName = course.getCourseName();
			studentDao.removeStudentFromCourse(student, course);
			result = STUDENT + firstName + " " + lastName + " was removed from course " + courseName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
