package com.foxminded.andreimarkov.sqlschool.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.andreimarkov.sqlschool.domain.Course;
import com.foxminded.andreimarkov.sqlschool.domain.Group;
import com.foxminded.andreimarkov.sqlschool.domain.Student;
import com.foxminded.andreimarkov.sqlschool.service.DataGeneratorService;
import com.foxminded.andreimarkov.sqlschool.service.GroupService;
import com.foxminded.andreimarkov.sqlschool.service.StudentService;

@SuppressWarnings("serial")
public class ServletMenu extends HttpServlet {
	private static final String START_BODY_HTML = "<html><body><p>";
	private static final String END_BODY_HTML = "</p></body></html>";
	private GroupService groupService;
	private StudentService studentService;

	@Override
	public void init() {
		try {
			groupService = new GroupService();
			studentService = new StudentService();
			// generating test data
			DataGeneratorService testData = new DataGeneratorService();
			Set<Group> groupsList = testData.createGroups(10);
			List<Course> coursesList = testData.createCourses("ListOfCources.txt");
			List<Student> studentsList = testData.createStudents("FirstNames.txt", "LastName.txt");
			testData.assignStudentsToGroups(studentsList, groupsList);
			testData.assignStudentsToCourses(studentsList, coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String choice = request.getParameter("choice");

		response.setContentType("text/html;charset=utf-8");
		out.println("<!DOCTYPE HTML>");

		if ("1".equals(choice)) {
			String studentCount = request.getParameter("studentCount");
			List<Group> groupsResult = groupService.getGroupsWithCountStudent(studentCount);
			out.println(START_BODY_HTML + "Result is " + groupsResult.toString() + END_BODY_HTML);
		}
		if ("2".equals(choice)) {
			String courseName = request.getParameter("courseName");
			List<Student> studentsResult = studentService.findStudentByCourse(courseName);
			out.println(START_BODY_HTML + "Result is " + studentsResult.toString() + END_BODY_HTML);
		}
		if ("3".equals(choice)) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			Student student = studentService.addStudent(firstName, lastName);
			out.println(START_BODY_HTML + "You create the student: first_name = " + student.getFirstName()
					+ " last_name = " + student.getLastName() + END_BODY_HTML);
		}
		if ("4".equals(choice)) {
			String studentIdLine = request.getParameter("studentId");
			int studentId = Integer.parseInt(studentIdLine);
			String resultMenu4 = studentService.deleteStudent(studentId);
			out.println(START_BODY_HTML + resultMenu4 + END_BODY_HTML);
		}
		if ("5".equals(choice)) {
			String studentIdAddLine = request.getParameter("studentIdAdd");
			int studentIdAdd = Integer.parseInt(studentIdAddLine);
			String courseIdLine = request.getParameter("courseId");
			int courseId = Integer.parseInt(courseIdLine);
			String resultMenu5 = null;
			resultMenu5 = studentService.addStudentToCourse(studentIdAdd, courseId);
			out.println(START_BODY_HTML + resultMenu5 + END_BODY_HTML);
		}
		if ("6".equals(choice)) {
			String studentIdAddDelete = request.getParameter("studentIdDelete");
			int studentId = Integer.parseInt(studentIdAddDelete);
			String courseIdDelete = request.getParameter("courseIdDelete");
			int courseId = Integer.parseInt(courseIdDelete);
			String resultMenu6 = studentService.removeStudentFromCourse(studentId, courseId);
			out.println(START_BODY_HTML + resultMenu6 + END_BODY_HTML);
		}

		out.println("<p>" + "Back to " + "<a href=/sqlschool>" + "Home page" + "</a></p>");
	}

}
