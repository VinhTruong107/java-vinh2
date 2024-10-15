import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Nguoi.User;
import Nguoi.UserManager;
import Nguoi.Manager;
import Nguoi.Product;

public class Main4 {
    private static List<Product> productList = new ArrayList<>();
    private static UserManager userManager = new UserManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load initial products into the productList
        initializeProducts();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Đăng ký User");
            System.out.println("2. Đăng ký Manager");
            System.out.println("3. Đăng nhập User");
            System.out.println("4. Đăng nhập Manager");
            System.out.println("5. Thoát");
            System.out.print("Chọn một chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    registerManager(scanner);
                    break;
                case 3:
                    User loggedInUser = loginUser(scanner);
                    if (loggedInUser != null) {
                        userMenu(scanner, loggedInUser);
                    }
                    break;
                case 4:
                    Manager loggedInManager = loginManager(scanner);
                    if (loggedInManager != null) {
                        managerMenu(scanner, loggedInManager);
                    }
                    break;
                case 5:
                    // Lưu thông tin sản phẩm vào tệp trước khi thoát
                    saveProductsToFile("products_output.txt");
                    System.out.println("Đã lưu thông tin sản phẩm ra file và thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void initializeProducts() {
        productList.add(new Product("P001", "Laptop Dell XPS 13", 1500.00, 10));
        productList.add(new Product("P002", "MacBook Pro 16", 2500.00, 5));
        productList.add(new Product("P003", "Máy tính để bàn HP", 800.00, 15));
        productList.add(new Product("P004", "Màn hình ASUS 24 inch", 300.00, 20));
        productList.add(new Product("P005", "Bàn phím cơ Logitech", 100.00, 30));
        productList.add(new Product("P006", "Chuột gaming Razer", 70.00, 25));
        productList.add(new Product("P007", "Máy in Canon", 200.00, 8));
        productList.add(new Product("P008", "Ổ cứng SSD Samsung 1TB", 150.00, 12));
        productList.add(new Product("P009", "RAM Corsair 16GB", 75.00, 18));
        productList.add(new Product("P010", "Bộ nguồn Cooler Master", 100.00, 15));
        System.out.println("Đã khởi tạo danh sách sản phẩm ban đầu.");
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Nhập ID của User: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập password: ");
        String password = scanner.nextLine();
        userManager.registerUser(id, name, address, phoneNumber, username, password);
    }

    private static void registerManager(Scanner scanner) {
        System.out.print("Nhập ID của Manager: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập password: ");
        String password = scanner.nextLine();
        userManager.registerManager(id, name, address, phoneNumber, username, password);
    }

    private static User loginUser(Scanner scanner) {
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập password: ");
        String password = scanner.nextLine();
        return userManager.loginUser(username, password);
    }

    private static Manager loginManager(Scanner scanner) {
        System.out.print("Nhập username của Manager: ");
        String username = scanner.nextLine();
        System.out.print("Nhập password: ");
        String password = scanner.nextLine();
        return userManager.loginManager(username, password);
    }

    private static void userMenu(Scanner scanner, User user) {
        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. Mua hàng");
            System.out.println("2. Xem lịch sử mua hàng");
            System.out.println("3. Đăng xuất");
            System.out.print("Chọn một chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userBuyProduct(scanner, user);
                    break;
                case 2:
                    user.viewPurchaseHistory();
                    break;
                case 3:
                    return; // Đăng xuất và quay lại menu chính
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void userBuyProduct(Scanner scanner, User user) {
        System.out.println("Chức năng mua hàng:");
        
        // Hiển thị danh sách sản phẩm trước khi mua
        viewProducts();
        
        System.out.print("Nhập ID sản phẩm cần mua: ");
        String productId = scanner.nextLine();
        System.out.print("Nhập số lượng cần mua: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Tiêu thụ ký tự xuống dòng
    
        for (Product product : productList) {
            if (product.getProductId().equals(productId) && product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                user.addPurchase("Mua sản phẩm: " + product.getProductName() + " - Số lượng: " + quantity);
                System.out.println("Mua hàng thành công!");
                return;
            }
        }
        System.out.println("Sản phẩm không đủ số lượng hoặc không tồn tại.");
    }
    
    private static void managerMenu(Scanner scanner, Manager manager) {
        while (true) {
            System.out.println("Manager Menu:");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Đăng xuất");
            System.out.print("Chọn một chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    removeProduct(scanner);
                    break;
                case 4:
                    return; // Đăng xuất và quay lại menu chính
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void viewProducts() {
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
    private static void addProduct(Scanner scanner) {
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

    private static void removeProduct(Scanner scanner) {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        String productId = scanner.nextLine();

        if (productList.removeIf(product -> product.getProductId().equals(productId))) {
            System.out.println("Đã xóa sản phẩm thành công.");
        } else {
            System.out.println("Sản phẩm không tồn tại.");
        }
    }

    private static void saveProductsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : productList) {
                writer.write(product.getProductId() + "," +
                             product.getProductName() + "," +
                             product.getPrice() + "," +
                             product.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu sản phẩm vào file: " + e.getMessage());
        }
    }
}