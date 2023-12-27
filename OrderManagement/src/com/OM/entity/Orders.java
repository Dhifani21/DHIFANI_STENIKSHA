package com.OM.entity;

import java.sql.Date;
import java.util.List;

public class Orders {
    private int orderId;
    private Users user;
    private List<Products> products;

    public Orders(int orderId, Users user, List<Products> products) {
        this.orderId = orderId;
        this.user = user;
        this.products = products;
    }

    public Orders(int int1, int int2, int int3) {
		// TODO Auto-generated constructor stub
	}

	public Orders(int orderId2, int userId, int productId, java.util.Date orderDate, String status) {
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int date) {
        this.orderId = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setOrderId(Date date) {
		// TODO Auto-generated method stub
		
	}
}