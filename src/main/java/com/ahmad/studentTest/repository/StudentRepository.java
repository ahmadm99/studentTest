package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository<T extends Student> extends JpaRepository<T,Long> {
    Optional<T> findStudentByEmail(String email);

}
