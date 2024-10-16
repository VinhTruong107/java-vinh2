package Nguoi;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;

    public Product(String productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters và Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Phương thức hiển thị thông tin sản phẩm
    public void displayProduct() {
        System.out.println("ID: " + productId + ", Name: " + productName + ", Price: " + price + ", Quantity: " + quantity);
    }
      // Static method to initialize default products
    public static List<Product> initializeDefaultProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P001", "Laptop Dell XPS 13", 1500.00, 10));
        products.add(new Product("P002", "MacBook Pro 16", 2500.00, 5));
        products.add(new Product("P003", "Máy tính để bàn HP", 800.00, 15));
        products.add(new Product("P004", "Màn hình ASUS 24 inch", 300.00, 20));
        products.add(new Product("P005", "Bàn phím cơ Logitech", 100.00, 30));
        products.add(new Product("P006", "Chuột gaming Razer", 70.00, 25));
        products.add(new Product("P007", "Máy in Canon", 200.00, 8));
        products.add(new Product("P008", "Ổ cứng SSD Samsung 1TB", 150.00, 12));
        products.add(new Product("P009", "RAM Corsair 16GB", 75.00, 18));
        products.add(new Product("P010", "Bộ nguồn Cooler Master", 100.00, 15));
        return products;
    }
}