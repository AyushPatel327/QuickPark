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

import com.quickPark.entity.Block;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.entity.Slot;
import com.quickPark.service.ShoppingMallService;

@RestController
@RequestMapping("/shoppingMall")
public class ShoppingMallController {

	@Autowired
	private ShoppingMallService mallService;

	@PostMapping("/addMall/{role}")
	public ResponseEntity<ShoppingMall> addShoppingMall(@RequestBody ShoppingMall mall, @PathVariable String role) {
		return new ResponseEntity<ShoppingMall>( mallService.addShoppingMall(mall, role),HttpStatus.OK);
	}

	@DeleteMapping("/shoppingMall/{mallId}")
	public ResponseEntity<Integer> deleteShoppingMall(@PathVariable("mallId") int mallId) {
		return new ResponseEntity<Integer>( mallService.deleteShoppingMall(mallId),HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateShoppingMall/{mallId}")
	public ResponseEntity<ShoppingMall> updateShoppingMall(@RequestBody ShoppingMall m, @PathVariable int mallId) {
		return  new ResponseEntity<ShoppingMall>( mallService.updateShoppingMall(m, mallId),HttpStatus.CREATED);
	}

	@GetMapping("/getAllShoppingMalls")
	public ResponseEntity<List<ShoppingMall>> getAllShoppingMalls() {
		return new ResponseEntity<List<ShoppingMall>>( mallService.getAllShoppingMalls(),HttpStatus.FOUND);
	}

	@PostMapping("/addBlock/{mallId}")
	public ResponseEntity<Block> addNewBlock(@RequestBody Block block, @PathVariable("mallId") int mallId) {

		return new ResponseEntity<Block>( mallService.addBlock(block, mallId),HttpStatus.CREATED);
	}
	
	@DeleteMapping("{blockId}")
	public void deleteBlock(@PathVariable int blockId) {
	
		mallService.deleteBlock(blockId);
	}
	@GetMapping("/viewAllblocks")
	public ResponseEntity<List<Block>> viewAllBlocks() {
		return new ResponseEntity<List<Block>>( mallService.viewAllBlocks(),HttpStatus.FOUND);
	}

	@PostMapping("/addSlot/{blockId}")
	public ResponseEntity<Slot> addSlotByBlockId(@RequestBody Slot slot, @PathVariable int blockId) {

		return new ResponseEntity<Slot>(mallService.addSlot(slot, blockId), HttpStatus.OK);
	}
	
	@GetMapping("/viewAllSlots")
	public ResponseEntity<List<Slot>> viewAllSlots() {
		return new ResponseEntity<List<Slot>>( mallService.viewAllSlots(),HttpStatus.FOUND);
	}

}