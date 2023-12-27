package com.OM.dao;

import com.OM.entity.Products;
import java.util.ArrayList;
import java.util.List;

public class ProductProcessor {
    private List<Products> products = new ArrayList<>();

    public void createProduct(Products product) {
        products.add(product);
    }

    public List<Products> getAllProducts() {
        return products;
    }
}
