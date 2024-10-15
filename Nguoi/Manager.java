package Nguoi; // Định nghĩa package là Nguoi

import java.util.ArrayList; // Nhập khẩu lớp ArrayList từ thư viện java.util

// Lớp Manager kế thừa từ lớp User và triển khai giao diện InfoDisplayable
public class Manager extends User implements InfoDisplayable {
    
    // Constructor với tất cả các thông tin chi tiết
    public Manager(String id, String name, String address, String phoneNumber, String username, String password) {
        super(id, name, address, phoneNumber, username, password); // Gọi constructor của lớp cha User
    }

    // Phương thức để xem danh sách người dùng đã mua hàng
    public void viewPurchasedUsers(ArrayList<User> users) {
        System.out.println("Danh sách người dùng đã mua hàng:");
        for (User user : users) { // Lặp qua từng người dùng trong danh sách
            System.out.println("Người dùng: " + user.getUsername()); // Hiển thị tên người dùng
            user.viewPurchaseHistory(); // Gọi phương thức để xem lịch sử mua hàng của người dùng
        }
    }

    // Phương thức thực hiện hành động quản lý người dùng
    @Override
    public void performAction() {
        System.out.println(getUsername() + " đang quản lý người dùng."); // Hiển thị thông báo người quản lý đang thực hiện hành động
    }
}
