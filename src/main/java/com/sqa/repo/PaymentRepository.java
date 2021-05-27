package com.sqa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sqa.entity.Customer;
import com.sqa.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Integer> {

}
