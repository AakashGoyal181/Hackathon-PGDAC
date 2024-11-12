package com.sunbeam.tester;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.dao.LoginDao;
import com.sunbeam.dao.MenuDao;
import com.sunbeam.dao.OrderDao;
import com.sunbeam.entity.Customer;
import com.sunbeam.entity.Item;

public class PizzaTest {

	public static int menu(Scanner sc) {
		System.out.println("\n1. Sign in");
		System.out.println("2. Sign up");
		System.out.println("3. Exit");
		System.out.print("Enter choice: ");
		int ch = sc.nextInt();
		
		return ch;
	}
	
	public static int custMenu(Scanner sc) {
		System.out.println("\n1. Show Veg Items");
		System.out.println("2. Show Non-Veg Items");
		System.out.println("3. Show available sizes");
		System.out.println("4. Add to cart");
		System.out.println("5. Show Cart");
		System.out.println("6. Place Order");
		System.out.println("7. Sign Out");
		System.out.print("Enter choice: ");
		int ch = sc.nextInt();
		
		return ch;
	}
	
	public static int adminMenu(Scanner sc) {
		System.out.println("\n1. Show all orders");
		System.out.println("2. Show order details (for given order id show Pizza & Customer details)");
		System.out.println("3. Sign Out");
		System.out.print("Enter choice: ");
		int ch = sc.nextInt();
		
		return ch;
	}
	
	public static void insertCustomer(Scanner sc) {
		Customer c = new Customer();
		c.accept(sc);
		
		try(LoginDao lDao = new LoginDao()) {
			lDao.insertCustomer(c);
			System.out.println("\nCustomer added successfully...");	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int[] userLogin(Scanner sc) {
		System.out.print("Enter email: ");
		String email = sc.next();
		System.out.print("Enter password: ");
		String pw = sc.next();
		int arr[] = {};
		try(LoginDao lDao = new LoginDao()) {
			arr = lDao.userLogin(email, pw);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public static void getVeg() {
		try(MenuDao mDao = new MenuDao()) {
			List<Item> l1 = mDao.getVeg();
			l1.forEach(System.out::println);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getNonVeg() {
		try(MenuDao mDao = new MenuDao()) {
			List<Item> l1 = mDao.getNonVeg();
			l1.forEach(System.out::println);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getSizeByItemId(Scanner sc) {
		System.out.print("Enter item id: ");
		try(MenuDao mDao = new MenuDao()) {
			mDao.getSizeByItemId(sc.nextInt());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void showAllOrders() {
		try(OrderDao oDao = new OrderDao()) {
			oDao.showAllOrders();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void showOrdDetails(Scanner sc) {
		System.out.print("Enter order id: ");
		try(OrderDao oDao = new OrderDao()) {
			oDao.showOrdDetails(sc.nextInt());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addCart(Scanner sc, List<Integer> cart) {
		System.out.print("Enter price id: ");
		cart.add(sc.nextInt());
		System.out.println("Added successfully...");
	}
	
	public static void showCart(List<Integer> cart) {
		try(OrderDao oDao = new OrderDao()) {
			System.out.println("Cart items: ");
			for(int i=0; i<cart.size(); i++) {
				oDao.showCart(cart.get(i));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void placeOrder(int cid, List<Integer> cart) {
		try(OrderDao oDao = new OrderDao()) {
			oDao.placeOrder(cid, cart);
			System.out.println("Updated in database...");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> cart = new ArrayList<Integer>();
		int ch;
		
		do {
			ch = menu(sc);
			
			switch(ch) {
				case 1: {
					System.out.println();
					int arr[] = userLogin(sc);
					int val = arr[0];
					if(val == -1)
						System.out.println("\nUser not registered!");
					else if(val == 3)
						System.out.println("\nWrong password...");
					else if(val == 1) {
						System.out.println("\nLogin Successfull as admin...");
						int ch1;
						do {
							ch1 = adminMenu(sc);
							
							switch(ch1) {
								case 1: {
									System.out.println();
									showAllOrders();
									break;
								}
								
								case 2: {
									System.out.println();
									showOrdDetails(sc);
									break;
								}
								
								case 3: {
									System.out.println("Signed out...");
									break;
								}
								
								default:
									System.out.println("Invalid choice...");
									break;
							}
						} while(ch1 != 3);
					}
					else if(val == 2) {
						System.out.println("Login Successfull as customer...");
						int ch1;
						do {
							ch1 = custMenu(sc);
							
							switch(ch1) {
								case 1: {
									System.out.println();
									getVeg();
									break;
								}
								
								case 2: {
									System.out.println();
									getNonVeg();
									break;
								}
								
								case 3: {
									System.out.println();
									getSizeByItemId(sc);
									break;
								}
								
								case 4: {
									System.out.println();
									addCart(sc, cart);
									break;
								}
								
								case 5: {
									System.out.println();
									showCart(cart);
									break;
								}
								
								case 6: {
									System.out.println();
									placeOrder(arr[1], cart);
									break;
								}
								
								case 7: {
									System.out.println("Signed out...");
									break;
								}
								
								default:
									System.out.println("Invalid choice...");
									break;
							}
						} while(ch1 != 7);
					}
					
					break;
				}
				
				case 2: {
					System.out.println();
					insertCustomer(sc);
					break;
				}
				
				case 3: {
					System.out.println("\nThank you for using the application...");
					break;
				}
				
				default:
					System.out.println("\nInvalid choice...");
					break;
			}
			
		} while (ch != 3);
		
		sc.close();
	}
}