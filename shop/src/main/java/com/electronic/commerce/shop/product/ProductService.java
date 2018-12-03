package com.electronic.commerce.shop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public void createProduct(Product product) {
		if(product != null) {
			productRepository.save(product);
		}else
		{
			throw new RuntimeException();
		}
	}
	
	public void editProduct(Product product) {
		if(product != null) {
			productRepository.save(product);
		}
	}
	
	public void deleteProduct(Product product) {
		if(product != null) {
			productRepository.delete(product);
		}
	}

}
