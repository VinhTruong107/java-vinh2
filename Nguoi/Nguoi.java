package Nguoi; // Định nghĩa package là Nguoi

import java.io.Serializable; // Nhập khẩu Serializable để cho phép đối tượng của lớp này có thể được tuần tự hóa

// Lớp Nguoi là lớp trừu tượng và implements Serializable
public abstract class Nguoi implements Serializable {
    // Các thuộc tính riêng của lớp Nguoi
    private String id; // Mã định danh của người
    private String name; // Tên của người
    private String address; // Địa chỉ của người
    private String phoneNumber; // Số điện thoại của người

    // Constructor mặc định
    public Nguoi() {}

    // Constructor với tất cả các thông tin chi tiết
    public Nguoi(String id, String name, String address, String phoneNumber) {
        this.id = id; // Khởi tạo id
        this.name = name; // Khởi tạo tên
        this.address = address; // Khởi tạo địa chỉ
        this.phoneNumber = phoneNumber; // Khởi tạo số điện thoại
    }

    // Getters và Setters cho các thuộc tính
    public String getId() {
        return id; // Trả về ID
    }

    public void setId(String id) {
        this.id = id; // Thiết lập ID
    }

    public String getName() {
        return name; // Trả về tên
    }

    public void setName(String name) {
        this.name = name; // Thiết lập tên
    }

    public String getAddress() {
        return address; // Trả về địa chỉ
    }

    public void setAddress(String address) {
        this.address = address; // Thiết lập địa chỉ
    }

    public String getPhoneNumber() {
        return phoneNumber; // Trả về số điện thoại
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber; // Thiết lập số điện thoại
    }

    // Phương thức hiển thị thông tin của người
    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Address: " + address + ", Phone: " + phoneNumber);
    }

    // Phương thức trừu tượng để được triển khai trong các lớp con
    public abstract void performAction();
}
