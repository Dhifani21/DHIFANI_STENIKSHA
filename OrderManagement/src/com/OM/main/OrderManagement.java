package com.OM.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.OM.entity.Users;
import com.OM.entity.Orders;
import com.OM.entity.Products;
import com.OM.dao.IOrderManagementRepository;
import com.OM.dao.OrderProcessor;

public class OrderManagement {
    private static Scanner scanner = new Scanner(System.in);
    private static IOrderManagementRepository orderProcessor = new OrderProcessor();
    private static Users users = null;
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to the Order Management System!\n");
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Create Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. View All Products");
            System.out.println("6. View Orders by User");
            System.out.println("7. View All Orders");
            System.out.println("8. Exit");
            System.out.println("\nEnter your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
            case 1:
                System.out.println("Enter user ID:");
                int userId = scanner.nextInt();
                System.out.println("Enter username:");
                String username = scanner.next();
                System.out.println("Enter password:");
                String password = scanner.next();
                System.out.println("Enter role:");
                String role = scanner.next();
                users = new Users(userId, username, password, role); 
                orderProcessor.createUser(users);
                break;
                case 2:
                    System.out.println("Enter product ID:");
                    int productId = scanner.nextInt();
                    System.out.println("Enter product name:");
                    String productName = scanner.next();
                    System.out.println("Enter description:");
                    String description = scanner.next();
                    System.out.println("Enter price:");
                    double price = scanner.nextDouble();
                    System.out.println("Enter quantity in stock:");
                    int quantityInStock = scanner.nextInt();
                    System.out.println("Enter type:");
                    String type = scanner.next();
                    Products product = new Products(productId, productName, description, price, quantityInStock, type);
                    orderProcessor.createProduct(users, product);
                    if (users != null) {
                        orderProcessor.createProduct(users, product);
                    } else {
                        System.out.println("Error: No user created. Please create a user before creating a product.");
                    }
                    break;
                case 3:
                    System.out.println("Enter user ID for the order:");
                    int userIdOrder = scanner.nextInt();
                    Products userOrder = orderProcessor.getProductById(userIdOrder);

                    List<Products> products = new ArrayList<>();
                    String moreProducts = "yes";
                    while (moreProducts.equalsIgnoreCase("yes")) {
                        System.out.println("Enter product ID to add to the order:");
                        int productIdOrder = scanner.nextInt();
                        Products productOrder = orderProcessor.getProductById(productIdOrder);
                        products.add(productOrder);

                        System.out.println("Do you want to add more products to the order? (yes/no)");
                        moreProducts = scanner.next();
                    }

                    orderProcessor.createOrder(userOrder, products);
                    break;
                case 4:
                    System.out.println("Enter user ID:");
                    int userIdCancel = scanner.nextInt();
                    System.out.println("Enter order ID:");
                    int orderId = scanner.nextInt();
                    orderProcessor.cancelOrder(userIdCancel, orderId);
                    break;
                case 5:
                    List<Products> allProducts = orderProcessor.getAllProducts();
                    for (Products prod : allProducts) {
                        System.out.println(prod.getProductName());
                    }
                    break;
                case 6:
                    System.out.println("Enter user ID:");
                    int userIdOrders = scanner.nextInt();
                    List<Orders> userOrders = orderProcessor.getOrdersByUser(userIdOrders);
                    for (Orders order : userOrders) {
                        System.out.println(order.getOrderId());
                    }
                    break;
                case 7:
                    List<Orders> allOrders = orderProcessor.getAllOrders();
                    for (Orders order : allOrders) {
                        System.out.println(order.getOrderId());
                    }
                    break;
                case 8:
                    System.out.println("Thank you for using the Order Management System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }
}