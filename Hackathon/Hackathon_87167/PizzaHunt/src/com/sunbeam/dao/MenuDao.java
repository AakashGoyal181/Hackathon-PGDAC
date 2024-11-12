package com.sunbeam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.entity.Item;
import com.sunbeam.utils.DbUtil;

public class MenuDao implements AutoCloseable {

	private Connection connection;

	public MenuDao() throws SQLException {
		connection = DbUtil.getConnection();
		
	}

	public List<Item> getVeg() throws SQLException {
		String sql = "SELECT * FROM PIZZA_ITEMS WHERE TYPE = ?";
		try (PreparedStatement selectStmt = connection.prepareStatement(sql)) {

			List<Item> OutputList = new ArrayList<Item>();
			selectStmt.setString(1, "Veg");
			ResultSet rs = selectStmt.executeQuery();

			while (rs.next()) {
				Item i = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				OutputList.add(i);
			}

			return OutputList;
		}
	}

	public List<Item> getNonVeg() throws SQLException {
		String sql = "SELECT * FROM PIZZA_ITEMS WHERE TYPE = ?";
		try (PreparedStatement selectStmt = connection.prepareStatement(sql)) {

			List<Item> OutputList = new ArrayList<Item>();
			selectStmt.setString(1, "NonVeg");
			ResultSet rs = selectStmt.executeQuery();

			while (rs.next()) {

				Item i = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				OutputList.add(i);
			}

			return OutputList;
		}
	}

	public void getSizeByItemId(int ItemId) throws SQLException {
		String sql = "SELECT ID, SIZES, PRICE FROM PIZZA_PRICING WHERE ITEMID = ?";
		try (PreparedStatement SelStmt = connection.prepareStatement(sql)) {
			SelStmt.setInt(1, ItemId);
			ResultSet rs = SelStmt.executeQuery();

			while (rs.next())
				System.out.println("Price ID: " + rs.getInt(1) + "| Size: " + rs.getString(2) + "| Price: " + rs.getDouble(3));
		}
	}
	
	@Override
	public void close() throws SQLException {
		if(connection!=null)
			connection.close();
	}
}