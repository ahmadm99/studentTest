package com.ahmad.studentTest.model;

import org.springframework.stereotype.Component;

@Component
public class CourseFactory {
    public static Course createCourse(String courseName){
        Course course = new Course(courseName);
        return course;
    }

    public static Course createCourse(){
        return new Course();
    }
}
