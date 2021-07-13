package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.DefaultStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultStudentRepository extends JpaRepository<DefaultStudent,Long> {
    @Modifying
    @Query(value = "UPDATE STUDENT SET STUDENT_TYPE='Special' WHERE id = :id",nativeQuery = true)
    void updateType(Long id);
}
