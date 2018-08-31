package com.zopenlab.studentManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zopenlab.studentManager.model.Course;
import com.zopenlab.studentManager.model.Step;
import com.zopenlab.studentManager.model.Student;
import com.zopenlab.studentManager.service.IStudentService;

@SpringBootApplication
public class StudentManagerApplication implements CommandLineRunner {

	@Autowired
	IStudentService studentService;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Course course=new Course("course1", "JAVA", "Apprendre Java");
		Course course2=new Course("course2", "Angular", "programmer en Angular2");
		List<Step> steps= new ArrayList<Step>();
		steps.add(new Step("Origine du langage java"));
		steps.add(new Step("Declaration des variable"));
		List<Step> steps2=new ArrayList<>();
		steps2.add(new Step("les bases de Angular"));
		System.out.println("-------------------------les cours-----------------");
		studentService.saveCourse(course);
		studentService.saveCourse(course2);
		System.out.println(studentService.saveCourseWithSteps(course.getId(), steps));
		System.out.println(studentService.saveCourseWithSteps(course2.getId(), steps2));
		System.out.println("-------------------------les etudiants-----------------");
		Student student=studentService.saveStudent(new Student("student1", "ASSEKE canis", "canisasseke@gmail.com", "student1"));
		System.out.println(student);
		studentService.getAllStudents().forEach(System.out::println);
		System.out.println("-------------------------Ajout des cours a un etudiant-----------------");
		Set<Course> courses=new HashSet<>();
		courses.add(course);
		courses.add(course2);
		studentService.addCoursesToStudent(student.getId(), courses);
		//studentService.addCoursetoStudent(student.getId(), course.getId());
		System.out.println("-------------------------liste des cours auxquels  un etudiant est inscrit-----------------");
		studentService.getCoursesOfStudent(student.getId()).forEach(System.out::println);
		System.out.println("-------------------------details d'un cours suivi par un etudiant-----------------");
		System.out.println(studentService.getDetailsOfCourseFromStudent(student.getId(), course.getId()));
	}
}
