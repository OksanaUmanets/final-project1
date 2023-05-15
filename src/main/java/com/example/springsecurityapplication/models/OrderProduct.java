package com.example.springsecurityapplication.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ОrderProduct")



public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product product;
    
    
   
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private OrderPerson orderPerson;
    

    private int count;
    private float price;
    private LocalDateTime dateTime;

   

    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

  
	

    public OrderProduct(Product product, OrderPerson orderPerson, int count, float price) {
		super();
		this.product = product;
		this.orderPerson = orderPerson;
		this.count = count;
		this.price = price;
	
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

  

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }




	public OrderPerson getOrderPerson() {
		return orderPerson;
	}




	public void setOrderPerson(OrderPerson orderPerson) {
		this.orderPerson = orderPerson;
	}




	public OrderProduct() {
		super();
	}


	





	

	


	
	
    
}
