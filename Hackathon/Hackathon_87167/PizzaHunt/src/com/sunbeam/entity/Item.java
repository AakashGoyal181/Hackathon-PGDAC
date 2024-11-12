package com.sunbeam.entity;

import java.util.Scanner;

public class Item {
	
	private int ID;
	private String name;
	private String type;
	private String category;
	private String description;
	
	public Item() {}
	
	public Item(int iD, String name, String type, String category, String description) {	
		ID = iD;
		this.name = name;
		this.type = type;
		this.category = category;
		this.description = description;
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enter Name: ");
		sc.nextLine();
		this.name = sc.nextLine();
		System.out.print("Enter Type: ");
		this.type = sc.next();		
		System.out.print("Enter Category: ");
		sc.nextLine();
		this.category = sc.nextLine();		
		System.out.print(" Enter Description ");
		this.description = sc.nextLine();	
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Item [ID= " + ID + ", name= " + name + ", type= " + type + ", category= " + category + ", description= "
				+ description + "]";
	}
}