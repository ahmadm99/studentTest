package com.ahmad.studentTest.controller;

import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.DTO.StudentRequestDTO;
import com.ahmad.studentTest.model.SpecialStudent;
import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.StudentRepository;
import com.ahmad.studentTest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{pageNumber}")
    public Page<Student> getPagination(@PathVariable("pageNumber") int pageNumber){
        return studentService.getPagination(pageNumber);
    }

    @GetMapping(path = "{pageSize}/{pageNumber}")
    public ResponseEntity<Object> getPaginationValid(@PathVariable("pageSize") int pageSize , @PathVariable("pageNumber") int pageNumber){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Total available pages", studentService.getMaxPageNumber(pageSize));
        body.put("Students List",studentService.getPaginationValid(pageSize,pageNumber));
        return new ResponseEntity<>(body, HttpStatus.OK);
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
