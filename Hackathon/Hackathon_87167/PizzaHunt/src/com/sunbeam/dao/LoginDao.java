package com.sunbeam.dao;

import com.sunbeam.entity.Customer;
import com.sunbeam.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao implements AutoCloseable{
	private Connection conn;
	
	public LoginDao() throws SQLException {
		conn = DbUtil.getConnection();
	}
	
	public void insertCustomer(Customer c) throws SQLException {
		String sql = "INSERT INTO pizza_customers(name, password, mobile, address, email)"
				+ "VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement insrtStmt = conn.prepareStatement(sql)) {
			insrtStmt.setString(1, c.getName());
			insrtStmt.setString(2, c.getPassword());
			insrtStmt.setString(3, c.getMobile());
			insrtStmt.setString(4, c.getAddress());
			insrtStmt.setString(5, c.getEmail());
			insrtStmt.execute();
		}
	}
	
	public int[] userLogin(String email, String password) throws SQLException {
		String sql = "SELECT * FROM pizza_customers WHERE email = ?";
		try(PreparedStatement slctStmt = conn.prepareStatement(sql)) {
			int cnt = 0;
			slctStmt.setString(1, email);
			ResultSet rs = slctStmt.executeQuery();
			while(rs.next())
				cnt++;
			if(cnt != 0) {
				String sql2 = "SELECT * FROM pizza_customers WHERE password = ?";
				try(PreparedStatement slctStmt2 = conn.prepareStatement(sql2)) {
					int cnt2 = 0;
					slctStmt2.setString(1, password);
					ResultSet rs2 = slctStmt2.executeQuery();
					while(rs2.next())
						cnt2++;
					if(cnt2 != 0) {
						if(email.equals("admin@sunbeaminfo.com"))
							return new int[]{1};
						else {
							int custId = 0;
							String s = "SELECT id FROM pizza_customers WHERE email = ?";
							try(PreparedStatement stmt = conn.prepareStatement(s)) {
								stmt.setString(1, email);
								ResultSet rs3 = stmt.executeQuery();
								while(rs3.next())
									custId = rs3.getInt(1);
							}
							return new int [] {2, custId};
						}
					}
					else
						return new int[] {3};
				}
			}
			return new int[] {-1};
		}
	}
	
	@Override
	public void close() throws SQLException {
		if(conn !=  null) 
			conn.close();
	}
}