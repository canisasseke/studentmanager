package com.zopenlab.studentManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zopenlab.studentManager.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}
