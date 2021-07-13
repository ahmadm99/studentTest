package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.SpecialStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialStudentRepository extends JpaRepository<SpecialStudent,Long> {
    @Modifying
    @Query(value = "UPDATE STUDENT SET STUDENT_TYPE='Default' WHERE id = :id",nativeQuery = true)
    void updateType(Long id);
}
