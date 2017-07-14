package com.puligundla.shoppingBackend.dao;

import java.util.List;

import com.puligundla.shoppingBackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category get(int id);
}
