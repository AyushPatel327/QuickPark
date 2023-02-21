package com.quickPark.service;

import java.util.List;

import com.quickPark.entity.AuthoriseUser;
import com.quickPark.entity.Block;
import com.quickPark.entity.Customer;
import com.quickPark.entity.MyBooking;
import com.quickPark.entity.Payment;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.entity.Slot;

public interface CustomerService {
	
	

	Customer addCustomer(Customer c);

	void deleteCustomer(int customerId);

	public Customer updateCustomer(Customer c, int customerId);

	List<Customer> getAllCustomers();

	List<ShoppingMall> findAllShoppingMall();

	// below one in progress
	public MyBooking addBooking(int customerId, int mallId, int blockId, int slotId, int vehicleType, String vehicleNo);

	public Payment checkout(int customerId);
	
	List<MyBooking> viewAllMyBookings();

	public List<MyBooking> viewBookingsByCustomerId(int customerId);

	public String authoriseCustomer(AuthoriseUser user);
	
	public List<Block> viewAllBlocksByShoppingMallId(int shoppingMallId);
	
	public List<Slot> viewAllSlotsByBlockId(int blockId);
}
