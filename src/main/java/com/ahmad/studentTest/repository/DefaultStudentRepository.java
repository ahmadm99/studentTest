package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.DefaultStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultStudentRepository extends JpaRepository<DefaultStudent,Long> {
}
