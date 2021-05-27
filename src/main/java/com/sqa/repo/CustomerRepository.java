package com.sqa.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.sqa.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Integer> {

	
//	@Query("SELECT u from customer u where u.email = :email")
//	public Customer getCustomerByEmail(@Param("email") String email);

	
//	@Query("select a.email from account a left join customer b on a.id = b.account_id")
//	public String selectEmail();
	
	
	public Customer findByEmail(String email);
	
	
	

}
