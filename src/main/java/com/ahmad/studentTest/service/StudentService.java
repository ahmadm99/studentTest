package com.ahmad.studentTest.service;


import com.ahmad.studentTest.DTO.StudentDTO;
import com.ahmad.studentTest.DTO.StudentRequestDTO;
import com.ahmad.studentTest.exception.*;
import com.ahmad.studentTest.model.*;
import com.ahmad.studentTest.repository.CourseRepository;
import com.ahmad.studentTest.repository.StudentRepository;
//import com.mysql.cj.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    private CourseRepository courseRepository;


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(StudentRequestDTO dto) throws InterruptedException {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(dto.getEmail());
        if (studentEmail.isPresent()) {
            throw new StudentAlreadyExistsException(dto.getEmail());
        }
//        TimeUnit.SECONDS.sleep(5);
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
        Student student = studentRepository.findById(studentId).get();
        student.setName(dto.getName());
        student.setDob(dto.getDob());
        student.setEmail(dto.getEmail());
        student.setId(studentId);
        if (dto.getType()) {
            student.setAmount((short) 500);
            studentRepository.changeType(studentId, "Special");
        } else {
            student.setAmount((short) 1000);
            studentRepository.changeType(studentId, "Default");
        }
    }

    public List<StudentDTO> getStudentDTO() {
        List<StudentDTO> dto = studentRepository.findAll().stream().map(this::mapStudentDTO).collect(Collectors.toList());
        return dto;
    }

    private StudentDTO mapStudentDTO(Object student) {
        StudentDTO dto = new StudentDTO();
        if (student instanceof SpecialStudent) {
            SpecialStudent specialStudent = (SpecialStudent) student;
            dto.setId(specialStudent.getId());
            dto.setName(specialStudent.getName());
            dto.setDob(specialStudent.getDob());
            dto.setEmail(specialStudent.getEmail());
            dto.setAmount(specialStudent.getAmount());
            dto.setType(true);
        } else {
            DefaultStudent defaultStudent = (DefaultStudent) student;
            dto.setId(defaultStudent.getId());
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

    public Page<Student> getPaginationValid(int pageSize, int pageNumber) {
        if (pageSize > 100 || pageSize <= 0) {
            throw new InvalidPageSizeException(pageSize);
        }
        if(pageNumber > getMaxPageNumber(pageSize)) {
            throw new ExceededMaxPageNumberException(pageNumber, getMaxPageNumber(pageSize));
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return studentRepository.findAll(pageable);

    }

    public int getMaxPageNumber(int pageSize){
        return (int)studentRepository.count()/pageSize;
    }

    public void mapCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).get();
        if(student == null){
            throw new StudentNotFoundException(studentId);
        }
        Course course = courseRepository.findById(courseId).get();
        if(course == null){
            throw new CourseNotFoundException(courseId);
        }
        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public void unmapCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).get();
        if(student == null){
            throw new StudentNotFoundException(studentId);
        }
        Course course = courseRepository.findById(courseId).get();
        if(course == null){
            throw new CourseNotFoundException(courseId);
        }
        student.getCourses().remove(course);
        studentRepository.save(student);
    }
}
//access pageable object added by pagination to modify last available page instead of accessing database
//switch to url parameters
//return class rather than responseentity.put

//request should always return json
//switch url to convention

//add login for admin with user and password

