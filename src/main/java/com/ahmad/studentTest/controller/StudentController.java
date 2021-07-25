package com.ahmad.studentTest.controller;

import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.DTO.StudentRequestDTO;
import com.ahmad.studentTest.model.SpecialStudent;
import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.StudentRepository;
import com.ahmad.studentTest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class StudentController {

    @Autowired //always use this instead of constructor, but be careful, if we ever initialize the object it won't work;
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "dto")
    public List<StudentDTO> getDTO() {
        return studentService.getDTO();
    }

    @GetMapping(path = "s")
    public List<Student> getSpecialDTO() {
        return studentRepository.findAll();
    }


    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public ResponseEntity<String> registerNewStudent(@RequestBody StudentRequestDTO dto) throws InterruptedException {
        if (studentService.addNewStudent(dto) != null)
        return new ResponseEntity<String>("Student added successfully", HttpStatus.OK);
        else{
            return new ResponseEntity<String>("Error adding student", HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<String>("Student deleted successfully", HttpStatus.OK);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentRequestDTO dto) throws InterruptedException {
        studentService.updateStudent(studentId, dto);
        return new ResponseEntity<String>("Student updated successfully", HttpStatus.OK);
    }
}
