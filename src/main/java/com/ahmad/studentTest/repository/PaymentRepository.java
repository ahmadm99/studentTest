package com.ahmad.studentTest.repository;

import com.ahmad.studentTest.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
