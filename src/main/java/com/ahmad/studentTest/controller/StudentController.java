package com.ahmad.studentTest.controller;

import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.DTO.StudentRequestDTO;
import com.ahmad.studentTest.model.SpecialStudent;
import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.SpecialStudentRepository;
import com.ahmad.studentTest.service.StudentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class StudentController {

    @Autowired //always use this instead of constructor, but be careful, if we ever initialize the object it won't work;
    private StudentService studentService;

    @Autowired
    private SpecialStudentRepository specialStudentRepository;

    @GetMapping(path="dto")
    public List<StudentDTO> getDTO(){return studentService.getDTO();}

    @GetMapping(path = "s")
    public List<SpecialStudent> getSpecialDTO(){return specialStudentRepository.findAll();}

    @GetMapping(path = "ss")
    public SpecialStudent getSpecial1DTO(){return specialStudentRepository.findById(2L).get();}


    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public ResponseEntity<String> registerNewStudent(@RequestBody StudentRequestDTO dto) throws InterruptedException {
        return studentService.addNewStudent(dto);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId){
        return studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentRequestDTO dto) throws InterruptedException{
        return studentService.updateStudent(studentId,dto);
    }
}
