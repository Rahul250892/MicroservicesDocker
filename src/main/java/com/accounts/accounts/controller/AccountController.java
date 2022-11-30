package com.accounts.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.accounts.config.AccountsServiceConfig;
import com.accounts.accounts.model.Accounts;
import com.accounts.accounts.model.Customer;
import com.accounts.accounts.model.Properties;
import com.accounts.accounts.repository.AccountsRepository;
import com.accounts.accounts.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class AccountController {

	
	@Autowired
	private AccountsRepository accountsRepository;

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}
	
	@PostMapping("/account")
    public ResponseEntity<Accounts> openAccount(@RequestBody Accounts account) {
       
		Accounts accounts = accountsRepository.save(account);
		System.out.println(accounts);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
	
	@Autowired
	private AccountsServiceConfig accountsServiceConfig;
	
	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		Properties properties = new Properties(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(),
				accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/customer")
    public ResponseEntity<Customer> openAccountCustomer(@RequestBody Customer cust) {
       
		Customer accounts = customerRepository.save(cust);
		System.out.println(accounts);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
}
