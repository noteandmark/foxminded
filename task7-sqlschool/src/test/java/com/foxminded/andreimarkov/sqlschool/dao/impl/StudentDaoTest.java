package com.foxminded.andreimarkov.sqlschool.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.foxminded.andreimarkov.sqlschool.TestResourseLoader;
import com.foxminded.andreimarkov.sqlschool.dao.ConnectionJdbc;
import com.foxminded.andreimarkov.sqlschool.domain.Course;
import com.foxminded.andreimarkov.sqlschool.domain.Student;

class StudentDaoTest {

	private StudentDao studentDao;

	@BeforeAll
	public static void init() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {	
		TestResourseLoader loader = new TestResourseLoader();
		loader.create("schema.sql");
	}

	@BeforeEach
	public void setUp() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
		studentDao = new StudentDao();
		TestResourseLoader loader = new TestResourseLoader();
		loader.create("customData.sql");
	}

	@Test
	void testCreate_whenGivenNewStudent_thenShouldCreateNewOne() throws SQLException {
		Student expected = new Student("Bilbo", "Beggins");
		studentDao.create(expected);
		Student actual = studentDao.read(expected.getStudentId()).get();

		assertEquals(expected, actual);
	}

	@Test
	void testRead_whenGivenStudentId_thenGetStudent() throws SQLException {
		Student expected = new Student("Frodo", "Beggins");
		studentDao.create(expected);

		Student actual = studentDao.read(expected.getStudentId()).get();

		assertEquals(expected, actual);
	}

	@Test
	void testFindStudentByCourse_whenGivenCourseName_thenFindStudent() throws IOException, SQLException {

		List<Student> expected = new ArrayList<>();

		expected.add(new Student(3, 2, "Ilja", "Pustoi"));
		expected.add(new Student(4, 3, "Denis", "Kovalsky"));

		List<Student> actual = studentDao.findStudentByCourse("Course Of Jedi");

		assertEquals(expected, actual);
	}

	@Test
	void testAddStudentToCourse_whenGivenStudentAndCourse_thenUpdateThisInformation() throws SQLException, IOException {

		Student student = new Student("Some","Student");
		studentDao.create(student);
		Course course = new Course(1,"Some", "Course");
		CourseDao courseDao = new CourseDao();
		courseDao.create(course);
		studentDao.addStudentToCourse(student, course);
		
		List<Student> expected = new ArrayList<>();
		expected.add(student);
		List<Student> actual = new ArrayList<>();
		actual = studentDao.findStudentByCourse(course.getCourseName());
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate_whenChangeStudent_thenUpdate() throws SQLException, IOException {

		Student expected = studentDao.read(1).get();
		expected.setLastName("Updated");
		studentDao.update(expected);

		Student actual = studentDao.read(1).get();

		assertEquals(expected, actual);
	}

	@Test
	void testDelete_whenGivenStudent_thenShouldDeleteThisStudent() throws SQLException {
		Student student = new Student("Sam", "Michael");
		studentDao.create(student);
		studentDao.delete(student);

		assertThrows(NoSuchElementException.class, () -> {
			studentDao.read(student.getStudentId()).get();
		});
	}

	@Test
	void testRemoveStudentFromCourse_whenGivenStudentIdAndCourseId_thenShouldDeleteHimOrHerFromCourse()
			throws SQLException {
		Student student1 = new Student("Some","Student");
		Student student2 = new Student("Other","Man");
		studentDao.create(student1);
		studentDao.create(student2);
		Course course = new Course("New Course", "for experiense");
		CourseDao courseDao = new CourseDao();
		courseDao.create(course);
		studentDao.addStudentToCourse(student1, course);
		studentDao.addStudentToCourse(student2, course);
		studentDao.removeStudentFromCourse(student1, course);
		List<Student> expected = new ArrayList<>();
		expected.add(student2);
		List<Student> actual = studentDao.findStudentByCourse(course.getCourseName());

		assertEquals(expected, actual);
	}

}
