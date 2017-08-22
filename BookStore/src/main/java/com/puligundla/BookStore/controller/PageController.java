package com.puligundla.BookStore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.puligundla.BookStore.exception.ProductNotFoundException;
import com.puligundla.shoppingBackend.dao.CategoryDAO;
import com.puligundla.shoppingBackend.dao.ProductDAO;
import com.puligundla.shoppingBackend.dto.Category;
import com.puligundla.shoppingBackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	@RequestMapping(value={"/","/index","/home"})
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		//passing list of objects
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);
		return mv;
	}
	
	@RequestMapping(value="/about")
	public ModelAndView about(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value="/contact")
	public ModelAndView contact(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	/* To get all products*/
	@RequestMapping(value="/show/all/products")
	public ModelAndView showAllProducts(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		//passing list of objects
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	
	/*to get products by category*/
	@RequestMapping(value="/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id")int id){
		
		ModelAndView mv = new ModelAndView("page");
		
		//to fetch single category
		Category category = null;
		category=categoryDAO.get(id);
		
		mv.addObject("title",category.getName());
		
		//passing list of objects
		mv.addObject("categories", categoryDAO.list());
		
		//single category
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	//view Single product
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		if(product == null) throw new ProductNotFoundException();
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		
		mv.addObject("userClickShowProject", true);
		return mv;
	}
	
	/* having similar mapping to our flow id*/
	@RequestMapping(value="/register")
	public ModelAndView register(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		return mv;
	}
	
	/*Login page */
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(name="error", required = false)String error, 
			                   @RequestParam(name="logout", required = false)String logout){
		ModelAndView mv = new ModelAndView("login");
		
		if(error!=null){
			mv.addObject("message", "Invalid Username and Password!");
		}
		
		if(logout!=null){
			mv.addObject("logout", "User has Successfuly logged out!");
		}
		
		mv.addObject("title", "Login");
		return mv;
	}
	
	/*Access denied*/
	@RequestMapping(value="/access-denied")
	public ModelAndView accessDenied(){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("errorTitle", "Not Allowed");
		mv.addObject("errorDescription", "You are not Authorized to view this page");
		return mv;
	}
	
	/* Logout */
	@RequestMapping(value="/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login?logout";
	}
	/*
	 * url looks like=http://localhost:8080/BookStore/test?greeting=hello
	 * @RequestMapping(value="/test")
	public ModelAndView test(@RequestParam(value="greeting", required=false)String greeting){
		if(greeting==null){
			greeting="Hello There!";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}

	 url looks like: http://localhost:8080/Bookstore/test/hi
	@RequestMapping(value="/test/{greeting}")
	public ModelAndView test(@PathVariable("greeting")String greeting){
		if(greeting==null){
			greeting="Hello There!";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}*/
	
}
