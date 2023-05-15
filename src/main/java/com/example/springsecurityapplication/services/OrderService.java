package com.example.springsecurityapplication.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.OrderPerson;
import com.example.springsecurityapplication.models.OrderProduct;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.OrderPersonRepository;
import com.example.springsecurityapplication.repositories.OrderProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderPersonRepository  orderPersonRepository ;
	@Autowired
	private OrderProductRepository orderProductRepository;
	

	public OrderService(OrderProductRepository orderProductRepository,OrderPersonRepository orderPersonRepository ) {
		super();
		this.orderPersonRepository = orderPersonRepository;

	}

	// Данный метод позволяет получить список заказов
	
	public List<OrderPerson> findAllOrders() {
		return orderPersonRepository.findAll();
	}
	
	
	 public OrderPerson getOrderPById(int id){
	        Optional<OrderPerson> optionalOrderP = orderPersonRepository.findById(id);
	        return optionalOrderP.orElse(null);
	    }
	
	

    
 // Данный метод позволяет обновить данные о заказе
    @Transactional
    public void updateOrderNunStat(int id,OrderPerson orderPerson){
   
   // orderPersonRepository.updateByOrderId(id,orderPerson);
   //	orderPersonRepository.save(orderPerson);
    	
    	  
    	orderPerson.setId(id);
    ;
    
    	
    	orderPersonRepository.save(orderPerson);
    	
 
    	
    }
	
}
