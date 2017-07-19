package com.puligundla.shoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.puligundla.shoppingBackend.dao.CategoryDAO;
//import com.puligundla.shoppingBackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	//private static Category category;
	
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.puligundla.shoppingBackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
		
	}
	
	@Test
	public void test(){
		
		/*//add
		category = new Category();
		category.setName("Mystery");
		category.setDescription("This is a Mystery!");
		category.setImageUrl("CAT_4.png");
		
		assertEquals("Sucessfully added the record", true, categoryDAO.add(category));
		
		//update
		category = categoryDAO.get(4);
		assertEquals("Sucessfully updated the record", true, categoryDAO.update(category));
		
		//delete
		category = categoryDAO.get(4);
		assertEquals("Sucessfully deleted the record", true, categoryDAO.delete(category));*/
		
		//list
		assertEquals("Sucessfully got list ", 3, categoryDAO.list().size());
	}
	

}
