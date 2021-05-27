package com.sqa.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {
	
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	

}
