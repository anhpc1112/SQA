package com.sqa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sqa.entity.Customer;

import com.sqa.repo.CustomerRepository;

public class CustomerServiceDetails implements UserDetailsService {

	
	@Autowired
	private CustomerRepository customerRepo;
	

	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Customer customerByEmail = customerRepo.getCustomerByEmail(email);
//		if(customerByEmail != null) {
//			return new CustomerDetails(customerByEmail);
//		}
//		throw new UsernameNotFoundException("Tài khoản này không tồn tại!");
		Customer customer = customerRepo.findByEmail(email);
		if(customer != null) {
			return new CustomerDetails(customer);
		}else {
			throw new UsernameNotFoundException("KHONG TIM THAY TAI KHOAN NHA!");
		}
		
	}

}
