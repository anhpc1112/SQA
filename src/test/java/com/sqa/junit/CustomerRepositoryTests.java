package com.sqa.junit;



import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.sqa.entity.Customer;
import com.sqa.entity.Payment;
import com.sqa.repo.CustomerController;
import com.sqa.repo.CustomerRepository;
import com.sqa.repo.PaymentRepository;
import com.sqa.entity.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {

	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	
	@Autowired
	private CustomerController custController;

	
	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setQuocTich("Việt Nam");
		customer.setDanToc("Kinh");
		customer.setCmnd("123456789");
		customer.setSdt("0968953283");
		customer.setDiaChiKhaiSinh("Hà Tây");
		customer.setDiaChiNhanKetQua("Hà Đông");
		
		
		Payment payment = new Payment();
//		payment.setPhuongThuc(3);
		
		customer.setPayment(payment);
		payment.setCustomer(customer);
		
		
		
		Customer customerSaved = custRepo.save(customer);
		System.out.println(customerSaved.getId());
		assertThat(customer.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void checkEmailExist() {
		String email = "phungconganh99prottabc@gmail.com";

//		System.out.println(check.getEmail());
//		System.out.println(account.getEmail());
		
//		assertThat(check.getEmail().equals(account.getEmail()));
		
		
	}
	
	@Test
	public void testAccout() {
		String email = "phungconganh99prottabc@gmail.com";
		Customer customer = custRepo.findByEmail(email);
		assertThat(email.equals(customer.getEmail()));
	}
	
	@Test
	public void testRegex() {
		String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		String dob = "07/13/1999";
		String nonResult = "notok";
		String result = "ok";
		
		String check ="";
		Matcher matcher = pattern.matcher(dob);
		if(matcher.matches() == true) {
			check = "ok";
		}else {
			check="notok";
		}
		System.out.println(check);
		assertThat(check.equals(result));
		
	}
	
	@Test 
	public void testTinhTien1() {
		Customer customer = new Customer();
		customer.setNgheNghiep("Người lao động");
		Payment payment = new Payment();
		payment.setKiHan(3);
		customer.setPayment(payment);
		payment.setCustomer(customer);
		
		Customer customerCal = custController.tinhTien(customer);
		
		double a = customerCal.getPayment().getTongTien();
		assertThat(a).isEqualByComparingTo((double) 201150);
		
		
	}
	
}
