package com.quickPark.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.quickPark.repository.ShoppingMallRepository;

@ExtendWith(MockitoExtension.class)
public class ShoppingMallServiceTest {


	private ShoppingMallServiceImpl mallServiceImpl;
	
	@Mock
	private ShoppingMallRepository mallRepository;
	
	
	@BeforeEach
	void setup(){
		 this.mallServiceImpl = new ShoppingMallServiceImpl(this.mallRepository);
	}
	
	@Test
	public void getAllMall() {
		
		mallServiceImpl.getAllShoppingMalls();
		verify(mallRepository).findAll();
	}
}
