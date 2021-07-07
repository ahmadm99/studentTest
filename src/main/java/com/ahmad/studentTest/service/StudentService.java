package com.ahmad.studentTest.service;


import com.ahmad.studentTest.DTO.StudentDTO;
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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    public void addNewStudent(StudentDTO dto) throws InterruptedException {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(dto.getEmail());
//        TimeUnit.SECONDS.sleep(5);
        if(studentEmail.isPresent()){
            throw new StudentAlreadyExistsException(dto.getEmail());
        }
        else{
            if(!dto.getType() && dto.getAmount()!=1000 || dto.getType() && dto.getAmount()!=500){
                throw new InvalidDataException(dto.getAmount());
            }
            if(dto.getType()){
                SpecialStudent specialStudent = new SpecialStudent();
                specialStudent.setName(dto.getName());
                specialStudent.setDob(dto.getDob());
                specialStudent.setEmail(dto.getEmail());
                specialStudent.setAmount(dto.getAmount());
                studentRepository.save(specialStudent);
            }
            else{
                DefaultStudent defaultStudent = new DefaultStudent();
                defaultStudent.setName(dto.getName());
                defaultStudent.setDob(dto.getDob());
                defaultStudent.setEmail(dto.getEmail());
                defaultStudent.setAmount(dto.getAmount());
                studentRepository.save(defaultStudent);
            }
        }
    }

    public void deleteStudent(Long studentId) {
        if(studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
        }
        else{
            throw new StudentNotFoundException(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, StudentDTO dto) throws InterruptedException {
        if(!studentRepository.findById(studentId).isPresent()){
            throw new StudentNotFoundException(studentId);
        }
        if(!dto.getType() && dto.getAmount()!=1000 || dto.getType() && dto.getAmount()!=500) {
            throw new InvalidDataException(dto.getAmount());
        }
        if(dto.getType()){
            SpecialStudent specialStudent = specialStudentRepository.findById(studentId).get();
            specialStudent.setName(dto.getName());
            specialStudent.setDob(dto.getDob());
            specialStudent.setEmail(dto.getEmail());
            specialStudent.setAmount(dto.getAmount());
        }
        else{
            DefaultStudent defaultStudent = defaultStudentRepository.findById(studentId).get();
            defaultStudent.setName(dto.getName());
            defaultStudent.setDob(dto.getDob());
            defaultStudent.setEmail(dto.getEmail());
            defaultStudent.setAmount(dto.getAmount());
        }

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