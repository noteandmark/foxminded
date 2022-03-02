package com.foxminded.andreimarkov.sqlschool.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxminded.andreimarkov.sqlschool.dao.impl.CourseDao;
import com.foxminded.andreimarkov.sqlschool.dao.impl.StudentDao;
import com.foxminded.andreimarkov.sqlschool.domain.Course;
import com.foxminded.andreimarkov.sqlschool.domain.Student;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

	@InjectMocks
	private StudentService studentService;

	@Mock
	private StudentDao studentDao;
	@Mock
	private CourseDao courseDao;

	@Test
	void testFindStudentByCourse_whenGetCourseName_thenShouldReturn() throws SQLException {
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student("Tom", "Soier"));
		given(studentDao.findStudentByCourse("Math")).willReturn(studentList);
		List<Student> expected = studentService.findStudentByCourse("Math");
		assertThat(expected, notNullValue());
	}

	@Test
	void testAddStudent_whenGetStudentName_thenShouldAddNewStudentInDao() throws SQLException {
		Student addedStudent = new Student(0, 0, "Tomas", "Oak");
		when(studentDao.create(addedStudent)).thenReturn(addedStudent);
		when(studentDao.read(addedStudent.getStudentId())).thenReturn(Optional.of(addedStudent));
		Student actual = studentService.addStudent("Tomas", "Oak");
		assertThat(actual, notNullValue());
		verify(studentDao).create(any(Student.class));
	}

	@Test
	void testDeleteStudent_whenGetStudentId_thenShouldDeleteThisStudent() throws SQLException {
		Student student = new Student(0, 0, "Bob", "Marly");
		given(studentDao.read(0)).willReturn(Optional.of(new Student(0, 0, "Bob", "Marly")));
		String result = studentService.deleteStudent(0);
		verify(studentDao, times(1)).delete(student);
		assertEquals("Student Bob Marly was deleted", result);
	}

	@Test
	void testDeleteStudent_whenTryToDeleteFakeStudentId_thenShouldThrowExseption() throws SQLException {
		int studentId = 1;
		assertThrows(NoSuchElementException.class, () -> {
			studentService.deleteStudent(studentId);
		});
	}

	@Test
	void testAddStudentToCourse_whenGetStudentIdAndCourseId_thenShouldAddOneToAnother() throws SQLException {
		Student student = new Student(0, 0, "Mike", "Taison");
		Course course = new Course(0, "Box", "Art of boxing");
		given(studentDao.read(0)).willReturn(Optional.of(new Student(0, 0, "Mike", "Taison")));
		given(courseDao.read(0)).willReturn(Optional.of(new Course(0, "Box", "Art of boxing")));
		String result = studentService.addStudentToCourse(0, 0);
		verify(studentDao, times(1)).addStudentToCourse(student, course);
		assertEquals("Student Mike Taison was added to course Box", result);
	}

	@Test
	void testRemoveStudentFromCourse_whenGetStudentIdAndCourseId_thenShouldRemoveOneToAnother() throws SQLException {
		Student student = new Student(0, 0, "Ben", "Watson");
		Course course = new Course(0, "Box", "Art of boxing");		
		given(studentDao.read(0)).willReturn(Optional.of(new Student(0, 0, "Ben", "Watson")));
		given(courseDao.read(0)).willReturn(Optional.of(new Course(0, "Box", "Art of boxing")));
		String result = studentService.removeStudentFromCourse(0, 0);
		verify(studentDao, times(1)).removeStudentFromCourse(student, course);
		assertEquals("Student Ben Watson was removed from course Box", result);	
	}

}
