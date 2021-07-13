package com.ahmad.studentTest.service;


import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.DTO.StudentRequestDTO;
import com.ahmad.studentTest.exception.InvalidDataException;
import com.ahmad.studentTest.exception.StudentAlreadyExistsException;
import com.ahmad.studentTest.exception.StudentNotFoundException;
import com.ahmad.studentTest.model.DefaultStudent;
import com.ahmad.studentTest.model.SpecialStudent;
import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.DefaultStudentRepository;
import com.ahmad.studentTest.repository.SpecialStudentRepository;
import com.ahmad.studentTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DefaultStudentRepository defaultStudentRepository;

    @Autowired
    private SpecialStudentRepository specialStudentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public ResponseEntity<String> addNewStudent(StudentRequestDTO dto) throws InterruptedException {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(dto.getEmail());
        if(studentEmail.isPresent()){
            throw new StudentAlreadyExistsException(dto.getEmail());
        }
        else{
//            if(!dto.getType() && dto.getAmount()!=1000 || dto.getType() && dto.getAmount()!=500){
//                throw new InvalidDataException(dto.getAmount());
//            }
            if(dto.getType()){
                SpecialStudent specialStudent = new SpecialStudent(dto.getName(), dto.getDob(), dto.getEmail());
                studentRepository.save(specialStudent);
                return new ResponseEntity<String>("Student added successfully", HttpStatus.OK);
            }
            else{
                DefaultStudent defaultStudent = new DefaultStudent(dto.getName(), dto.getDob(),dto.getEmail());
                studentRepository.save(defaultStudent);
                return new ResponseEntity<String>("Student added successfully", HttpStatus.OK);
            }
        }
    }

    public ResponseEntity<String> deleteStudent(Long studentId) {
        if(studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
            return new ResponseEntity<String>("Student deleted successfully", HttpStatus.OK);
        }
        else{
            throw new StudentNotFoundException(studentId);
        }
    }

    @Transactional
    public ResponseEntity<String> updateStudent(Long studentId, StudentRequestDTO dto) throws InterruptedException {
        if(!studentRepository.findById(studentId).isPresent()){
            throw new StudentNotFoundException(studentId);
        }
//        if(!dto.getType() && dto.getAmount()!=1000 || dto.getType() && dto.getAmount()!=500) {
//            throw new InvalidDataException(dto.getAmount());
//        }
        if(specialStudentRepository.existsById(studentId)){
            SpecialStudent specialStudent = specialStudentRepository.findById(studentId).get();
            specialStudent.setName(dto.getName());
            specialStudent.setDob(dto.getDob());
            specialStudent.setEmail(dto.getEmail());
            specialStudent.setAmount((short)500);
            if(!dto.getType()) {
                specialStudentRepository.updateType(studentId);
                specialStudentRepository.findById(studentId).get().setAmount((short)1000);
            }
        }
        else{
            DefaultStudent defaultStudent = defaultStudentRepository.findById(studentId).get();
            defaultStudent.setName(dto.getName());
            defaultStudent.setDob(dto.getDob());
            defaultStudent.setEmail(dto.getEmail());
            defaultStudent.setAmount((short)1000);
            if(dto.getType()) {
                defaultStudentRepository.updateType(studentId);
                defaultStudentRepository.findById(studentId).get().setAmount((short)500);
            }
        }
        return new ResponseEntity<String>("Student updated successfully", HttpStatus.OK);
    }

    public List<StudentDTO> getDTO() {
            List<StudentDTO> dto = studentRepository.findAll().stream().map(this::mapStudentDTO).collect(Collectors.toList());
            return dto;
    }

    private StudentDTO mapStudentDTO(Object student){
        StudentDTO dto = new StudentDTO();
        if(student instanceof SpecialStudent){
            SpecialStudent specialStudent = (SpecialStudent) student;
            dto.setName(specialStudent.getName());
            dto.setDob(specialStudent.getDob());
            dto.setEmail(specialStudent.getEmail());
            dto.setAmount(specialStudent.getAmount());
            dto.setType(true);
        }
        else{
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

//YES spring services are singletons otherwise for every request a new object would be created with a lot of business logic and code