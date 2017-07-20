package com.puligundla.shoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.puligundla.shoppingBackend.dao.ProductDAO;
//import com.puligundla.shoppingBackend.dto.Product;

public class ProductTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDAO;
	
	//private Product product;
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.puligundla.shoppingBackend");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	@Test
	public void test(){
		
		//add
		/*product = new Product();
		product.setName("Gone Girl");
		product.setAuthor("Gillian Flynn");
		product.setDescription("The novel's suspense comes from the main character, Nick Dunne, and whether he is involved in the disappearance of his wife	");
		product.setUnitPrice(123.00);
		product.setActive(true);
		product.setCategoryId(1);
		product.setSupplierId(2);
		
		assertEquals("SuccessFully added the product",true,productDAO.add(product));*/
		
		//update
		/*product = productDAO.get(12);
		product.setDescription("The novel's suspense comes from the main character, Nick Dunne, and whether he is involved in the disappearance of his wife.");
		assertEquals("Successfully updated the product",true,productDAO.update(product));*/
		
		//delete
		/*product=productDAO.get(12);
		assertEquals("Successfully deleted the record",true,productDAO.delete(product));*/
		
		//list
		assertEquals("Sucessfully got the list of products",12,productDAO.list().size());
		
		//active list
		assertEquals("Sucessfully got the active list of products",9,productDAO.listActiveProducts().size());
		
		//active list by category
		assertEquals("Sucessfully got the active list of products by category",3,productDAO.listActiveProductsByCategory(1).size());
		
		//get active list for count
		assertEquals("Sucessfully got the list of products",9,productDAO.getLatestActiveProducts(9).size());
	}

}
