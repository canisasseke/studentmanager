package com.zopenlab.studentManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zopenlab.studentManager.model.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {

}
