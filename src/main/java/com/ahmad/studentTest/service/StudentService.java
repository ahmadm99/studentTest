package com.ahmad.studentTest.service;


import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentEmail.isPresent()){
            System.out.println("Email is already taken");
        }
        else{
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        if(studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
        }
        else{
            System.out.println("No student found with given id");
        }
    }
    @Transactional
    public void updateStudent(Long studentId, Student student){
        Student student1 = studentRepository.findById(studentId).get();
        student1.setName(student.getName());
        student1.setDob(student.getDob());
        student1.setEmail(student.getEmail());
    }

}

