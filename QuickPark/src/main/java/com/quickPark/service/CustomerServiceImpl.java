package com.quickPark.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quickPark.entity.Block;
import com.quickPark.entity.Customer;
import com.quickPark.entity.Login;
import com.quickPark.entity.MyBooking;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.entity.Slot;
import com.quickPark.exceptions.CustomerNotPresentException;
import com.quickPark.exceptions.EmptyFieldException;
import com.quickPark.exceptions.MallNotFoundException;
import com.quickPark.exceptions.NoSuchBlockExistsException;
import com.quickPark.exceptions.SlotNotAvailableException;
import com.quickPark.repository.BlockRepository;
import com.quickPark.repository.CustomerRepository;
import com.quickPark.repository.LoginRepository;
import com.quickPark.repository.MyBookingRepository;
import com.quickPark.repository.ShoppingMallRepository;
import com.quickPark.repository.SlotRepository;

@Component
public class CustomerServiceImpl implements CustomerService {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private MyBookingRepository myBookingRepository;

	@Autowired
	private ShoppingMallRepository mallRepository;

	@Autowired
	private BlockRepository blockRepository;

	@Autowired
	private SlotRepository slotRepository;

	public Customer addCustomer(Customer c, String role) {

		if (c.getPassword() == "" || c.getCustomerEmail() == "") {
			throw new EmptyFieldException("Enter the valid data");
		} else {
			Customer customer = customerRepository.save(c);

			Login login = new Login();
			login.setEmail(c.getCustomerEmail());
			login.setPassword(c.getPassword());
			login.setRole(role);
			loginRepository.save(login);
			customer.setLogin(login);

			return customerRepository.save(customer);
		}

	}

	@Override
	public int deleteCustomer(int customerId) {

		if (customerRepository.findById(customerId).isEmpty()) {
			throw new CustomerNotPresentException("Can not find the customer");
		}

		else {

			int delete = customerRepository.deleteCustomers(customerId);
			if (delete != 0) {

				return delete;

			}
			return delete;
		}

	}

	@Override
	public Customer updateCustomer(Customer updatedCustomer, int customerId) {
		Optional<Customer> fetchedCustomer = customerRepository.findById(customerId);

		Login login = new Login();

		if (fetchedCustomer.isEmpty() || fetchedCustomer == null) {
			throw new CustomerNotPresentException("Not Present");
		} else {
//			fetchedCustomer.get().setCustomerId(customerId);
//			fetchedCustomer.get().setCustomerName(updatedCustomer.getCustomerName());
//			fetchedCustomer.get().setCustomerEmail(updatedCustomer.getCustomerEmail());
//			fetchedCustomer.get().setCustomerMobileNo(updatedCustomer.getCustomerMobileNo());
//			fetchedCustomer.get().setPassword(updatedCustomer.getPassword());

			fetchedCustomer.get().setCustomerEmail(updatedCustomer.getCustomerEmail());
			login.setEmail(updatedCustomer.getCustomerEmail());

			if (updatedCustomer.getPassword() == "") {
				fetchedCustomer.get().setPassword(fetchedCustomer.get().getPassword());
				login.setPassword(fetchedCustomer.get().getPassword());

			} else {
				fetchedCustomer.get().setPassword(updatedCustomer.getPassword());
				login.setPassword(updatedCustomer.getPassword());
			}
			if (updatedCustomer.getCustomerMobileNo() == 0) {
				fetchedCustomer.get().setCustomerMobileNo(fetchedCustomer.get().getCustomerMobileNo());
			} else {
				fetchedCustomer.get().setCustomerMobileNo(updatedCustomer.getCustomerMobileNo());
			}
			if (updatedCustomer.getCustomerName() == "") {
				fetchedCustomer.get().setCustomerName(fetchedCustomer.get().getCustomerName());
			} else {
				fetchedCustomer.get().setCustomerName(updatedCustomer.getCustomerName());
			}

			login.setRole(loginRepository
					.findById(customerRepository.findById(customerId).get().getLogin().getLoginId()).get().getRole());
			loginRepository.save(login);

			fetchedCustomer.get().setLogin(login);
			fetchedCustomer.get().setCustomerId(updatedCustomer.getCustomerId());
		}

		return customerRepository.save(fetchedCustomer.get());

	}

	@Override
	public List<Customer> getAllCustomers() {
		if (customerRepository.getAllCustomers().isEmpty()) {
			throw new CustomerNotPresentException("No customers found");
		}
		return customerRepository.getAllCustomers();
	}

	public List<ShoppingMall> findAllShoppingMall() {
		// List<ShoppingMall> list = em.createQuery("select m from ShoppingMall
		// m").getResultList();
		
		if (mallRepository.findAll().isEmpty()) {
			throw new MallNotFoundException("No malls found");
		} else
			return mallRepository.findAll();
	}

	// this method in progress
	@Override
	public MyBooking addBooking(int customerId, int mallId, int blockId, int slotId,int vehicleType, String vehicleNo) {

		Optional<Customer> customer = customerRepository.findById(customerId);
		Optional<ShoppingMall> mall = mallRepository.findById(mallId);
		
		
		if (customer.isEmpty() && mall.isEmpty()) {
			throw new CustomerNotPresentException("Not found");
		} else {

			
			MyBooking myBooking = new MyBooking();
			myBooking.setCustomer(customer.get());
			myBooking.setSlotDate(LocalDate.now());
			myBooking.setStartTime(LocalTime.now());
			myBooking.setVehicleNo(vehicleNo);
			myBooking.setVehicleType(vehicleType);

			// first find selected block
			// List<Block> blockList = blockRepository.findAll();

			// validate if present or not

			// Setting Slot
			Slot selectedSlot;
			if (slotRepository.findById(slotId).isEmpty()) {
				throw new SlotNotAvailableException("Slot not available");
			} else {
				selectedSlot = slotRepository.findById(slotId).get();
			}

			selectedSlot.setSlotNumber(selectedSlot.getSlotNumber() - 1);
			slotRepository.save(selectedSlot);

			List<Slot> slots = new ArrayList<>();
			slots.add(selectedSlot);

			// Setting Block
			Block selectedBlock;
			if (blockRepository.findById(blockId).isEmpty()) {
				throw new NoSuchBlockExistsException("Block not present");
			} else {
				selectedBlock = blockRepository.findById(blockId).get();
			}

			List<Block> blockList = new ArrayList<>();
			blockList.add(selectedBlock);

			blockRepository.save(selectedBlock);

			mall.get().setBlocks(blockList);
			mallRepository.save(mall.get());

			/*
			 * List<Slot> slotsAvailble ; for(Block block: blockList) {
			 * if(block.getMall().getMallId() == mall.get().getMallId()) { slotsAvailble =
			 * block.getSlots();
			 * 
			 * //handle exceptions for not found selectedBlock =
			 * blockRepository.findById(block.getBlockId()).get();
			 * 
			 * selectedBlock.get } }
			 */

			myBooking.setMall(mall.get());
			myBooking.setEndTime(LocalTime.now().plusHours(1));
			myBooking.setPayment("50");
			myBooking.setStatus("Booked");
			myBooking.setTotalprice(
					50 * (java.time.Duration.between(LocalTime.now(), LocalTime.now().plusHours(1)).toHours()));

			// Customer booking list
			List<MyBooking> customerBookings = new ArrayList<>();
			customerBookings.add(myBooking);

			// set that booking into mybooking as well in Mybooking

			/*
			 * customer.get().setBookings(customerBookings);
			 * customerRepository.save(customer.get());
			 */

			myBookingRepository.save(myBooking);

			customerRepository.saveAndFlush(customer.get());

			return myBooking;
		}

	}
	// this method in progress
	
	

@Override
	public List<MyBooking> viewAllMyBookings(){
		List<MyBooking> book=myBookingRepository.findAll();
		return book;
		
		
	}

}
