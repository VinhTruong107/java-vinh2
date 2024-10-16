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
    private static ArrayList<User> userList = new ArrayList<>();  
    private static UserManager userManager = new UserManager();
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load initial products into the productList
       // initializeProducts();
        productList = Product.initializeDefaultProducts();
        System.out.println("Đã khởi tạo danh sách sản phẩm ban đầu.");

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
                    System.out.println("Danh sách sản phẩm có sẵn cho User:");
                 //   viewProducts();
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
                        managerMenu(scanner, loggedInManager, userList); 
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
           // Gọi phương thức registerUser từ UserManager và nhận lại thông tin người dùng mới
    User newUser = userManager.registerUser(id, name, address, phoneNumber, username, password);

    // Kiểm tra xem người dùng mới có được tạo thành công không
    if (newUser != null) {
        userList.add(newUser); // Thêm người dùng vào danh sách userList nếu đăng ký thành công
        System.out.println("Đăng ký thành công."); // Thông báo thành công
    } else {
        System.out.println("Đăng ký không thành công. Vui lòng kiểm tra lại thông tin."); // Thông báo lỗi
    }

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
                user.userBuyProduct(scanner, productList);
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


    private static void managerMenu(Scanner scanner, Manager manager,ArrayList<User> userList) {
        while (true) {
            System.out.println("Manager Menu:");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Xem người dùng đã mua hàng");
            System.out.println("5. Đăng xuất");
            System.out.print("Chọn một chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1:
                    manager.viewProducts(productList);
                    break;
                case 2:
                    manager.addProduct(scanner, productList);
                    break;
                case 3:
                    manager.removeProduct(scanner, productList);
                    break;
                case 4:
                if (!userList.isEmpty()) {
                    manager.viewPurchasedUsers(userList, productList); // Truyền danh sách sản phẩm vào
                } else {
                    System.out.println("Chưa có người dùng nào đăng ký hoặc mua hàng.");
                }
                break;
                case 5:
                    return; // Đăng xuất và quay lại menu chính
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
    
            }
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