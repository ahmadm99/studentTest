package com.ahmad.studentTest.controller;

import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.DTO.StudentRequestDTO;
import com.ahmad.studentTest.model.Course;
import com.ahmad.studentTest.model.SpecialStudent;
import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.StudentRepository;
import com.ahmad.studentTest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
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


    @GetMapping(path = "studentdto")
    public List<StudentDTO> getStudentDTO() {
        return studentService.getStudentDTO();
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{pageNumber}")
    public Page<Student> getPagination(@PathVariable("pageNumber") int pageNumber){
        return studentService.getPagination(pageNumber);
    }

    @GetMapping(path = "page")
    public ResponseEntity<Object> getPaginationValid(@RequestParam(name = "size") int pageSize , @RequestParam("number") int pageNumber){
        return new ResponseEntity<>(studentService.getPaginationValid(pageSize,pageNumber), HttpStatus.OK);
    }

    @PostMapping(path = "add/student")
    public ResponseEntity<Student> registerNewStudent(@RequestBody StudentRequestDTO dto) throws InterruptedException {
        return new ResponseEntity<>(studentService.addNewStudent(dto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/students/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Student deleted successfully");
        body.put("status",HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping(path = "/students/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentRequestDTO dto) throws InterruptedException {
        studentService.updateStudent(studentId, dto);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Student updated successfully");
        body.put("status",HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping(path = "students/{studentId}/courses/{courseId}")
    public ResponseEntity<Object> mapCourseToStudent(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId){
        studentService.mapCourseToStudent(studentId,courseId);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Student updated successfully");
        body.put("status",HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping(path = "students/{studentId}/courses/{courseId}")
    public ResponseEntity<Object> unmapCourseToStudent(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId){
        studentService.unmapCourseToStudent(studentId,courseId);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Student updated successfully");
        body.put("status",HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
