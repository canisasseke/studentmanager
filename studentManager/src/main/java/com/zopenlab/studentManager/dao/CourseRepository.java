package com.zopenlab.studentManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zopenlab.studentManager.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

}
