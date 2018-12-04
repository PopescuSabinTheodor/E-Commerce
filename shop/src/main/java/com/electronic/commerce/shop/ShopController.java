package com.electronic.commerce.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.electronic.commerce.shop.product.Product;
import com.electronic.commerce.shop.product.ProductDTO;
import com.electronic.commerce.shop.product.ProductService;
import com.electronic.commerce.shop.user.User;
import com.electronic.commerce.shop.user.UserService;

/**
 * 
 * @author Sabin Theodor
 *
 */
@RestController
public class ShopController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	
	

	
	
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	public void addNewProduct(@RequestBody ProductDTO product) {
		Product newProduct = new Product();
		newProduct.setName(product.getName());
		newProduct.setDescription(product.getDescription());
		newProduct.setSpecifications(product.getSpecifications());
		newProduct.setStock(product.getStock());
		newProduct.setPrice(product.getPrice());
		newProduct.setCategoryId(1);
		System.out.println(product.toString());
		productService.createProduct(newProduct);
	}
	
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.DELETE)
	public void deleteProduct(ProductDTO product) {
		//productService.deleteProduct(product);
	}
	
	@RequestMapping(value = "/admin/edit", method = RequestMethod.PUT)
	public void editProduct(ProductDTO product) {
		//productService.editProduct(product);
	}
}
