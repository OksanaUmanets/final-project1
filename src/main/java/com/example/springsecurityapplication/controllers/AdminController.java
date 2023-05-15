package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.Category;
import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.OrderPerson;
import com.example.springsecurityapplication.models.OrderProduct;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;

import com.example.springsecurityapplication.models.Warehouse;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderProductRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.CategoryService;

import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;

import com.example.springsecurityapplication.services.WarehouseService;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminController {

	@Autowired
	private final ProductService productService;
	@Autowired
	private final CategoryService categoryService;
	@Autowired
	private final WarehouseService warehouseSevice;
	@Autowired
	private final OrderService orderService;
	@Autowired
	private final PersonService personService;
	
	
	

	@Value("${upload.path}")
	private String uploadPath;
	@Autowired
	private final CategoryRepository categoryRepository;
	@Autowired
	private final OrderProductRepository orp;
	

	public AdminController(ProductService productService, CategoryRepository categoryRepository,CategoryService categoryService,
			WarehouseService warehouseService,OrderService orderService,PersonService personService,OrderProductRepository orp){
		this.productService = productService;
		this.categoryRepository = categoryRepository;
		this.categoryService = categoryService;
		this.warehouseSevice = warehouseService;
		this.orderService = orderService;
		this.personService= personService;
		this.orp = orp;
		
	}

	@GetMapping("admin/product/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("category", categoryService.getAllCategory());
		model.addAttribute("warehouse", warehouseSevice.getAllwarehouse());
		return "product/addProduct";
	}

	@PostMapping("/admin/product/add")
	public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
			@RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two") MultipartFile file_two,
			@RequestParam("file_three") MultipartFile file_three, @RequestParam("file_four") MultipartFile file_four,
			@RequestParam("file_five") MultipartFile file_five, @RequestParam("category") int category,
			@RequestParam("warehouse") int warehouse, Model model)
			throws IOException {
		Category category_db = (Category) categoryService.getCategoryById(category);
		Warehouse warehouse_db = (Warehouse) warehouseSevice.getWarehouseById(warehouse);
		System.out.println(category_db.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("category", categoryService.getAllCategory());
			model.addAttribute("warehouse", warehouseSevice.getAllwarehouse());
			return "product/addProduct";
		}

		if (file_one != null) {
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String uuidFile = UUID.randomUUID().toString();
			String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
			file_one.transferTo(new File(uploadPath + "/" + resultFileName));
			Image image = new Image();
			image.setProduct(product);
			image.setFileName(resultFileName);
			product.addImageToProduct(image);

		}

		if (file_two != null) {
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String uuidFile = UUID.randomUUID().toString();
			String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
			file_two.transferTo(new File(uploadPath + "/" + resultFileName));
			Image image = new Image();
			image.setProduct(product);
			image.setFileName(resultFileName);
			product.addImageToProduct(image);
		}

		if (file_three != null) {
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String uuidFile = UUID.randomUUID().toString();
			String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
			file_three.transferTo(new File(uploadPath + "/" + resultFileName));
			Image image = new Image();
			image.setProduct(product);
			image.setFileName(resultFileName);
			product.addImageToProduct(image);
		}

		if (file_four != null) {
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String uuidFile = UUID.randomUUID().toString();
			String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
			file_four.transferTo(new File(uploadPath + "/" + resultFileName));
			Image image = new Image();
			image.setProduct(product);
			image.setFileName(resultFileName);
			product.addImageToProduct(image);
		}

		if (file_five != null) {
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String uuidFile = UUID.randomUUID().toString();
			String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
			file_five.transferTo(new File(uploadPath + "/" + resultFileName));
			Image image = new Image();
			image.setProduct(product);
			image.setFileName(resultFileName);
			product.addImageToProduct(image);
		}
		productService.saveProduct(product, category_db,warehouse_db);
		
		return "redirect:/admin";
	}

	@GetMapping("admin/product/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "product/addCategory";
	}

	@PostMapping("admin/product/addCategory")
	public String addCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult,
			 Model model) {
			if(bindingResult.hasErrors()) {
				return "admin/product/addCategory";
			}	
		
		 categoryService.saveCategory(category);
		
		return "redirect:/admin";

	}

	@GetMapping("/admin")
	public String admin(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		
		
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("user", personDetails.getPerson().getLogin());
		return "admin";
	}

	@GetMapping("admin/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);
		return "redirect:/admin";
	}

	@GetMapping("admin/product/edit/{id}")
	public String editProduct(Model model, @PathVariable("id") int id) {
		model.addAttribute("product", productService.getProductId(id));
		model.addAttribute("category", categoryService.getAllCategory());
		model.addAttribute("warehouse", warehouseSevice.getAllwarehouse());
		return "product/editProduct";

	}

	@PostMapping("admin/product/edit/{id}")
	public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
			@PathVariable("id") int id, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("category", categoryService.getAllCategory());
			model.addAttribute("warehouse", warehouseSevice.getAllwarehouse());
			return "product/editProduct";
		}
		productService.updateProduct(id, product);
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/orders")
	public String orderUser(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		String role = personDetails.getPerson().getRole();
		if (role.equals("ROLE_USER")) {
			return "redirect:/orders";
		}
		
		List<OrderPerson> orderList = orderService.findAllOrders();
		System.out.println(orderList);
		model.addAttribute("orders", orderList);
		 
		return "/admin/orders";
	}
	
	@GetMapping("admin/order/edit/{id}")
	public String editOrder(Model model, @PathVariable("id") int id){
		
		
		List<Status> enums = Arrays.asList(Status.values());
		model.addAttribute("orderProduct",orderService.getOrderPById(id));
		model.addAttribute("orderPerson", orderService.getOrderPById(id));
		model.addAttribute("statuses",enums);
		
		//System.out.println(orderService.getOrderPById(id).);
		
		
		return "admin/editOrder";
	}
	
	@PostMapping("admin/order/edit/{id}")
	public String editOrder(@ModelAttribute("orderPerson") @Valid OrderPerson orderPerson, BindingResult bindingResult,
			@PathVariable("id") Integer id , Model model) {
		
		
		
		
		orderService.updateOrderNunStat(id, orderPerson);
		
		return "redirect:/admin/orders";
	}
	
	
	
	@GetMapping("/admin/users")
	public String getAllUsers(Model model) {
		model.addAttribute("person", personService.getAllPerson());
		
		return "admin/users";
	}
	
	@GetMapping("admin/person/edit/{id}")
	public String editUser(Model model, @PathVariable("id") int id) {
		model.addAttribute("person", personService.getPersonId(id));
		return "admin/editUser";

	}
	
	
	@PostMapping("admin/person/edit/{id}")
	public String editUser(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
			@PathVariable("id") int id, Model model) {
		
		personService.updatePerson(id, person);
		
		return "redirect:/admin/users";
	}
	
	@GetMapping("admin/users/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		personService.deletePerson(id);
		return "redirect:/admin";
	}
}






















