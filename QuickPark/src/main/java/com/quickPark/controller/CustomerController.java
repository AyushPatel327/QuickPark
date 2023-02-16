package com.quickPark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickPark.entity.Customer;
import com.quickPark.entity.MyBooking;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("{role}")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c, @PathVariable String role) {
		Customer customer = customerService.addCustomer(c, role);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Integer> deleteCustomers(@PathVariable("customerId") int customerId) {
		return new ResponseEntity<Integer> (customerService.deleteCustomer(customerId),HttpStatus.OK);
	}

	@PutMapping("/{customerId}")
	public  ResponseEntity<Customer> updateCustomers(@RequestBody Customer c, @PathVariable int customerId) {
		return new ResponseEntity<Customer>( customerService.updateCustomer(c, customerId),HttpStatus.OK);
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>>  getAllCustomers() {
		return new ResponseEntity<List<Customer>>( customerService.getAllCustomers(),HttpStatus.OK);
	}

	@GetMapping("/shoppingmalls")
	public ResponseEntity<List<ShoppingMall>> findAllShoppingMalls() {
		return new ResponseEntity<List<ShoppingMall>>( customerService.findAllShoppingMall(),HttpStatus.OK);
	}
	@PostMapping("/booking/{customerId}/{mallId}/{blockId}/{slotId}")
	public ResponseEntity<MyBooking> addBooking(@PathVariable int customerId,@PathVariable int mallId,@PathVariable int blockId,@PathVariable int slotId){
		;
		return new ResponseEntity<MyBooking> (customerService.addBooking(customerId, mallId, blockId, slotId), HttpStatus.OK);
	}

}
