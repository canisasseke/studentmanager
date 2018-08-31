package com.zopenlab.studentManager.service;

import java.util.List;
import java.util.Set;

import com.zopenlab.studentManager.model.Course;
import com.zopenlab.studentManager.model.Step;
import com.zopenlab.studentManager.model.Student;

public interface IStudentService {

	
	public List<Student> getAllStudents();
	public Set<Course> getCoursesOfStudent(String studentid);
	public void addCoursetoStudent(String studentid, Course course);
	public void addCoursesToStudent(String studentid,Set<Course> courses);
	public Course getDetailsOfCourseFromStudent(String studentid, String courseid);
	public Student saveStudent(Student student);
	public Course saveCourse(Course course);
	public Course saveCourseWithSteps(String courseid, List<Step> steps);
	public List<Course> getAllCourses();
	
}
