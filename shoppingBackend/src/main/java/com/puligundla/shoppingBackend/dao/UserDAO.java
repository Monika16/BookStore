package com.puligundla.shoppingBackend.dao;

import java.util.List;

import com.puligundla.shoppingBackend.dto.Address;
import com.puligundla.shoppingBackend.dto.User;

public interface UserDAO {
	
	boolean addUser(User user);
	User getByEmail(String email);
	
	boolean addAddress(Address address);
	Address getBillingAddress(int userId);
	List<Address> listShippingAddress(int userId);
	
	

}
