package com.OM.dao;

import com.OM.entity.Users;
import com.OM.entity.Products;
import com.OM.entity.Orders;
import java.util.List;

public interface IOrderManagementRepository {
    void createOrder(Products userOrder, List<Products> products);
    void cancelOrder(int userId, int orderId);
    void createProduct(Users user, Products product);
    void createUser(Users user);
    List<Products> getAllProducts();
    List<Orders> getOrdersByUser(int userIdOrders);
	List<Products> getOrderByUser(Users user);
	List<Orders> getAllOrders();
	Products getProductById(int productIdOrder);
	void createOrder(Users user, List<Products> products);
	List<Orders> getOrdersByUser(Users user);
}