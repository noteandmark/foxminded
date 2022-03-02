package com.foxminded.andreimarkov.sqlschool.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.andreimarkov.sqlschool.dao.impl.CourseDao;
import com.foxminded.andreimarkov.sqlschool.dao.impl.GroupDao;
import com.foxminded.andreimarkov.sqlschool.dao.impl.StudentDao;
import com.foxminded.andreimarkov.sqlschool.domain.Course;
import com.foxminded.andreimarkov.sqlschool.domain.Group;
import com.foxminded.andreimarkov.sqlschool.domain.Student;

@ExtendWith(MockitoExtension.class)
class DataGeneratorServiceTest {

	@InjectMocks
	private DataGeneratorService testData;

	@Mock
	private GroupDao groupDao;
	@Mock
	private CourseDao courseDao;
	@Mock
	private StudentDao studentDao;

	@Test
	void testCreateGroups_whenGetNumberOfGroups_thenShouldCreate() throws SQLException {
		testData.createGroups(10);
		verify(groupDao, times(10)).create(any());
	}

	@Test
	void testCreateCourses_whenGetFileName_thenCreateCourses() throws IOException, URISyntaxException, SQLException {
		String fileName = "ListOfCources.txt";
		List<Course> actual = new ArrayList<Course>();
        actual.add(new Course(0,"Mathematics","Topics in mathematics that every educated person needs to know to process, evaluate, and understand the numerical and graphical information in our society"));
        actual.add(new Course(0,"Biology","This course includes a study of living organisms and vital processes"));
        actual.add(new Course(0,"Art","Emphasis is placed on understanding the Elements of Art and Principles of Design as a basis for composition"));
        actual.add(new Course(0,"Spanish foreign language","Understanding of basic vocabulary, grammar and pronunciation"));
        actual.add(new Course(0,"Handwriting","There are four main aspects of handwriting instruction: pencil grasp, formation, legibility, and pacing"));
        actual.add(new Course(0,"Music","Designed to enhance music skills and basic music fundamentals"));
        actual.add(new Course(0,"Geography","The study of the spatial aspects of human existence, enables students to find answers to questions about the world around them"));
        actual.add(new Course(0,"Computer Science","Topics include an historical perspective, evolving hardware and software, using the Internet, creating web pages, social implications, and using a modern programming language"));
        actual.add(new Course(0,"Sports","These courses will cover sport, fitness and coaching, helping students understand the science and leadership behind these areas"));
        actual.add(new Course(0,"Choir","Chorus students will learn to use their vocal instrument to create a correct and pleasing singing sound"));

		assertThat("",testData.createCourses(fileName),is(actual));
		verify(courseDao, times(10)).create(any());
	}

	@Test
	void testCreateStudents_whenGetFileWithNames_thenShouldCreateStudents() throws IOException, SQLException {
		testData.createStudents("FirstNames.txt", "LastName.txt");
		verify(studentDao, times(200)).create(any());		
	}

	@Test
	void testAssignStudentsToGroups_whenGetListsOfStudentAndGroup_thenRandomlyAssignBetweenIt() throws SQLException {
		List<Student> studentsList = Arrays.asList(
				new Student(1, 0, "Sam", "Winston"),
				new Student(2, 0, "Bill", "Shenkly")
				);
		Set<Group> groupsList = new HashSet<>(Arrays.asList(
				new Group(1,"aa-11"),
				new Group(2, "bb-22")
				));
		testData.assignStudentsToGroups(studentsList, groupsList);
		Mockito.verify(studentDao, Mockito.atLeastOnce()).update(Mockito.any());
	}

	@Test
	void testAssignStudentsToCourses_whenGetListsOfStudentsAndCourses_thenRandomlyAssignOneAtAnother() throws SQLException {
		List<Student> studentsList = Arrays.asList(
				new Student(1, 0, "Sam", "Winston"),
				new Student(2, 0, "Bill", "Shenkly")
				);
		List<Course> coursesList = Arrays.asList(
				new Course(1, "FirstCourse", "Some"),
				new Course(2, "SecondCourse", "Some")
				);
		testData.assignStudentsToCourses(studentsList, coursesList);
		Mockito.verify(courseDao, Mockito.atLeastOnce()).assignStudentToCourse(Mockito.any(),Mockito.any());
	}

}
