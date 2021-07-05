package com.ahmad.studentTest.controller;

import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class StudentController {

    @Autowired //always use this instead of constructor, but be careful, if we ever initialize the object it won't work;
    private StudentService studentService;

    @GetMapping(path="dto")
    public List<StudentDTO> getDTO(){return studentService.getDTO();}
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody StudentDTO dto) throws InterruptedException {
        studentService.addNewStudent(dto);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentDTO dto) throws InterruptedException{
        studentService.updateStudent(studentId,dto);
    }
}
