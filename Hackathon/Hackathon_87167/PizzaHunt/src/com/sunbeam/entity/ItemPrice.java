package com.sunbeam.entity;

import java.util.Scanner;

public class ItemPrice {
	
	private int id;
	private int itemId;
	private String sizes;
	private double price;
	
	public ItemPrice() {
		
	}

	public ItemPrice(int id, int itemId, String sizes, double price) {
		
		this.id = id;
		this.itemId = itemId;
		this.sizes = sizes;
		this.price = price;
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enter Item ID: ");
		this.itemId = sc.nextInt();
		System.out.print("Enter Sizes: ");
		this.sizes = sc.next();	
		System.out.print(" Enter Price: ");
		this.price = sc.nextDouble();
	}

	public int getId() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ItemPrice [ID=" + id + ", itemID=" + itemId + ", sizes=" + sizes + ", price=" + price + "]";
	}
}