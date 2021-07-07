package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.SpecialStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialStudentRepository extends JpaRepository<SpecialStudent,Long> {
}
