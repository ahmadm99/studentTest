package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
