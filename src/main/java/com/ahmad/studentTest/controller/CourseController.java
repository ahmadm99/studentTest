package com.ahmad.studentTest.controller;

import com.ahmad.studentTest.DTO.CourseDTO;
import com.ahmad.studentTest.exception.StudentAlreadyExistsException;
import com.ahmad.studentTest.model.Course;
import com.ahmad.studentTest.repository.CourseRepository;
import com.ahmad.studentTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class CourseController {

    @Autowired
    CourseService courseService;


    @GetMapping(path = "coursedto")
    public List<CourseDTO> getCoursesDTO(){
        return courseService.getCourseDTO();
    }

    @GetMapping(path = "courses")
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    @DeleteMapping(path = "courses/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Course deleted successfully");
        body.put("status",HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping(path = "add/course")
    public ResponseEntity<Course> addNewCourse(@RequestBody CourseDTO courseDTO){
        return new ResponseEntity<>(courseService.addNewCourse(courseDTO), HttpStatus.OK);
    }

    @PutMapping(path = "courses/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable("courseId") Long courseId, @RequestBody CourseDTO courseDTO){
        courseService.updateCourse(courseId,courseDTO);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Course updated successfully");
        body.put("status",HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
