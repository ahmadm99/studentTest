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

import java.util.List;
import java.util.Optional;

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

    @PostMapping(path = "addcourse")
    public ResponseEntity<String> addNewCourse(@RequestBody CourseDTO courseDTO){
        if(courseService.addNewCourse(courseDTO) != null){
            return new ResponseEntity<String>("Course added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error adding course", HttpStatus.OK);
    }

    @PutMapping(path = "updatecourse/{courseId}")
    public ResponseEntity<String> updateCourse(@PathVariable("courseId") Long courseId, @RequestBody CourseDTO courseDTO){
        courseService.updateCourse(courseId,courseDTO);
        return new ResponseEntity<String>("Course updated successfully", HttpStatus.OK);
    }
}
