// Inventory Management System Application

import java.util.*;

// Product class
class Product {
    private String id;
    private String name;
    private int stock;

    public Product(String id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int newStock) {
        this.stock = newStock;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Stock: " + stock;
    }
}

// Supplier interface
interface Supplier {
    void supplyProduct(String productId, int quantity);
}

// Inventory class
class Inventory implements Supplier {
    private Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        System.out.println("Product added: " + product);
    }

    public void removeProduct(String productId) {
        if (products.containsKey(productId)) {
            products.remove(productId);
            System.out.println("Product removed with ID: " + productId);
        } else {
            System.out.println("Product ID not found: " + productId);
        }
    }

    public void updateProductStock(String productId, int newStock) {
        Product product = products.get(productId);
        if (product != null) {
            product.updateStock(newStock);
            System.out.println("Stock updated: " + product);
        } else {
            System.out.println("Product ID not found: " + productId);
        }
    }

    public void checkLowStock(int threshold) {
        System.out.println("Products with stock below " + threshold + ":");
        products.values().stream()
                .filter(product -> product.getStock() < threshold)
                .forEach(System.out::println);
    }

    @Override
    public void supplyProduct(String productId, int quantity) {
        Product product = products.get(productId);
        if (product != null) {
            product.updateStock(product.getStock() + quantity);
            System.out.println("Supplied " + quantity + " units of product: " + product);
        } else {
            System.out.println("Product ID not found: " + productId);
        }
    }
}

// Inventory Management System
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Sample products
        Product product1 = new Product("P001", "Laptop", 10);
        Product product2 = new Product("P002", "Smartphone", 5);
        Product product3 = new Product("P003", "Headphones", 2);

        // Add products
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);

        // Update stock
        inventory.updateProductStock("P002", 15);

        // Check low stock
        inventory.checkLowStock(5);

        // Supply products
        inventory.supplyProduct("P003", 10);

        // Remove a product
        inventory.removeProduct("P001");

        // Check low stock again
        inventory.checkLowStock(5);
    }
}