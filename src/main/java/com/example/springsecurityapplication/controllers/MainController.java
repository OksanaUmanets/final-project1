package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.Cart;
import com.example.springsecurityapplication.models.OrderProduct;
import com.example.springsecurityapplication.models.OrderPerson;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;

import com.example.springsecurityapplication.repositories.CartRepository;
import com.example.springsecurityapplication.repositories.OrderPersonRepository;
import com.example.springsecurityapplication.repositories.OrderProductRepository;
import com.example.springsecurityapplication.repositories.ProductRepository;

import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.CategoryService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;

import com.example.springsecurityapplication.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

	private final ProductRepository productRepository;

	private final PersonValidator personValidator;
	private final PersonService personService;

	private final ProductService productService;

	private final CartRepository cartRepository;

	private final OrderProductRepository orderProductRepository;
	
	private final OrderPersonRepository orderPersonRepository;
	
	private final CategoryService categoryService;
	
	

	public MainController(ProductRepository productRepository, PersonValidator personValidator,
			PersonService personService, ProductService productService, CartRepository cartRepository,
			OrderProductRepository orderProductRepository,CategoryService categoryService,OrderPersonRepository orderPersonRepository) {
		this.productRepository = productRepository;
		this.personValidator = personValidator;
		this.personService = personService;
		this.productService = productService;
		this.cartRepository = cartRepository;
		this.orderProductRepository = orderProductRepository;
		this.categoryService = categoryService;
		this.orderPersonRepository = orderPersonRepository;
		
		
	}

	@GetMapping("/person account")
	public String index(Model model) {
		// Получаем объект аутентификации -> с помощью SpringContextHolder обращаемся к
		// контексту и на нем вызываем метод аутентификации. Из сессии текущего
		// пользователя получаем объект, который был положен в данную сессию после
		// аутентификации пользователя
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		String role = personDetails.getPerson().getRole();
		if (role.equals("ROLE_ADMIN")) {
			return "redirect:/admin";
		}
//        System.out.println(personDetails.getPerson());
//        System.out.println("ID пользователя: " + personDetails.getPerson().getId());
//        System.out.println("Логин пользователя: " + personDetails.getPerson().getLogin());
//        System.out.println("Пароль пользователя: " + personDetails.getPerson().getPassword());
//        System.out.println(personDetails);
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("category", categoryService.getAllCategory());
		model.addAttribute("user", personDetails.getPerson().getLogin());
		return "/user/index";
	}

	// @GetMapping("/registration")
//    public String registration(Model model){
//        model.addAttribute("person", new Person());
//        return "registration";
//    }

	@GetMapping("/registration")
	public String registration(@ModelAttribute("person") Person person) {
		return "registration";
	}

	@PostMapping("/registration")
	public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		personValidator.validate(person, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		personService.register(person);
		return "redirect:/person account";
	}

	@GetMapping("/person account/product/info/{id}")
	public String infoProduct(@PathVariable("id") int id, Model model) {
		model.addAttribute("product", productService.getProductId(id));
		return "/user/infoProduct";
	}

	
	@PostMapping("/person account/product/search")
	public String productSearch(@RequestParam("search")  String search, @RequestParam("ot")   String ot,
			@RequestParam("do")   String Do,
			@RequestParam(value = "price", required = false, defaultValue = "")  String price,
			@RequestParam(value = "contract", required = false, defaultValue = "") String contract, Model model) {
		model.addAttribute("products", productService.getAllProduct());

		if (!ot.isEmpty() & !Do.isEmpty()) {
			if (!price.isEmpty()) {
				if (price.equals("sorted_by_ascending_price")) {
					if (!contract.isEmpty()) {
						if (contract.equals("furniture")) {
							model.addAttribute("search_product",
									productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
											Float.parseFloat(ot), Float.parseFloat(Do), 1));
						} else if (contract.equals("appliances")) {
							model.addAttribute("search_product",
									productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
											Float.parseFloat(ot), Float.parseFloat(Do), 3));
						} else if (contract.equals("clothes")) {
							model.addAttribute("search_product",
									productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
											Float.parseFloat(ot), Float.parseFloat(Do), 2));
						}
					} else {
						model.addAttribute("search_product", productRepository.findByTitleOrderByPriceAsc(
								search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
					}
				} else if (price.equals("sorted_by_descending_price")) {
					if (!contract.isEmpty()) {
						System.out.println(contract);
						if (contract.equals("furniture")) {
							model.addAttribute("search_product",
									productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
											Float.parseFloat(ot), Float.parseFloat(Do), 1));
						} else if (contract.equals("appliances")) {
							model.addAttribute("search_product",
									productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
											Float.parseFloat(ot), Float.parseFloat(Do), 3));
						} else if (contract.equals("clothes")) {
							model.addAttribute("search_product",
									productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
											Float.parseFloat(ot), Float.parseFloat(Do), 2));
						}
					} else {
						model.addAttribute("search_product", productRepository.findByTitleOrderByPriceDesc(
								search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
					}
				}
			} else {
				model.addAttribute("search_product",
						productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search.toLowerCase(),
								Float.parseFloat(ot), Float.parseFloat(Do)));
			}
		} else {
			model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
		}

		model.addAttribute("value_search", search);
		model.addAttribute("value_price_ot", ot);
		model.addAttribute("value_price_do", Do);
		return "/user/product";

	}
	
	


	@GetMapping("/cart/add/{id}")
	public String addProductInCart(@PathVariable("id") int id, Model model) {
		// Получаем продукт по id
		Product product = productService.getProductId(id);
		// Извлекаем объект аутентифицированного пользователя
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Извлекаем id пользователя из объекта
		int id_person = personDetails.getPerson().getId();
		Cart cart = new Cart(id_person, product.getId());
		cartRepository.save(cart);
		return "redirect:/cart";
	}

	@GetMapping("/cart")
	public String cart(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Извлекаем id пользователя из объекта
		int id_person = personDetails.getPerson().getId();

		List<Cart> cartList = cartRepository.findByPersonId(id_person);
		List<Product> productList = new ArrayList<>();

		// Получаем продукты из корзины по id товара
		for (Cart cart : cartList) {
			productList.add(productService.getProductId(cart.getProductId()));
		}

		// Вычисление итоговой цена
		float price = 0;
		for (Product product : productList) {
			price += product.getPrice();
		}

		model.addAttribute("price", price);
		model.addAttribute("cart_product", productList);
		return "/user/cart";
	}

	@GetMapping("/cart/delete/{id}")
	public String deleteProductFromCart(Model model, @PathVariable("id") int id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Извлекаем id пользователя из объекта
		int id_person = personDetails.getPerson().getId();
		List<Cart> cartList = cartRepository.findByPersonId(id_person);
		List<Product> productList = new ArrayList<>();

		// Получаем продукты из корзины по id товара
		for (Cart cart : cartList) {
			productList.add(productService.getProductId(cart.getProductId()));
		}
		cartRepository.deleteCartByProductId(id);
		return "redirect:/cart";
	}

	@GetMapping("/order/create")
	public String order() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Извлекаем id пользователя из объекта
		int id_person = personDetails.getPerson().getId();

		List<Cart> cartList = cartRepository.findByPersonId(id_person);
		
		List<Product> productList = new ArrayList<>();

		// Получаем продукты из корзины по id товара
		for (Cart cart : cartList) {
			productList.add(productService.getProductId(cart.getProductId()));
		}

		// Вычисление итоговой цена
		float price = 0;
		for (Product product : productList) {
			price += product.getPrice();
		}

		String uuid = UUID.randomUUID().toString();
	//	Status orderStatus = statusesService.getStatusesById(1);
		
		//System.out.println(orderStatus);
		OrderPerson newOrderPerson = new  OrderPerson(uuid,personDetails.getPerson(),price,Status.Оформлен); 
		orderPersonRepository.save(newOrderPerson);
		for (Product product : productList) {
			
			OrderProduct newOrder = new OrderProduct(product,newOrderPerson, 1, product.getPrice());
			orderProductRepository.save(newOrder);
			cartRepository.deleteCartByProductId(product.getId());
		}
		
		
		
		orderPersonRepository.save(newOrderPerson);
		
		return "redirect:/orders";
	}

	@GetMapping("/orders")
	public String orderUser(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		
		String role = personDetails.getPerson().getRole();
		if (role.equals("ROLE_USER")) {
		List<OrderPerson> orderPList = orderPersonRepository.findByPersonIdNumber(personDetails.getPerson().getId());
		System.out.println(orderPList.size());
		model.addAttribute("orders", orderPList);}
		return "/user/orders";
	}

}
