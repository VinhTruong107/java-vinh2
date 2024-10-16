package Nguoi; // Định nghĩa package là Nguoi

import java.util.ArrayList; // Nhập khẩu lớp ArrayList từ thư viện java.util
import java.util.List;
import java.util.Scanner;

// Lớp Manager kế thừa từ lớp User và triển khai giao diện InfoDisplayable
public class Manager extends User implements InfoDisplayable {
    
    // Constructor với tất cả các thông tin chi tiết
    public Manager(String id, String name, String address, String phoneNumber, String username, String password) {
        super(id, name, address, phoneNumber, username, password); // Gọi constructor của lớp cha User
    }


        // Phương thức xem danh sách sản phẩm
    public void viewProducts(List<Product> productList) {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng.");
            return;
        }
    
        System.out.println("Danh sách sản phẩm:");
        for (Product product : productList) {
            System.out.println("ID: " + product.getProductId() +
                               " - Tên: " + product.getProductName() +
                               " - Giá: " + product.getPrice() +
                               " - Số lượng: " + product.getQuantity());
        }
    }

    // Phương thức thêm sản phẩm
    public void addProduct(Scanner scanner, List<Product> productList) {
        System.out.print("Nhập ID sản phẩm: ");
        String productId = scanner.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String productName = scanner.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = scanner.nextDouble();
        System.out.print("Nhập số lượng sản phẩm: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Tiêu thụ ký tự xuống dòng

        productList.add(new Product(productId, productName, price, quantity));
        System.out.println("Đã thêm sản phẩm thành công.");
    }

    // Phương thức xóa sản phẩm
    public void removeProduct(Scanner scanner, List<Product> productList) {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        String productId = scanner.nextLine();

        if (productList.removeIf(product -> product.getProductId().equals(productId))) {
            System.out.println("Đã xóa sản phẩm thành công.");
        } else {
            System.out.println("Sản phẩm không tồn tại.");
        }
    }

 /*   public void viewPurchasedUsers(ArrayList<User> userList) {
        System.out.println("Danh sách người dùng đã mua hàng:");
        
        for (User user : userList) {
            // Hiển thị thông tin người dùng
            user.displayInfo(); // Bạn có thể sử dụng phương thức này để hiển thị thông tin người dùng
            user.viewPurchaseHistory(); // Hiển thị lịch sử mua hàng của người dùng
            System.out.println(); // Thêm dòng trống giữa các người dùng
        }
    } */
    public void viewPurchasedUsers(ArrayList<User> userList, List<Product> productList) {
        System.out.println("Danh sách người dùng đã mua hàng:");
        
        for (User user : userList) {
            user.displayInfo(); // Hiển thị thông tin người dùng
    
            System.out.println("Lịch sử mua hàng:");
            for (String purchase : user.getPurchaseHistory()) {
                // Phân tích chuỗi để lấy tên sản phẩm
                String productName = extractProductName(purchase);
                System.out.println(" - " + purchase); // In ra từng sản phẩm trong lịch sử mua hàng
    
                // Tìm kiếm sản phẩm trong danh sách sản phẩm để hiển thị chi tiết
                for (Product product : productList) {
                    if (product.getProductName().equals(productName)) {
                        System.out.println("   Chi tiết sản phẩm: " +
                                "ID: " + product.getProductId() +
                                ", Giá: " + product.getPrice() +
                                ", Số lượng còn lại: " + product.getQuantity());
                        break; // Dừng vòng lặp khi tìm thấy sản phẩm
                    }
                }
            }
            System.out.println(); // Thêm dòng trống giữa các người dùng
        }
    }
    
    // Phương thức hỗ trợ để lấy tên sản phẩm từ chuỗi lịch sử mua hàng
    private String extractProductName(String purchase) {
        // Giả định chuỗi là "Mua sản phẩm: <tên sản phẩm> - Số lượng: <số lượng>"
        return purchase.split(" - ")[0].replace("Mua sản phẩm: ", "").trim();
    }
    
    
    

    // Phương thức thực hiện hành động quản lý người dùng
    @Override
    public void performAction() {
        System.out.println(getUsername() + " đang quản lý người dùng."); // Hiển thị thông báo người quản lý đang thực hiện hành động
    }
}
