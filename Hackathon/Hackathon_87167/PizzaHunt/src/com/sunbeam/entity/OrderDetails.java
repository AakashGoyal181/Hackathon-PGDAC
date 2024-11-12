package com.sunbeam.entity;

import java.util.Scanner;

public class OrderDetails {
	
	private int id;
	private int orderId;
	private int priceId;
	
	public OrderDetails() {}

	public OrderDetails(int id, int orderId, int priceId) {
		this.id = id;
		this.orderId = orderId;
		this.priceId = priceId;
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enter Order ID: ");
		this.orderId = sc.nextInt();
		System.out.print("Enter Price ID: ");
		this.priceId = sc.nextInt();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	
	@Override
	public String toString() {
		return "Order_Details [ID=" + id + ", orderID=" + orderId + ", priceID=" + priceId + "]";
	}
}