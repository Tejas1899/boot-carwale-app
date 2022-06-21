package com.ty.bootcarwaleapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}

	@GetMapping("/products")
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable int id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return productRepository.save(product);
		}
	}

	@DeleteMapping("/products")
	public String deleteProducts(@RequestParam int id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isEmpty()) {
			return "Product not available";
		} else {
			productRepository.delete(optional.get());
			return "Product deleted";
		}
	}
}
