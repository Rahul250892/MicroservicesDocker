package com.accounts.accounts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accounts.accounts.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer > {

}
