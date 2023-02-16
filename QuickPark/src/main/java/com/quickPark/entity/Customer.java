package com.quickPark.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;

	private String customerName;

	private String customerEmail;

	private long customerMobileNo;
	
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loginId")
	@JsonIgnore
	Login login;

	/*
	 * @OneToMany
	 * 
	 * @JsonIgnore
	 * 
	 * @JoinColumn(name = "bookingId") List<MyBooking> bookings;
	 */



}
