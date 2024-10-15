package Nguoi; // Định nghĩa package là Nguoi

import java.util.ArrayList; // Nhập khẩu ArrayList để sử dụng cho lịch sử mua hàng
import java.util.List; // Nhập khẩu List để định nghĩa danh sách lịch sử mua hàng

// Lớp User kế thừa từ lớp Nguoi và implements Purchasable
public class User extends Nguoi implements Purchasable {
    private String username; // Tên người dùng
    private String password; // Mật khẩu
    private List<String> purchaseHistory; // Lịch sử mua hàng của người dùng
    private static int totalUsers = 0; // Thuộc tính tĩnh để theo dõi tổng số người dùng

    // Constructor với tất cả các thông tin chi tiết
    public User(String id, String name, String address, String phoneNumber, String username, String password) {
        super(id, name, address, phoneNumber); // Gọi constructor của lớp cha Nguoi
        this.username = username; // Khởi tạo tên người dùng
        this.password = password; // Khởi tạo mật khẩu
        this.purchaseHistory = new ArrayList<>(); // Khởi tạo danh sách lịch sử mua hàng
        totalUsers++; // Tăng tổng số người dùng khi có người dùng mới
    }

    // Constructor cho việc đăng nhập với tên người dùng và mật khẩu
    public User(String username, String password) {
        this.username = username; // Khởi tạo tên người dùng
        this.password = password; // Khởi tạo mật khẩu
        this.purchaseHistory = new ArrayList<>(); // Khởi tạo danh sách lịch sử mua hàng
    }

    // Phương thức lấy tên người dùng
    public String getUsername() {
        return username; // Trả về tên người dùng
    }

    // Phương thức lấy mật khẩu
    public String getPassword() {
        return password; // Trả về mật khẩu
    }

    // Phương thức đăng nhập
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password); // Kiểm tra tên và mật khẩu
    }

    // Phương thức thêm mua hàng vào lịch sử
    @Override
    public void addPurchase(String product) {
        purchaseHistory.add(product); // Thêm sản phẩm vào lịch sử mua hàng
    }

    // Phương thức xem lịch sử mua hàng
    @Override
    public void viewPurchaseHistory() {
        System.out.println("Lịch sử mua hàng của " + username + ":");
        if (purchaseHistory.isEmpty()) {
            System.out.println("Không có lịch sử mua hàng."); // Thông báo nếu không có lịch sử
        } else {
            for (String purchase : purchaseHistory) {
                System.out.println(purchase); // In ra từng sản phẩm trong lịch sử mua hàng
            }
        }
    }

    // Phương thức hiển thị thông tin người dùng
    @Override
    public void displayInfo() {
        super.displayInfo(); // Gọi phương thức hiển thị thông tin của lớp cha Nguoi
        System.out.println("Username: " + username); // Hiển thị tên người dùng
    }

    // Phương thức tĩnh để lấy tổng số người dùng
    public static int getTotalUsers() {
        return totalUsers; // Trả về tổng số người dùng
    }

    // Phương thức thực hiện hành động
    @Override
    public void performAction() {
        System.out.println(username + " is performing an action."); // Thông báo hành động của người dùng
    }
}
