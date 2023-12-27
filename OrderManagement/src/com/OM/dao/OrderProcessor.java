package com.OM.dao;

import com.OM.entity.Orders;
import com.OM.entity.Products;
import com.OM.entity.Users;
import com.OM.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderProcessor implements IOrderManagementRepository {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void createOrder(Users user, List<Products> products) {
        String sql = "INSERT INTO Orders (userId, productId) VALUES (?, ?)";

        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Products product : products) {
                pstmt.setInt(1, user.getUserId());
                pstmt.setInt(2, product.getProductId());
                pstmt.executeUpdate();
            }

            System.out.println("Order created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void cancelOrder(int userId, int orderId) {
        System.out.println("Enter user ID:");
        userId = scanner.nextInt();
        System.out.println("Enter order ID:");
        orderId = scanner.nextInt();

        String sql = "DELETE FROM Orders WHERE userId = ? AND orderId = ?";

        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, orderId);
            pstmt.executeUpdate();

            System.out.println("Order cancelled successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void createProduct(Users user, Products product) {
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        System.out.println("Enter product description:");
        String description = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter quantity in stock:");
        int quantityInStock = scanner.nextInt();
        System.out.println("Enter product type:");
        scanner.nextLine();  // Consume newline left-over
        String type = scanner.nextLine();

        String sql = "INSERT INTO Products (productName, description, price, quantityInStock, type) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, productName);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, quantityInStock);
            pstmt.setString(5, type);
            pstmt.executeUpdate();

            System.out.println("Product created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void createUser(Users user) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter role:");
        String role = scanner.nextLine();

        String sql = "INSERT INTO Users (userId,username, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int userId = 0;
			pstmt.setInt(1, userId);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.executeUpdate();

            System.out.println("User created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Products> getAllProducts() {
        List<Products> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Products product = new Products(
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantityInStock"),
                    rs.getString("type")
                );

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    
    @Override
    public List<Orders> getOrdersByUser(Users user) {
        List<Orders> orders = new ArrayList<>();

        String sql = "SELECT * FROM Orders WHERE userId = ?";

        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getUserId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                int userId = rs.getInt("userId");
                int productId = rs.getInt("productId");
                Date orderDate = rs.getDate("orderDate");
                String status = rs.getString("status");

                Orders order = new Orders(orderId, userId, productId, orderDate, status);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
    
    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";

        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Orders order = new Orders(
                    rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getInt("productId")
                );

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

	@Override
	public List<Products> getOrderByUser(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void viewOrdersByUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createOrder(Products userOrder, List<Products> products) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Orders> getOrdersByUser(int userIdOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products getProductById(int productIdOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	
	}

