package com.ahmad.studentTest.controller;

import com.ahmad.studentTest.DTO.CourseDTO;
import com.ahmad.studentTest.model.Course;
import com.ahmad.studentTest.repository.CourseRepository;
import com.ahmad.studentTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(path = "coursedto")
    public List<CourseDTO> getCoursesDTO(){
        return courseService.getCourseDTO();
    }

    @GetMapping(path = "courses")
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    @DeleteMapping(path = "delete/course/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<String>("Course deleted successfully", HttpStatus.OK);
    }
}
