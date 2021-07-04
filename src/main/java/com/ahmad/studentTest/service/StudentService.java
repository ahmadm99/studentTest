package com.ahmad.studentTest.service;


import com.ahmad.studentTest.DTO.StudentPaymentDTO;
import com.ahmad.studentTest.model.Payment;
import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.PaymentRepository;
import com.ahmad.studentTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private Student student = new Student();
    private Payment payment = new Payment();


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public void addNewStudent(StudentPaymentDTO dto) {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(dto.getEmail());
        if(studentEmail.isPresent()){
            System.out.println("Email is already taken");
        }
        else{
            if(dto.getType()==false && dto.getAmount()!=1000 || dto.getType()==true && dto.getAmount()!=500){
                System.out.println("Invalid Amount");
                return;
            }
            student.setName(dto.getName());
            student.setDob(dto.getDob());
            student.setEmail(dto.getEmail());
            payment.setStudent(student);
            payment.setAmount(dto.getAmount());
            payment.setType(dto.getType());
            studentRepository.save(student);
            paymentRepository.save(payment);
        }
    }

    public void deleteStudent(Long studentId) {
        if(paymentRepository.existsById(studentId)){
            paymentRepository.deleteById(studentId);
        }
        else{
            System.out.println("No student found with given id");
        }
    }
    @Transactional
    public void updateStudent(Long studentId, StudentPaymentDTO dto){
        if(!studentRepository.findById(studentId).isPresent()){
            System.out.println("Id not found");
            return;
        }
        if(dto.getType()==false && dto.getAmount()!=1000 || dto.getType()==true && dto.getAmount()!=500) {
            System.out.println("Invalid Amount");
            return;
        }
        Student student = studentRepository.findById(studentId).get();
        Payment payment = paymentRepository.findById(studentId).get();
        student.setName(dto.getName());
        student.setDob(dto.getDob());
        student.setEmail(dto.getEmail());
        payment.setType(dto.getType());
        payment.setAmount(dto.getAmount());
    }

    public List<StudentPaymentDTO> getDTO() {
        List<StudentPaymentDTO> dto1 = studentRepository.findAll().stream().map(this::mapStudentDTO).collect(Collectors.toList());
        List<StudentPaymentDTO> dto2 = paymentRepository.findAll().stream().map(this::mapPaymentDTO).collect(Collectors.toList());
        List<StudentPaymentDTO> dto = dto1;
        for(int i = 0; i<dto.size();i++){
            dto.get(i).setAmount(dto2.get(i).getAmount());
            dto.get(i).setType(dto2.get(i).getType());
        }
        return dto;
    }

    private StudentPaymentDTO mapStudentDTO(Student student){
        StudentPaymentDTO dto = new StudentPaymentDTO();
        dto.setName(student.getName());
        dto.setDob(student.getDob());
        dto.setEmail(student.getEmail());
        return dto;
    }
    private StudentPaymentDTO mapPaymentDTO(Payment payment){
        StudentPaymentDTO dto = new StudentPaymentDTO();
        dto.setType(payment.getType());
        dto.setAmount(payment.getAmount());
        return dto;
    }
}

