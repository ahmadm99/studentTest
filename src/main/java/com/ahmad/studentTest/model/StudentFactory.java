package com.ahmad.studentTest.model;

public class StudentFactory extends BaseStudentFactory{
    @Override
    public Student createStudent(Boolean type) {
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
