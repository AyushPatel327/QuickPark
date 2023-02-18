package com.quickPark.service;

import java.util.List;

import com.quickPark.entity.Customer;
import com.quickPark.entity.MyBooking;
import com.quickPark.entity.ShoppingMall;

public interface CustomerService {
	

	Customer addCustomer(Customer c, String role);
	int deleteCustomer( int customerId);
	public Customer updateCustomer(Customer c,int customerId);
	List <Customer> getAllCustomers();
	List <ShoppingMall> findAllShoppingMall();
	//below one in progress
	public MyBooking addBooking(int customerId,int mallId,int blockId,int slotId,int vehicleType, String vehicleNo);
 
	  List<MyBooking> viewAllMyBookings();
}
