package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentByEmail(String email);

    Page<Student> findAll(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE STUDENT SET STUDENT_TYPE= :type WHERE id = :id",nativeQuery = true)
    void changeType(Long id,String type);
}
