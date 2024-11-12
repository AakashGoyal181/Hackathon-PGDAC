package com.sunbeam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sunbeam.utils.DbUtil;

public class OrderDao implements AutoCloseable {
	private Connection conn;

	public OrderDao() throws SQLException {
		conn = DbUtil.getConnection();
		
	}
	
	public void showAllOrders() throws SQLException {
		String sql = "SELECT * FROM pizza_orders";

		try(PreparedStatement sel = conn.prepareStatement(sql)) {
			ResultSet rs = sel.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + ", Customer ID: " + rs.getInt(2) + ", Order Time: "
						+ rs.getString(3) + ", Order Status: " + rs.getString(4));
			}
		}
	}
	
	public void showOrdDetails(int oId) throws SQLException {
		String sql = "select c.name, c.mobile, p.name, p.type, p.description from pizza_items p, pizza_customers c, "
				+ "pizza_orders o, pizza_orderdetails od, pizza_pricing pr WHERE c.id = o.customerid and o.id = "
				+ "od.orderid and p.id = pr.itemid and pr.id = od.priceid and o.id = ?";
		
		try(PreparedStatement selStmt = conn.prepareStatement(sql)) {
			selStmt.setInt(1, oId);
			ResultSet rs = selStmt.executeQuery();
			while(rs.next()) {
				System.out.println("Customer name: " + rs.getString(1) + ", Cust. mobile: " + rs.getString(2) + 
						", Pizza name: " + rs.getString(3) + ", Type: " + rs.getString(4) + ", Desc.: " + 
						rs.getString(5));
			}
		}
	}
	
	public void showCart(int val) throws SQLException {
		String sql = "SELECT p.id, i.name, p.sizes, p.price FROM pizza_pricing p, pizza_items i WHERE p.itemid = i.id and p.id=?";
		try(PreparedStatement selStmt = conn.prepareStatement(sql)) {
			selStmt.setInt(1, val);
			ResultSet rs = selStmt.executeQuery();
			while(rs.next()) {
				System.out.println("Price id: " + rs.getInt(1) + ", Pizza name: " +  rs.getString(2)
				+ "Pizza size: " + rs.getString(3) + "Pizza price: " + rs.getString(4));
			}
		}
	}
	
	public void placeOrder(int cid, List<Integer> cart) throws SQLException {
		String sql = "INSERT into pizza_orders(customerid, ordertime, status)"
				+ "VALUES(?, ?, 'Pending')";
		String time = "SELECT NOW()";
		String sql2 = "SELECT id from pizza_orders ORDER BY id DESC LIMIT 1";
		String sql3 = "INSERT INTO pizza_orderdetails(orderid, priceid) VALUES(?, ?)";
		String t1 = "";
		int id = 0;
		int x = 0;
		try(PreparedStatement insrtStmt = conn.prepareStatement(time)) {
			ResultSet t = insrtStmt.executeQuery();
			while(t.next())
				t1 = t.getString(1);
			try(PreparedStatement Stmt = conn.prepareStatement(sql)) {
				Stmt.setInt(1, cid);
				Stmt.setString(2, t1);
				Stmt.execute();
				try(PreparedStatement insrtStmt1 = conn.prepareStatement(sql2)) {
					ResultSet rs = insrtStmt1.executeQuery();
					while(rs.next())
						id = rs.getInt(1);
				}
				try(PreparedStatement insrtStmt2 = conn.prepareStatement(sql3)) {
					while(!cart.isEmpty()) {
						for(int i=0; i<cart.size(); i++) {
							x = cart.remove(i);
							insrtStmt2.setInt(1, id);
							insrtStmt2.setInt(2, x);
							insrtStmt2.execute();
						}
					}
				}
			}
		}
	}
	
	@Override
	public void close() throws SQLException {
		if(conn!=null)
			conn.close();
	}
}
