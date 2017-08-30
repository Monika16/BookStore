package com.puligundla.BookStore.service;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puligundla.BookStore.model.UserModel;
import com.puligundla.shoppingBackend.dao.CartLineDAO;
import com.puligundla.shoppingBackend.dao.ProductDAO;
import com.puligundla.shoppingBackend.dto.Cart;
import com.puligundla.shoppingBackend.dto.CartLine;
import com.puligundla.shoppingBackend.dto.Product;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	//returns cart of the user who has logged in
	private Cart getCart(){
		
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	//returns entire cart lines for that cart
	public List<CartLine> getCartLines(){
		
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());
	}
	
	public String updateCartLine(int cartLineId, int count){
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null){
			return "result=error";
		}
		else{
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			
			if(product.getQuantity() <= count){
				count = product.getQuantity();
			}
			
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(Double.valueOf(df2.format(product.getUnitPrice() * count)));
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
	}

	public String deleteCartLine(int cartLineId) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null){
			return "result=error";
		}
		else{
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			
			cartLineDAO.delete(cartLine);
			return "result=deleted";
		}
		
	}

	public String addCartLine(int productId) {
		String response = null;
		Cart cart = this.getCart();
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null){
			
			cartLine = new CartLine();
			
			Product product = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			
			response ="result=added";
		}
		else{
			
			Product product = productDAO.get(productId);
			double oldTotal = cartLine.getTotal();
			cartLine.setProductCount(cartLine.getProductCount()+1);
			cartLine.setTotal(Double.valueOf(df2.format(product.getUnitPrice() * cartLine.getProductCount())));
			
			cartLineDAO.update(cartLine);
			
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			response ="result=added";
		}
		
		return response;
	}

}
