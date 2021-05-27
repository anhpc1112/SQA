package com.sqa.autotest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.BeforeTest;

import org.openqa.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
class TestSelanium {
	
	WebDriver driver;

	@BeforeTest
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PESI\\Desktop\\Final\\chromedriver.exe");
		driver = new ChromeDriver();
		
		
	}
	
	@Test
	void testLoginSucess() {
		
		initTest();
		driver.get("http://localhost:8081/SQA/register");
		WebElement usernameRe = driver.findElement(By.name("email"));
		usernameRe.sendKeys("123@gmail.com");
		WebElement passwordRe = driver.findElement(By.name("password"));
		passwordRe.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		
		driver.get("http://localhost:8081/SQA/login");
//		driver.get("http://localhost:8081/SQA/login");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("123@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		String title = driver.getTitle();
		String expected = "123@gmail.com";
		
		System.out.println(title);
		
		String email = driver.findElement(By.xpath("/html/body/header/div/li/a")).getText();
		System.out.println(email);
		System.out.println(title);
		
		driver.close();
		
		assertEquals(expected, email);
		
		
	}
	@Test
	void testLoginFail() {
		initTest();
		driver.get("http://localhost:8081/SQA/login");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("12345678@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		String title = driver.getTitle();
		String expected = "Login Page";
		System.out.println(title);
		
		driver.close();
		
		assertEquals(expected, title);
	}

	@Test
	void testRegisterFail() {
		
		//Trùng email
		initTest();
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("conganh@gmail.com");
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		
		driver.findElement(By.id("btnRegister")).click();
		
		String status = driver.findElement(By.xpath("/html/body/div/div/div[2]")).getText();
		String expected = "Đăng kí thất bại";
		System.out.println(status);
		driver.close();
		assertEquals(expected, status);
	}
	
	
	@Test
	void testRegisterSuccess() {
		
		//Trùng email
		initTest();
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("91@gmail.com");
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		
		driver.findElement(By.id("btnRegister")).click();
		
		String status = driver.findElement(By.xpath("/html/body/div/div/div[2]")).getText();
		String expected = "Đăng kí thành công";
		System.out.println(status);
		driver.close();
		assertEquals(expected, status);
	}
	
	
	@Test
	void testInformationSuccess() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk1@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk1@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("3 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Học sinh - sinh viên");
		
		
		driver.findElement(By.id("submit")).click();
		
		String status = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]")).getText();
		System.out.println(status);
		String expected = "Khai báo thành công";
		
		driver.close();
		
		assertEquals(expected, status);
		
		
	}
	
	@Test
	void testInformationFail1() {
		//Sai dinh dang ngay thang nam
		
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk3@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk3@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999abc");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("3 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Học sinh - sinh viên");
		
		
		driver.findElement(By.id("submit")).click();
		
		String status = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]")).getText();
		
		System.out.println(status);
		String expected = "Sai định dạng ngày sinh";
		
		driver.close();
		
		assertEquals(expected, status);
		
		
	}
	
	@Test
	void testInformationFail2() {
		//Sai sdt
		
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk3@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk3@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283abc");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("3 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Học sinh - sinh viên");
		
		
		driver.findElement(By.id("submit")).click();
		
		String status = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]")).getText();
		
		System.out.println(status);
		String expected = "Sai định dạng số điện thoại";
		
		driver.close();
		
		assertEquals(expected, status);
		
		
	}
	
	@Test
	void testInformationFail3() {
		//Sai sdt
		
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk3@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk3@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111a"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("3 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Học sinh - sinh viên");
		
		
		driver.findElement(By.id("submit")).click();
		
		String status = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]")).getText();
		
		System.out.println(status);
		String expected = "Sai định dạng số chứng minh thư";
		
		driver.close();
		
		assertEquals(expected, status);
		
		
	}
	@Test
	void testInformationAlready() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk1@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk1@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		
		
		String status = driver.findElement(By.xpath("//*[@id=\"khaiBaoForm\"]/h2")).getText();
		System.out.println(status);
		String expected = "Bạn đã khai báo thông tin, xin cảm ơn!";
//		
//		driver.close();
		
		assertEquals(expected, status);
		
		
	}
	
	@Test
	void testTinhToan1() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk8@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk8@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("3 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Học sinh - sinh viên");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "140805.0";
//		
		assertEquals(expected, money);
		
		
	}
	
	@Test
	void testTinhToan2() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk9@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk9@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("3 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Người lao động");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "201150.0";
//		
		assertEquals(expected, money);
		
		
	}
	
	@Test
	void testTinhToan3() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk10@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk10@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("3 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Hộ cận nghèo");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "60345.0";
//		
		assertEquals(expected, money);
		
		
	}
	
	@Test
	void testTinhToan4() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk11@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk11@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("6 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Người lao động");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "402300.0";
//		
		assertEquals(expected, money);
		
		
	}
	
	@Test
	void testTinhToan5() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk12@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk12@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("1 năm");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Người lao động");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "804600.0";
//		
		assertEquals(expected, money);
		
		
	}
	
	
	@Test
	void testTinhToan6() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk13@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk13@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("6 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Học sinh - sinh viên");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "281610.0";
//		
		assertEquals(expected, money);
		
		
	}
	
	
	@Test
	void testTinhToan7() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk15@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk15@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("1 năm");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Học sinh - sinh viên");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "563220.0";
//		
		assertEquals(expected, money);
		
		
	}
	
	
	@Test
	void testTinhToan8() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk16@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk16@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("6 tháng");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Hộ cận nghèo");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "120690.0";
//		
		assertEquals(expected, money);
		
		
	}
	
	@Test
	void testTinhToan9() {
		initTest();
		//Register
		driver.get("http://localhost:8081/SQA/register");
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("kkk17@gmail.com");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		driver.findElement(By.id("btnRegister")).click();
		
		
		//Login
		driver.get("http://localhost:8081/SQA/login");
		WebElement usernameLogin = driver.findElement(By.name("email"));
		usernameLogin.sendKeys("kkk17@gmail.com");
		
		WebElement passwordLogin = driver.findElement(By.name("password"));
		passwordLogin.sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
		
		
		//Khai bao tt
		driver.get("http://localhost:8081/SQA/dangki");
		WebElement hoVaTen = driver.findElement(By.name("hoTen"));
		hoVaTen.sendKeys("Phùng Công Anh");
		
		WebElement cmnd = driver.findElement(By.name("cmnd"));
		cmnd.sendKeys(("123456789111"));
		
	
		Select chonGioiTinh = new Select(driver.findElement(By.name("gioiTinh")));
		chonGioiTinh.selectByVisibleText("Nam");
		
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("11/12/1999");
		
		WebElement sdt = driver.findElement(By.name("sdt"));
		sdt.sendKeys("0965953283");
		
		WebElement quocTich = driver.findElement(By.name("quocTich"));
		quocTich.sendKeys("Việt Nam");
		
		WebElement danToc = driver.findElement(By.name("danToc"));
		danToc.sendKeys("Kinh");
		
		WebElement diaChiKhaiSinh = driver.findElement(By.name("diaChiKhaiSinh"));
		diaChiKhaiSinh.sendKeys("Ba Vì - Hà Nội");
		
		
		WebElement diaChiNhanKetQua = driver.findElement(By.name("diaChiNhanKetQua"));
		diaChiNhanKetQua.sendKeys("34 Đại An- Hà Đông - Hà Nội");
		
		
		Select phuongThuc = new Select(driver.findElement(By.name("payment.kiHan")));
		phuongThuc.selectByVisibleText("1 năm");
		
		
		Select ngheNghiep = new Select(driver.findElement(By.name("ngheNghiep")));
		ngheNghiep.selectByVisibleText("Hộ cận nghèo");
		
		
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();

		
		driver.get("http://localhost:8081/SQA/baohiem");
		
		String money = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[12]/div[2]/div/input")).getAttribute("value");
		
		System.out.println(money);
		
		String expected = "241380.0";
//		
		driver.close();
		assertEquals(expected, money);
		
		
	}
	

	
	
	
}
