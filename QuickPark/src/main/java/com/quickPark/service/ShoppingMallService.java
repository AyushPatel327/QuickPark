package com.quickPark.service;

import java.util.List;

import com.quickPark.entity.Block;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.entity.Slot;

public interface ShoppingMallService {
	ShoppingMall addShoppingMall(ShoppingMall m,String role);
	
	int deleteShoppingMall(int mallId);
	
	public ShoppingMall updateShoppingMall(ShoppingMall m,int mallId);
	
	List <ShoppingMall> getAllShoppingMalls();
	
    Block addBlock(Block b,int mallId);
    
    public void deleteBlock( int blockId);
    
    public List<Block> viewAllBlocks();
    
    public Slot addSlot(Slot slot,int blockId);
    
    public List<Slot> viewAllSlots();
    
    
}
