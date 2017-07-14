package com.puligundla.shoppingBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.puligundla.shoppingBackend.dao.CategoryDAO;
import com.puligundla.shoppingBackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();
	
	static{
		Category category = new Category();
		
		//add categories
		category.setId(1);
		category.setName("Science Fiction");
		category.setDescription("Its about Fiction");
		
		categories.add(category);
		
		//add categories
		category = new Category();
		category.setId(2);
		category.setName("Thriller");
		category.setDescription("Its about Mystery");
				
		categories.add(category);
		
		//add categories
		category = new Category();
		category.setId(3);
		category.setName("Drama");
		category.setDescription("Its about Drama");
						
		categories.add(category);
	}
	@Override
	public List<Category> list() {
		return categories;
	}
	@Override
	public Category get(int id) {
		for(Category category: categories){
			if(category.getId() == id)
				return category;
		}
		return null;
	}

}
