package com.ahmad.studentTest.model;

import org.springframework.stereotype.Component;

@Component
public class StudentFactory extends BaseStudentFactory{
    @Override
    public Student createStudent(Boolean type) { //switch statement + add invalid type exception
        Student student;
        if(type){
            student = new SpecialStudent();
        }
        else{
            student = new DefaultStudent();
        }
        return student;
    }

}
//make the method static //consequences //test static method like bean testing