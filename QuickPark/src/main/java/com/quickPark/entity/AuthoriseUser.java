package com.quickPark.entity;

import lombok.Data;

@Data 
public class AuthoriseUser {

	private String email;
	private String password;
	private String role;
	
}
