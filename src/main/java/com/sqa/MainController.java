package com.sqa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sqa.entity.Customer;
import com.sqa.entity.Payment;
import com.sqa.repo.CustomerRepository;
import com.sqa.security.CustomerDetails;

@Controller
public class MainController {
	
	
	@Autowired
	private CustomerRepository custRepo;
	
	@GetMapping("")
	public String getHome() {
		return "index";
	}
	
	@GetMapping("/dangki")
	public String getDangKi(Model model,@AuthenticationPrincipal CustomerDetails loggedCustomer) {
		
		String email = loggedCustomer.getUsername();
		
		
		Customer customer = custRepo.findByEmail(email);
//		Payment payment = new Payment();
//		customer.setPayment(payment);
//		payment.setCustomer(customer);
		model.addAttribute("customer",customer);
//		model.addAttribute("payment",payment);
		return "dangki";
	}
	
	@GetMapping("/baohiem")
	public String getBaoHiem(Model model,@AuthenticationPrincipal CustomerDetails loggedCustomer) {
		
		String email = loggedCustomer.getUsername();
		
		
		Customer customer = custRepo.findByEmail(email);
//		Payment payment = new Payment();
//		customer.setPayment(payment);
//		payment.setCustomer(customer);
		model.addAttribute("customer",customer);
//		model.addAttribute("payment",payment);
		return "baohiem";
	}
	
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("customer",new Customer());
		return "register";
	}
	
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	
	@GetMapping("/dongbaohiem")
	public String getDongBaoHiem(Model model) {
		model.addAttribute("customer",new Customer());
		return "dongbaohiem";
	}
	
}
