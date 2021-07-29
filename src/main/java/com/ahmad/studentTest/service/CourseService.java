package com.ahmad.studentTest.service;

import com.ahmad.studentTest.DTO.CourseDTO;
import com.ahmad.studentTest.exception.CourseAlreadyExistsException;
import com.ahmad.studentTest.exception.CourseNotFoundException;
import com.ahmad.studentTest.model.Course;
import com.ahmad.studentTest.model.CourseFactory;
import com.ahmad.studentTest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
            throw new CourseNotFoundException(courseId);
        }
    }

    public Course addNewCourse(CourseDTO courseDTO) {
        Optional<Course> courseName = courseRepository.findCourseByName(courseDTO.getName());
        if(courseName.isPresent()){
            throw new CourseAlreadyExistsException(courseDTO.getName());
        }
//        Course course = new Course(courseDTO.getName());
        Course course = CourseFactory.createCourse(courseDTO.getName());
        return courseRepository.save(course);
    }

    @Transactional
    public void updateCourse(Long courseId, CourseDTO courseDTO) {
        if(!courseRepository.findById(courseId).isPresent()){
            throw new CourseNotFoundException(courseId);
        }
        Course course = courseRepository.findById(courseId).get();
        course.setName(courseDTO.getName());
        courseRepository.save(course);
    }
}
