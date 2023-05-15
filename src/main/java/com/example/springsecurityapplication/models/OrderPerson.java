package com.example.springsecurityapplication.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.example.springsecurityapplication.enumm.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrderPerson")
public class OrderPerson {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	 	
	 	
	 	@Column(name = "oPNumber")
	 	private String number;
	 	
	 	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	 	//@JoinColumn(name="OrderPerson_id", nullable=false,insertable = false, updatable = false)
	    private Person person;
	 	
	 	
	 	@OneToMany(mappedBy = "orderPerson", fetch = FetchType.EAGER)
	 	private List<OrderProduct> orderProductList;
	 	 
	 	
	    private Status status;
	 	
	 	private LocalDateTime dateTime;
	 	
	 	
	 	
	 	 @PrePersist
	     private void init(){
	         dateTime = LocalDateTime.now();
	     }
	 	
	 	@Column(name = "oPSumm")
	 	private Float orderSumm;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}

		

		public Float getOrderSumm() {
			return orderSumm;
		}

		public void setOrderSumm(Float orderSumm) {
			this.orderSumm = orderSumm;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, number, orderSumm, person);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OrderPerson other = (OrderPerson) obj;
			return id == other.id && Objects.equals(number, other.number)
				 && Objects.equals(orderSumm, other.orderSumm)
					&& Objects.equals(person, other.person);
		}

	
		
		public LocalDateTime getDateTime() {
			return dateTime;
		}

		public void setDateTime(LocalDateTime dateTime) {
			this.dateTime = dateTime;
		}

		

		

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		public OrderPerson(String number, Person person, Float orderSumm, Status orderStatus) {
			super();
			this.number = number;
			this.person = person;
			this.status = orderStatus;
			this.orderSumm = orderSumm;
		}

		public OrderPerson() {
		
		}

		public OrderPerson(String number, Status status) {
			super();
			this.number = number;
			this.status = status;
		}

		public List<OrderProduct> getOrderProductList() {
			return orderProductList;
		}

		public void setOrderProductList(List<OrderProduct> orderProductList) {
			this.orderProductList = orderProductList;
		}

		
	
	
		
}
 