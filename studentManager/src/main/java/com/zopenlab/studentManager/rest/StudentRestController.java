package com.zopenlab.studentManager.rest;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zopenlab.studentManager.model.Course;
import com.zopenlab.studentManager.model.CourseStep;
import com.zopenlab.studentManager.model.Step;
import com.zopenlab.studentManager.model.Student;
import com.zopenlab.studentManager.service.IStudentService;

@RestController
public class StudentRestController {

	@Autowired
	IStudentService studentService;
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		
	return studentService.getAllStudents();
	}
	@GetMapping("/students/{studentid}/courses")
	public Set<Course> getCoursesOfStudent(@PathVariable String studentid){
		
		return studentService.getCoursesOfStudent(studentid);
	}
	@GetMapping("/students/{studentid}/courses/{courseid}")
	public Course getDetailsCourseOfStudent(@PathVariable String studentid,@PathVariable String courseid){
		
		return studentService.getDetailsOfCourseFromStudent(studentid, courseid);
	}
	@GetMapping("/courses")
	public List<Course> getAllCourses(){
		return studentService.getAllCourses();
	}
	
	@PostMapping("/students/{studentid}/courses")
	public ResponseEntity<Void> registerStudentForCourse(@PathVariable String studentid,@RequestBody Course course){
		
		studentService.addCoursetoStudent(studentid, course);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	@PostMapping("/courses")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course){
		studentService.saveCourse(course);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	
	}
	@PostMapping("/courses/{courseid}/steps")
	public ResponseEntity<Course> registerCourseWithSteps(@PathVariable String courseid,@RequestBody List<Step> steps){
		
		Course course=studentService.saveCourseWithSteps(courseid, steps);
		return new ResponseEntity<Course>(course,HttpStatus.CREATED);
	}
	
	@PostMapping("/coursesteps")
	public ResponseEntity<CourseStep> saveCourseWithSteps( CourseStep coursestep){
		
		//studentService.saveCourseWithSteps(coursestep.getCourse(), coursestep.getSteps());
		return new ResponseEntity<CourseStep>(coursestep, HttpStatus.CREATED);
	}
}
