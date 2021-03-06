package com.puligundla.BookStore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.puligundla.BookStore.util.FileUploadUtility;
import com.puligundla.BookStore.validator.ProductValidator;
import com.puligundla.shoppingBackend.dao.CategoryDAO;
import com.puligundla.shoppingBackend.dao.ProductDAO;
import com.puligundla.shoppingBackend.dto.Category;
import com.puligundla.shoppingBackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products" , method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		Product nproduct = new Product();
		nproduct.setSupplierId(1);
		nproduct.setActive(true);
		
		mv.addObject("product", nproduct);
		
		if(operation!=null){
			if(operation.equals("product")){
				mv.addObject("message","Product submitted sucessfully!");
			}
			else if(operation.contentEquals("category")){
				mv.addObject("message","Category submitted sucessfully!");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/{id}/product" , method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		Product nproduct = productDAO.get(id);
		
		mv.addObject("product", nproduct);
		
		
		return mv;
	}
	
	@RequestMapping(value="/products" , method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product")Product mProduct, BindingResult result, Model model,
			                                 HttpServletRequest request){
		if(mProduct.getId() == 0){
			new ProductValidator().validate(mProduct,result);
		}else{
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct,result);
			}
		}
		if(result.hasErrors()){
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		if(mProduct.getId() == 0){
			productDAO.add(mProduct);
		}else{
			productDAO.update(mProduct);
		}
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productDAO.update(product);
		return (isActive)?
				 "You sucessfully deactivated the product - " + product.getId() 
				 :"You sucessfully activated the product - " + product.getId();
	}
	
	@RequestMapping(value="/category" , method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
}
