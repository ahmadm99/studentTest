package com.ahmad.studentTest.service;

import com.ahmad.studentTest.DTO.CourseDTO;
import com.ahmad.studentTest.exception.StudentNotFoundException;
import com.ahmad.studentTest.model.Course;
import com.ahmad.studentTest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<CourseDTO> getCourseDTO() {
       List<CourseDTO> courses =  courseRepository.findAll().stream().map(course -> new CourseDTO(course.getId(),course.getName())).collect(Collectors.toList());
       return courses;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public void deleteCourse(Long courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
        } else {
            throw new StudentNotFoundException(courseId);
        }
    }
}
