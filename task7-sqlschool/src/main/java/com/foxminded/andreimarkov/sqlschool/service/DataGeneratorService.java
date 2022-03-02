package com.foxminded.andreimarkov.sqlschool.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.foxminded.andreimarkov.sqlschool.dao.impl.CourseDao;
import com.foxminded.andreimarkov.sqlschool.dao.impl.GroupDao;
import com.foxminded.andreimarkov.sqlschool.dao.impl.StudentDao;
import com.foxminded.andreimarkov.sqlschool.dao.readers.ResourseReader;
import com.foxminded.andreimarkov.sqlschool.domain.Course;
import com.foxminded.andreimarkov.sqlschool.domain.Group;
import com.foxminded.andreimarkov.sqlschool.domain.Student;

public class DataGeneratorService {

	private static final int NUMBER_OF_STUDENTS = 200;
	ResourseReader fileResourseReader;
	private GroupDao groupDao;
	private CourseDao courseDao;
	private StudentDao studentDao;
	private Random rand;

	public DataGeneratorService() throws SQLException, IOException, URISyntaxException {
		groupDao = new GroupDao();
		courseDao = new CourseDao();
		studentDao = new StudentDao();
		fileResourseReader = new ResourseReader();
		try {
			rand = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public Set<Group> createGroups(int numberOfGroups) throws SQLException {
		Set<Group> groupsList = new HashSet<Group>();
		Group group;
		int index = 1;
		while (groupsList.size() < numberOfGroups) {
			group = generateGroupNames(index);
			groupsList.add(group);
			if (groupsList.size() == index) {
				groupDao.create(group);
				index++;
			}
		}
		return groupsList;
	}

	private Group generateGroupNames(int index) {
		Group group = new Group();
		group.setGroupId(index);
		group.setGroupName(generateRandomName());
		return group;
	}

	private String generateRandomName() {
		StringBuilder name = new StringBuilder();
		char nextChar;
		int nextInt;
		Random rnd = new Random();
		for (int i = 0; i < 2; i++) {
			// lowercase characters go from 97 to 122
			nextChar = (char) (rnd.nextInt(26) + 97);
			name.append(nextChar);
		}
		name.append("-");
		for (int i = 0; i < 2; i++) {
			nextInt = (int) (Math.random() * 10);
			name.append(nextInt);
		}
		return name.toString();
	}

	public List<Course> createCourses(String fileName) throws IOException, URISyntaxException {
		List<Course> coursesList = new ArrayList<>();
		List<String> listOfCourses = fileResourseReader.readTxtDataFiles(fileName);

		return listOfCourses.stream().map(s -> s.split("_")).map(this::buildCourseAndDescription)
				.collect(Collectors.toCollection(() -> coursesList));
	}

	public List<Student> createStudents(String fileListOfFirstNames, String fileListOfLastName) throws IOException {

		List<String> listOfFirstNames = fileResourseReader.readTxtDataFiles(fileListOfFirstNames);
		List<String> listOfLastName = fileResourseReader.readTxtDataFiles(fileListOfLastName);

		return generateStudents(listOfFirstNames, listOfLastName);

	}

	public void assignStudentsToGroups(List<Student> studentsList, Set<Group> groupsList) {
		int randomNumbersStudentsInGroup = 0;
		int min = 10;
		int max = 30;
		int numberOfRandomStudent = 0;

		List<Integer> unappliedStudentsID = new ArrayList<>();
		for (Student student : studentsList) {
			unappliedStudentsID.add(student.getStudentId());
		}

		for (Group group : groupsList) {
			randomNumbersStudentsInGroup = rand.nextInt(max - min + 1) + min;
			while (randomNumbersStudentsInGroup > 0) {
				if (!unappliedStudentsID.isEmpty()) {
					Collections.shuffle(unappliedStudentsID);
					numberOfRandomStudent = getRandomIDForStudent(unappliedStudentsID);
					assignGivenStudentToGroup(studentsList, group, numberOfRandomStudent);
				}
				randomNumbersStudentsInGroup--;
			}
		}

	}

	public void assignStudentsToCourses(List<Student> studentsList, List<Course> coursesList) {
		int randomCoursesToStudents = 0;
		int min = 1;
		int max = 3;
		for (Student student : studentsList) {
			randomCoursesToStudents = rand.nextInt(max - min + 1) + min;
			Collections.shuffle(coursesList);
			for (Course course : coursesList.subList(0, randomCoursesToStudents)) {
				try {
					courseDao.assignStudentToCourse(student, course);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void assignGivenStudentToGroup(List<Student> studentsList, Group group, int numberOfRandomStudent) {
		for (Student student : studentsList) {
			if (student.getStudentId() == numberOfRandomStudent) {
				student.setGroupId(group.getGroupId());
				try {
					studentDao.update(student);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int getRandomIDForStudent(List<Integer> unappliedStudentsID) {
		return unappliedStudentsID.remove(0);
	}

	private List<Student> generateStudents(List<String> listOfFirstNames, List<String> listOfLastName) {
		List<String> studentsNames = new ArrayList<>();

		for (int i = 0; i < listOfFirstNames.size(); i++) {
			for (int j = 0; j < listOfLastName.size(); j++) {
				studentsNames.add(listOfFirstNames.get(i) + "_" + listOfLastName.get(j));
			}
		}

		Collections.shuffle(studentsNames);
		List<String> sublistStudentsNames = studentsNames.subList(0, 400 - NUMBER_OF_STUDENTS); // 400 - max number of
		List<Student> studentsList = new ArrayList<>();
		studentsNames.removeAll(sublistStudentsNames);

		return studentsNames.stream().map(s -> s.split("_")).map(this::buildStudent)
				.sorted(Comparator.comparing(Student::getStudentId))
				.collect(Collectors.toCollection(() -> studentsList));
	}

	private Student buildStudent(String[] elements) {
		String firstName = elements[0];
		String lastName = elements[1];
		Student student = new Student(firstName, lastName);
		try {
			studentDao.create(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	private Course buildCourseAndDescription(String[] elements) {
		Course course = new Course("", "");
		String name = elements[0];
		String description = elements[1];
		course.setCourseName(name);
		course.setCourseDescription(description);
		try {
			courseDao.create(course);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

}
