package com.ahmad.studentTest.service;


import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.DTO.StudentRequestDTO;
import com.ahmad.studentTest.exception.StudentAlreadyExistsException;
import com.ahmad.studentTest.exception.StudentNotFoundException;
import com.ahmad.studentTest.model.*;
import com.ahmad.studentTest.repository.StudentRepository;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.mysql.cj.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(StudentRequestDTO dto) throws InterruptedException {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(dto.getEmail());
        if (studentEmail.isPresent()) {
            throw new StudentAlreadyExistsException(dto.getEmail());
        }
        TimeUnit.SECONDS.sleep(5);
        Student student = StudentFactory.createStudent(dto.getType());
        student.setName(dto.getName());
        student.setDob(dto.getDob());
        student.setEmail(dto.getEmail());
            return studentRepository.save(student);
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
        Student student =studentRepository.findById(studentId).get();
        student.setName(dto.getName());
        student.setDob(dto.getDob());
        student.setEmail(dto.getEmail());
        student.setId(studentId);
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

    public Page<Student> getPagination(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("name"));
        return studentRepository.findAll(pageable);
    }
}
//add validation. respond with max size of pages = size of users/pagination size. user specifies size
//many to many relationship
//many to many with eager and lazy load with cascade

//YES spring services are singletons otherwise for every request a new object would be created with a lot of business logic and code