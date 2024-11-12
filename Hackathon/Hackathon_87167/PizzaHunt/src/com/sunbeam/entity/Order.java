package com.sunbeam.entity;

import java.util.Scanner;

public class Order {
	
	private int id;
	private int customerId;
	private String orderTime;
	private String status;
	
	public Order() {
		
	}
	
	public Order(int id, int customerId, String orderTime, String status) {
		
		this.id = id;
		this.customerId = customerId;
		this.orderTime = orderTime;
		this.status = status;
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enter Customer ID: ");
		this.customerId = sc.nextInt();
		System.out.print("Enter Order Time: ");
		sc.nextLine();
		this.orderTime = sc.nextLine();
		System.out.print("Enter Status: ");
		this.status = sc.next();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [ID=" + id + ", customerID=" + customerId + ", orderTime=" + orderTime + ", status=" + status
				+ "]";
	}
}