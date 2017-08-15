package com.puligundla.shoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.puligundla.shoppingBackend.dao.UserDAO;
import com.puligundla.shoppingBackend.dto.Address;
import com.puligundla.shoppingBackend.dto.Cart;
import com.puligundla.shoppingBackend.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static UserDAO userDAO;
	
	private User user=null;
	private Address address=null;
	private Cart cart=null;
	
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.puligundla.shoppingBackend");
		context.refresh();
		
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	/*@Test
	public void testAdd(){
		
		user = new User();
		user.setFirstname("Sree");
		user.setLastname("S");
		user.setEmail("sree@gmail.com");
		user.setContactNumber("123456789");
		user.setRole("USER");
		user.setPassword("123456");
		
		assertEquals("Add user",true,userDAO.addUser(user));
		
		//billing address
		address = new Address();
		address.setAddressLineOne("No.1234");
		address.setAddressLineTwo("street valley");
		address.setCity("Vizag");
		address.setState("AP");
		address.setCountry("India");
		address.setPostalCode("280001");
		address.setBilling(true);
		address.setUserId(user.getId());
		
		
		assertEquals("Add billing address",true,userDAO.addAddress(address));
		
		
		if(user.getRole().equals("USER")){
			cart = new Cart();
			cart.setUser(user);
			
			assertEquals("update cart",true,userDAO.updateCart(cart));
		
		
			//shipping address
			address = new Address();
			address.setAddressLineOne("No.1234");
			address.setAddressLineTwo("street valley");
			address.setCity("Vizag");
			address.setState("AP");
			address.setCountry("India");
			address.setPostalCode("280001");
			address.setShipping(true);
			address.setUserId(user.getId());
			
			assertEquals("Add shipping address",true,userDAO.addAddress(address));
		}
	}*/
	
	/*@Test
	public void testAdd(){
		
		user = new User();
		user.setFirstname("Sree");
		user.setLastname("S");
		user.setEmail("sree@gmail.com");
		user.setContactNumber("123456789");
		user.setRole("USER");
		user.setPassword("123456");
		
		
		if(user.getRole().equals("USER")){
			cart = new Cart();
			cart.setUser(user);
			
			user.setCart(cart);
		}
			
			assertEquals("Add user",true,userDAO.addUser(user));
	}*/
	
	/*@Test
	public void testUpdate(){
		user = userDAO.getByEmail("sree@gmail.com");
		
		cart = user.getCart();
		cart.setGrandTotal(555);
		cart.setCartLines(2);
		
		assertEquals("Update cart",true,userDAO.updateCart(cart));
	}*/
	
	/*@Test
	public void testAddress(){
		
		user = new User();
		user.setFirstname("Sree");
		user.setLastname("S");
		user.setEmail("sree@gmail.com");
		user.setContactNumber("123456789");
		user.setRole("USER");
		user.setPassword("123456");
		
		assertEquals("Add user",true,userDAO.addUser(user));
		
		//billing address
		address = new Address();
		address.setAddressLineOne("No.1234");
		address.setAddressLineTwo("street valley");
		address.setCity("Vizag");
		address.setState("AP");
		address.setCountry("India");
		address.setPostalCode("280001");
		address.setBilling(true);
		
		address.setUser(user);
		
		
		assertEquals("Add billing address",true,userDAO.addAddress(address));
		
		//shipping address
		address = new Address();
		address.setAddressLineOne("No.1234");
		address.setAddressLineTwo("street valley");
		address.setCity("Vizag");
		address.setState("AP");
		address.setCountry("India");
		address.setPostalCode("280001");
		address.setShipping(true);
		address.setUser(user);
		
		assertEquals("Add shipping address",true,userDAO.addAddress(address));
	}
	*/
	/*@Test
	public void testAddAddress(){
		
		user = userDAO.getByEmail("sree@gmail.com");
		
		//shipping address
		address = new Address();
		address.setAddressLineOne("No.2234");
		address.setAddressLineTwo("Street valley");
		address.setCity("Hyderabad");
		address.setState("Telengana");
		address.setCountry("India");
		address.setPostalCode("580001");
		address.setShipping(true);
		address.setUser(user);
		
		assertEquals("Add shipping address",true,userDAO.addAddress(address));
		
	}*/
	
	/*@Test
	public void testGetAddresses(){
		
		user = userDAO.getByEmail("sree@gmail.com");
		
		assertEquals("Get shipping address size",2,userDAO.listShippingAddress(user.getId()).size());
		
		assertEquals("Get billing address","Vizag",userDAO.getBillingAddress(user.getId()).getCity());
		
	}*/
}
