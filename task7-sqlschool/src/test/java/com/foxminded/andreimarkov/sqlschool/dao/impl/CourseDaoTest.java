package com.foxminded.andreimarkov.sqlschool.dao.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

class CourseDaoTest {

	private CourseDao courseDao;

	@BeforeAll
	public static void init() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
		TestResourseLoader loader = new TestResourseLoader();
		loader.create("schema.sql");
	}	
	
	@BeforeEach
	public void setUp() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
		courseDao = new CourseDao();
		TestResourseLoader loader = new TestResourseLoader();
		loader.create("customData.sql");	
	}

	@Test
	void testCreate_whenGivenNewCourse_thenShouldCreateNewOne() throws SQLException, IOException {

		Course expected = new Course("Some course","Some description");
		courseDao.create(expected);

		Course actual = courseDao.read(expected.getCourseId()).get();

		assertEquals(expected, actual);
	}

	@Test
	void testRead_whenGivenCourseId_thenGetCourse() throws SQLException, IOException {

		Course expected = new Course("Some course","Some description");
		courseDao.create(expected);
		Course actual = courseDao.read(expected.getCourseId()).get();
		assertEquals(expected, actual);
	}
	
	@Test
	void testRead_whenGivenWrongCourseId_thenThrowException() throws SQLException, IOException {

		assertThrows(NoSuchElementException.class, () -> {
			courseDao.read(1000).get();
		});
	}

	@Test
	void testUpdate_whenChangeCourse_thenUpdate() throws SQLException, IOException {

		Course actual = new Course("Some course","Some description");
		courseDao.create(actual);
		actual.setCourseName("Updated course");
		actual.setCourseDescription("Updated description");
		courseDao.update(actual);

		Course expected = new Course("Updated course","Updated description");
		expected.setCourseId(actual.getCourseId());

		assertEquals(expected, actual);
	}

	@Test
	void testDelete_whenGivenCourse_thenShouldDeleteThisCourse() throws SQLException {
		Course course = new Course("Course for delete","This is to deleted");
		courseDao.create(course);
		courseDao.delete(course);

		assertThrows(NoSuchElementException.class, () -> {
			courseDao.read(course.getCourseId()).get();
		});
	}

	@Test
	void testAssignStudentToCourse() throws SQLException {
		//create course
		Course course = new Course(1000,"Some course","Some description");
		courseDao.create(course);
		//create student
		Student student = new Student(1000, 0, "Some", "Student");
		StudentDao studentDao = new StudentDao();
		studentDao.create(student);		
		//assign student to course
		courseDao.assignStudentToCourse(student, course);
		List<Student> expected = new ArrayList<>();
		expected.add(student);
		List<Student> actual = studentDao.findStudentByCourse(course.getCourseName());
		
		//test check
		assertEquals(expected, actual);
	}
}
