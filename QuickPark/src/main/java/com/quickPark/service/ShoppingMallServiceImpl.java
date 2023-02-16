package com.quickPark.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.quickPark.entity.Block;
import com.quickPark.entity.Login;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.entity.Slot;
import com.quickPark.exceptions.CustomException;
import com.quickPark.exceptions.EmptyFieldException;
import com.quickPark.exceptions.MallNotFoundException;
import com.quickPark.exceptions.NoSuchBlockExistsException;
import com.quickPark.exceptions.SlotNotAvailableException;
import com.quickPark.repository.BlockRepository;
import com.quickPark.repository.CustomerRepository;
import com.quickPark.repository.LoginRepository;
import com.quickPark.repository.ShoppingMallRepository;
import com.quickPark.repository.SlotRepository;

@Component
public class ShoppingMallServiceImpl implements ShoppingMallService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ShoppingMallRepository mallRepository;
	@Autowired
	private BlockRepository blockRepository;
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	CustomerRepository customerRepository;

	public ShoppingMall addShoppingMall(ShoppingMall mall, String role) {

		if (mall.getMallEmail() == "") {
			throw new EmptyFieldException("Enter the valid email");
		}
		if (mall.getPassword() == "") {
			throw new EmptyFieldException("Enter the valid password");
		}
		if (mall.getMallName() == "") {
			throw new EmptyFieldException("Enter the Mall name");
		}

		Login login = new Login();
		login.setEmail(mall.getMallEmail());
		login.setPassword(mall.getPassword());
		login.setRole(role);
		mall.setLogin(login);

		loginRepository.save(login);

		return mallRepository.save(mall);
	}

	@Override
	public int deleteShoppingMall(int mallId) {
		if (mallRepository.findById(mallId).isEmpty()) {
			throw new MallNotFoundException("Select right mall to be deleted ");
		} else {
			int delete = mallRepository.deleteShoppingMall(mallId);
			if (delete != 0) {
				return delete;
			}
			return delete;
		}

	}

	@Transactional
	public ShoppingMall updateShoppingMall(ShoppingMall m, int mallId) {
		ShoppingMall mall = em.find(ShoppingMall.class, mallId);
		if (mall != null) {
			mall.setMallName(m.getMallName());
			mall.setMallMobileNo(m.getMallMobileNo());
			mall.setMallEmail(m.getMallEmail());
			mall.setMallId(m.getMallId());
			mall.setPassword(m.getPassword());
			mall.setAddress(m.getAddress());
		}
		return mall;
	}

	@Override
	public List<ShoppingMall> getAllShoppingMalls() {

		List<ShoppingMall> m = mallRepository.getAllShoppingMalls();
		if (m.isEmpty()) {
			throw new CustomException("No malls present");
		} else
			return m;
	}

	@Override
	public Block addBlock(Block block, int mallId) {
		if (mallRepository.findById(mallId).isEmpty()) {
			throw new MallNotFoundException("Select valid mall");
		} else {
			block.setMall(mallRepository.findById(mallId).get());

			return blockRepository.save(block);
		}

	}

	@Override
	public void deleteBlock(@PathVariable int blockId) {
		if(blockRepository.findById(blockId).isEmpty()) {
			throw new NoSuchBlockExistsException("Block not present");
		}
		else {
			mallRepository.deleteById(blockId);
		}
		
	}

	@Override
	public Slot addSlot(Slot slot, int blockId) {

		if (blockRepository.findById(blockId).isEmpty()) {
			throw new NoSuchBlockExistsException("No block found");
		}
		slot.setBlock(blockRepository.findById(blockId).get());

		return slotRepository.save(slot);
	}

	@Override
	public List<Block> viewAllBlocks() {
		if(blockRepository.findAll().isEmpty()) {
			throw new CustomException("No blocks present");
		}
		return blockRepository.findAll();
	}

	public List<Slot> viewAllSlots() {
		if (slotRepository.findAll().isEmpty()) {
			throw new SlotNotAvailableException("No slots present");
		}
		return slotRepository.findAll();
	}

}
