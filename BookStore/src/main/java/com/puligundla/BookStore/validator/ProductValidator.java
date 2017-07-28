package com.puligundla.BookStore.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.puligundla.shoppingBackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Product product = (Product) target;
		
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")){
			errors.rejectValue("file", null, "Please upload the image file");
			return;
		}
		if(!(product.getFile().getContentType().equals("image/jpeg") || 
				product.getFile().getContentType().equals("image/png")	||
				product.getFile().getContentType().equals("image/gif") ) ){
			
			errors.rejectValue("file", null,"Please Use only Image file for upload");
			return;
		}
		
	}

}
