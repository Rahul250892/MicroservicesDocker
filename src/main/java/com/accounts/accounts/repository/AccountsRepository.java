package com.accounts.accounts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accounts.accounts.model.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long>  {
	
	Accounts findByCustomerId(int customerId);

}
