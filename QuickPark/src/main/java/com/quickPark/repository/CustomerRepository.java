package com.quickPark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quickPark.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
@Query(value="delete from Customer c where c.customerId= :customerId")
@Modifying
int deleteCustomers(@Param("customerId") int customerId);
@Query("select c from Customer c")
@Modifying
List<Customer> getAllCustomers();




}
