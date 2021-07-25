package com.ahmad.studentTest.service;


import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.DTO.StudentRequestDTO;
import com.ahmad.studentTest.exception.StudentAlreadyExistsException;
import com.ahmad.studentTest.exception.StudentNotFoundException;
import com.ahmad.studentTest.model.*;
import com.ahmad.studentTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository<Student> studentRepository;


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(StudentRequestDTO dto) throws InterruptedException {
        BaseStudentFactory baseStudentFactory = new StudentFactory();
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(dto.getEmail());
        if (studentEmail.isPresent()) {
            throw new StudentAlreadyExistsException(dto.getEmail());
        } else {
            // factory design pattern
            //look more for strategy design pattern
            //then merge repos
            Student student = baseStudentFactory.createStudent(dto.getType());
            student.setName(dto.getName());
            student.setDob(dto.getDob());
            student.setEmail(dto.getEmail());
            if(dto.getType()){
                student.setAmount((short)500);
            }
            else{
                student.setAmount((short)1000);
            }
            return studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new StudentNotFoundException(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, StudentRequestDTO dto) throws InterruptedException {
        if (!studentRepository.findById(studentId).isPresent()) {
            throw new StudentNotFoundException(studentId);
        }
        BaseStudentFactory baseStudentFactory = new StudentFactory();
        Student student =studentRepository.findById(studentId).get();
        student.setName(dto.getName());
        student.setDob(dto.getDob());
        student.setEmail(dto.getEmail());
        if(dto.getType()){
            student.setAmount((short)500);
            studentRepository.changeType(studentId,"Special");
        }
        else{
            student.setAmount((short)1000);
            studentRepository.changeType(studentId,"Default");
        }
    }

    public List<StudentDTO> getDTO() {
        List<StudentDTO> dto = studentRepository.findAll().stream().map(this::mapStudentDTO).collect(Collectors.toList());
        return dto;
    }

    private StudentDTO mapStudentDTO(Object student) {
        StudentDTO dto = new StudentDTO();
        if (student instanceof SpecialStudent) {
            SpecialStudent specialStudent = (SpecialStudent) student;
            dto.setName(specialStudent.getName());
            dto.setDob(specialStudent.getDob());
            dto.setEmail(specialStudent.getEmail());
            dto.setAmount(specialStudent.getAmount());
            dto.setType(true);
        } else {
            DefaultStudent defaultStudent = (DefaultStudent) student;
            dto.setName(defaultStudent.getName());
            dto.setDob(defaultStudent.getDob());
            dto.setEmail(defaultStudent.getEmail());
            dto.setAmount(defaultStudent.getAmount());
            dto.setType(false);
        }
        return dto;
    }

}
//Transformer / Serializer
//RESPONSE ENTITY IN CONTROLLER

//YES spring services are singletons otherwise for every request a new object would be created with a lot of business logic and code