package com.quickPark.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.quickPark.exceptions.CustomerNotPresentException;
import com.quickPark.repository.CustomerRepository;

/**
 * @author aypatel
 *
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	

	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private CustomerRepository customerRepository;
	
	@BeforeEach
	void setup(){
		 this.customerServiceImpl = new CustomerServiceImpl(this.customerRepository);
	}
	
	

	/*
	 * @Mock private LoginRepository loginRepository;
	 */

	//private Customer customer;

	/*
	 * @BeforeEach void customerIntializerSetup() throws Exception {
	 * this.customerServiceImpl = new CustomerServiceImpl(this.customerRepository);
	 * 
	 * 
	 * Login login =
	 * Login.builder().email("raghu123@gmail.com").password("Raghu@123").role(
	 * "customer").build(); Customer customer =
	 * Customer.builder().customerId(24).customerEmail("raghu123@gmail.com")
	 * .customerMobileNo(76104577).customerName("Raghu").password("Raghu@123").login
	 * (login).build();
	 * 
	 * Mockito.when(customerRepository.save(customer)).thenReturn(customer);
	 * Mockito.when(customerRepository.findAll()).thenReturn(Collections.
	 * singletonList(customer));
	 * 
	 * }
	 */

	/*
	 * @Test public void addCustomerTestCase() throws Exception {
	 * 
	 * Customer customer =
	 * Customer.builder().customerId(24).customerEmail("raghu123@gmail.com")
	 * .customerMobileNo(76104577).customerName("Raghu").password("Raghu@123").build
	 * ();
	 * 
	 * Customer customer2 = customerServiceImpl.addCustomer(customer);
	 * 
	 * assertEquals(customer, customer2);
	 * 
	 * // Customer fetchedCustomer = customerRepository.findById(24).get(); //
	 * verify(customerRepository).findById(24); // assertEquals(fetchedCustomer,
	 * customer); }
	 */

	/*
	 * @Test public void getAllCustomers() throws CustomerNotPresentException {
	 * 
	 * Customer customer =
	 * Customer.builder().customerEmail("raghu123@gmail.com").customerMobileNo(
	 * 76104577) .customerName("Raghu").password("Raghu@123").build();
	 * 
	 * List<Customer> fetchCustomers = customerService.getAllCustomers();
	 * 
	 * assertEquals(fetchCustomers, Collections.singletonList(customer)); }
	 */

	@Test
	public void getAllCustomers() throws CustomerNotPresentException {
		/*
		 * Login login =
		 * Login.builder().email("raghu123@gmail.com").password("Raghu@123").role(
		 * "customer").build();
		 * 
		 * Customer customer =
		 * Customer.builder().customerId(27).customerEmail("ravi@gmail.com")
		 * .customerMobileNo(9767666877l).customerName("Ravi").password("ravi@123").
		 * login(login) .build();
		 */
		// Mockito.when(customerRepository.save(customer)).thenReturn(customer);

		/*
		 * Login login =
		 * Login.builder().email("raghu123@gmail.com").password("Raghu@123").role(
		 * "customer").build(); Customer customer =
		 * Customer.builder().customerId(24).customerEmail("raghu123@gmail.com")
		 * .customerMobileNo(76104577).customerName("Raghu").password("Raghu@123").login
		 * (login).build();
		 * 
		 * List<Customer> customerList = customerServiceImpl.getAllCustomers();
		 * assertEquals(customerList, Collections.singletonList(customer));
		 */
		
		customerServiceImpl.getAllCustomers();
		verify(customerRepository).findAll();
		
	}
	
//	@Test
//	public void deleteCustomer () throws CustomerNotPresentException{
//		
//		customerServiceImpl.deleteCustomer(7);
//		
//		verify(customerRepository).deleteById(7);
//	}

}
