package com.puligundla.BookStore.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.puligundla.BookStore.model.RegisterModel;
import com.puligundla.shoppingBackend.dao.UserDAO;
import com.puligundla.shoppingBackend.dto.Address;
import com.puligundla.shoppingBackend.dto.Cart;
import com.puligundla.shoppingBackend.dto.User;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init(){
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user){
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing){
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model){
		String transitionValue="success";
		
		User user = model.getUser();
		
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			 cart.setUser(user);
			 user.setCart(cart);
		}
		
		userDAO.addUser(user);
		
		Address billing = model.getBilling();
		
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext error){
		String transitionValue="success";
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			
			error.addMessage(new MessageBuilder()
					.error().source("confirmPassword")
					.defaultText("Password does not match the Confirm Password")
					.build());
			transitionValue = "failure";
		}
		
		if(userDAO.getByEmail(user.getEmail())!= null){
			
			error.addMessage(new MessageBuilder()
					.error().source("email")
					.defaultText("Email address already used")
					.build());
			transitionValue = "failure";
		}
		return transitionValue;
	}
}
