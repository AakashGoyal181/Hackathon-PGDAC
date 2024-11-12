package com.sunbeam.entity;

import java.util.Scanner;

public class Customer {

	private int id;
	private String name;
	private String password;
	private String mobile;
	private String address;
	private String email;
	
	public Customer() {}

	public Customer(int id, String name, String password, String mobile, String address, String email) {
	
		this.id = id;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.address = address;
		this.email = email;
		
	}
	
	public void accept(Scanner sc) {	
		System.out.print("Enter name: ");
		sc.nextLine();
		this.name = sc.nextLine();
		System.out.print("Enter password: ");
		this.password = sc.next();
		System.out.print("Enter 10 digit mobile no: ");
		this.mobile = sc.next();
		System.out.print("Enter address: ");
		sc.nextLine();
		this.address = sc.nextLine();
		System.out.print("Enter email: ");
		this.email = sc.next();		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Customer [ID=" + id + ", name=" + name + ", password=" + password + ", mobile=" + mobile + ", address="
				+ address + ", email=" + email + "]";
	}
}