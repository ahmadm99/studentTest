package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByName(String name);
}
