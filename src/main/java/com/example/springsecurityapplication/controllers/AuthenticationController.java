package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.services.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

	private final ProductService productService;
	
	public AuthenticationController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/")
	public String indexp(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "index";
	}

	@GetMapping("/authentication")
	public String login() {
		return "authentication";
	}

}
