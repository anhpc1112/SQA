package com.sqa.repo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.Errors;

import com.sqa.entity.Customer;
import com.sqa.entity.Payment;
import com.sqa.security.CustomerDetails;

import org.springframework.ui.Model;

@Controller
public class CustomerController {
	
	private double luongCS = 1490000;
	private double hoTrohssv = 0.3;
	private double hoTroCanNgheo = 0.7;
	private double mucChung = 0.045;
	
	private String HSSV = "Học sinh - sinh viên";
	private String canNgheo = "Hộ cận nghèo";
	private String nguoiLaoDong = "Người lao động";
	

	@Autowired
	private CustomerRepository customerRepo;
	


	
	@PostMapping("/addCustomer")
	public String add(@ModelAttribute("customer") Customer customer,@AuthenticationPrincipal CustomerDetails loggedCustomer,RedirectAttributes redirAttrs) {
		
	     
		
		String email = loggedCustomer.getUsername();
		Customer customerHienTai = customerRepo.findByEmail(email);
		
//		System.out.println(customer.getPayment().getKiHan());	
//		System.out.println(email);
//		System.out.println(customer.getEmail());
//		System.out.println(customer.getDanToc());
		
		Customer customer_config = updateCustomer(customerHienTai, customer);
		String regexDob = "(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}";
	
		
		String regexSdt = "^0[123456789]{1}\\d{8}$";
		
		String regexCmt = "^[0-9]{12}$";
		if(customer_config.getDob().matches(regexDob) == false) {
			redirAttrs.addFlashAttribute("errorDob", "Đăng kí thất bại!");
			return "redirect:/dangki";
		}
		else if(customer_config.getSdt().matches(regexSdt) == false) {
			redirAttrs.addFlashAttribute("errorSdt", "Đăng kí thất bại!");
			return "redirect:/dangki";
			
		}
		else if(customer_config.getCmnd().matches(regexCmt) == false) {
			redirAttrs.addFlashAttribute("errorCmt", "Đăng kí thất bại!");
			return "redirect:/dangki";
		}
		else {
		
		
		
		Customer customer_counted = tinhTien(customer_config);	
		System.out.println(customer_config.getPayment().getTongTien());
		
		redirAttrs.addFlashAttribute("success", "Đăng kí thành công!");
		customerRepo.save(customer_counted);
		

		
		return "redirect:/dangki";
	    }
	}
	
	
	private Customer updateCustomer(Customer customerHienTai,Customer customer) {
		customerHienTai.setHoTen(customer.getHoTen());
		customerHienTai.setCmnd(customer.getCmnd());
		customerHienTai.setGioiTinh(customer.getGioiTinh());
		customerHienTai.setDob(customer.getDob());
		customerHienTai.setSdt(customer.getSdt());
		customerHienTai.setQuocTich(customer.getQuocTich());
		customerHienTai.setDanToc(customer.getDanToc());
		customerHienTai.setDiaChiKhaiSinh(customer.getDiaChiKhaiSinh());
		customerHienTai.setDiaChiNhanKetQua(customer.getDiaChiKhaiSinh());
		Payment t = customer.getPayment();
		customerHienTai.setPayment(t);
//		customerHienTai.getPayment().setKiHan(customer.getPayment().getKiHan());
		customerHienTai.setNgheNghiep(customer.getNgheNghiep());
		
		return customerHienTai;
	}
	
	
	
	
	@PostMapping("/register/save")
	public String register(@ModelAttribute("customer") Customer customer,RedirectAttributes redirAttrs) {
		Customer account = customerRepo.findByEmail(customer.getEmail());
		if(account != null && account.getEmail().equals(customer.getEmail())) {
//			String notOK = "notOK";
//			model.addAttribute("error", "notOK");
			redirAttrs.addFlashAttribute("error", "Tài khoản đã tồn tại!");
			return "redirect:/register";
		}else {
//			String OK = "OK";
//			model.addAttribute("error","OK");
			redirAttrs.addFlashAttribute("success", "Đăng kí thành công!");
			String password = customer.getPassword();
			customer.setPassword(new BCryptPasswordEncoder().encode(password));
	
			customerRepo.save(customer);
			return "redirect:/register";
		}
		
		
		
		
	}
	
	
	public Customer tinhTien(Customer customer) {
		NumberFormat formatter = new DecimalFormat( "#0"); 
		int kiHan = customer.getPayment().getKiHan();
		if (customer.getNgheNghiep().equals(HSSV)) {
			double tongTien = kiHan * luongCS * (1-hoTrohssv) * mucChung;
			String tien_fixed = formatter.format(tongTien);
			double tongTienCuoi = Double.parseDouble(tien_fixed);
			customer.getPayment().setTongTien(tongTienCuoi);
				
			
		}
		else if(customer.getNgheNghiep().equals(canNgheo)) {
			double tongTien = kiHan * luongCS * (1-hoTroCanNgheo) * mucChung;
			String tien_fixed = formatter.format(tongTien);
			double tongTienCuoi = Double.parseDouble(tien_fixed);
			customer.getPayment().setTongTien(tongTienCuoi);
	
		}
		else if(customer.getNgheNghiep().equals(nguoiLaoDong)) {
			double tongTien = kiHan * luongCS * mucChung;
			String tien_fixed = formatter.format(tongTien);
			double tongTienCuoi = Double.parseDouble(tien_fixed);
			customer.getPayment().setTongTien(tongTienCuoi);
		}
		return customer;
		
		
	}
	
	
	
	
	
	
	

}
