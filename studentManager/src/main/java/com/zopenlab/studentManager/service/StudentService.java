package com.zopenlab.studentManager.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zopenlab.studentManager.dao.CourseRepository;
import com.zopenlab.studentManager.dao.StudentRepository;
import com.zopenlab.studentManager.exceptions.StudentServiceException;
import com.zopenlab.studentManager.model.Course;
import com.zopenlab.studentManager.model.Step;
import com.zopenlab.studentManager.model.Student;

@Service
@Transactional
public class StudentService implements IStudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
/*	@Autowired
	private StepRepository stepRepository;
*/
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Set<Course> getCoursesOfStudent(String studentid) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findById(studentid);
		if(!student.isPresent()) throw new StudentServiceException("student does not exist");
		//activation du lazy loading par la methode toString (methode toString choisie mais pas obligatoire)
		student.get().getCourses().toString();
		//System.out.println( student.get().getCourses());
		return student.get().getCourses();
	}

	@Override
	public void addCoursetoStudent(String studentid, Course course) {
		// TODO Auto-generated method stub
		Optional<Student> studentopt = studentRepository.findById(studentid);
		if(!studentopt.isPresent()) throw new StudentServiceException("student does not exist");
		Optional<Course> courseopt=courseRepository.findById(course.getId());
		if(!courseopt.isPresent()) throw new StudentServiceException("course does not exist");
		Student student=studentopt.get();
		Course course1=courseopt.get();
		student.getCourses().add(course1);
		
	}
	@Override
	public void addCoursesToStudent(String studentid, Set<Course> courses) {
		// TODO Auto-generated method stub
		Optional<Student> studentopt = studentRepository.findById(studentid);
		if(!studentopt.isPresent()) throw new StudentServiceException("student does not exist");
		Student student=studentopt.get();
		student.getCourses().addAll(courses);
	}

	@Override
	public Course getDetailsOfCourseFromStudent(String studentid, String courseid) {
		// TODO Auto-generated method stub
		Optional<Student> studentopt = studentRepository.findById(studentid);
		if(!studentopt.isPresent()) throw new StudentServiceException("student does not exist");
		Student student=studentopt.get();
		for(Course c:student.getCourses()){
			if(c.getId().equals(courseid)){
				return courseRepository.findById(courseid).get();
			}
		}
		return null;
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public Course saveCourse(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}

	@Override
	public Course saveCourseWithSteps(String courseid, List<Step> steps) {
		// TODO Auto-generated method stub
		Optional<Course> courseopt=courseRepository.findById(courseid);
		if(!courseopt.isPresent()) throw new StudentServiceException("course does not exist");
		Course course=courseopt.get();
		course.getSteps().addAll(steps);
		for(Step s:steps){
			s.setCourse(course);
		}
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	
	

}
