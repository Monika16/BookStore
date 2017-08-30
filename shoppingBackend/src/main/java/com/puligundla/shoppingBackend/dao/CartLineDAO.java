package com.puligundla.shoppingBackend.dao;

import java.util.List;

import com.puligundla.shoppingBackend.dto.Cart;
import com.puligundla.shoppingBackend.dto.CartLine;

public interface CartLineDAO {
	
	public CartLine get(int cartId);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	public List<CartLine> list(int cartId);
	
	
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId,int productId);
	
	boolean updateCart(Cart cart);
}
