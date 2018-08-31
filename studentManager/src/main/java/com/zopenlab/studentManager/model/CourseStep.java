package com.zopenlab.studentManager.model;

import java.util.ArrayList;
import java.util.List;

public class CourseStep {

	Course course =new Course();
	List<Step> steps=new ArrayList<Step>();
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Step> getSteps() {
		return steps;
	}
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
}
