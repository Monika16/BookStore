package com.puligundla.shoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.puligundla.shoppingBackend.dao.CartLineDAO;
import com.puligundla.shoppingBackend.dao.ProductDAO;
import com.puligundla.shoppingBackend.dao.UserDAO;
import com.puligundla.shoppingBackend.dto.Cart;
import com.puligundla.shoppingBackend.dto.CartLine;
import com.puligundla.shoppingBackend.dto.Product;
import com.puligundla.shoppingBackend.dto.User;

public class CartLineTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	private Product product=null;
	private User user=null;
	private Cart cart=null;
	private CartLine cartLine=null;
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.puligundla.shoppingBackend");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		
	}
	
	@Test
	public void testAddNewCartLine(){
		
		user = userDAO.getByEmail("n@gmail.com");
		
		cart = user.getCart();
		product = productDAO.get(1);
		
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the cartLine!",true,cartLineDAO.add(cartLine));
		
		//update the cart
		
		cart.setGrandTotal(cartLine.getTotal() + cart.getGrandTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		assertEquals("Failed to update the cart!",true,cartLineDAO.updateCart(cart));
		
	}

}
