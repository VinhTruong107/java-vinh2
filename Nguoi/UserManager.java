package Nguoi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public class UserManager {
    

    private List<User> userList = new ArrayList<>(); // Danh sách người dùng
    private List<Manager> managerList = new ArrayList<>(); // Danh sách quản lý
   // private List<Product> productList = new ArrayList<>(); // Danh sách sản phẩm
   // private Scanner scanner = new Scanner(System.in); // Khởi tạo scanner để nhập dữ liệu từ người dùng

    // Constructor khởi tạo và tải người dùng, quản lý từ file
    public UserManager() {
        loadUsersFromFile("users.txt"); // Tải người dùng từ file
        loadManagersFromFile("managers.txt"); // Tải quản lý từ file
    }


    // Đăng ký người dùng mới
     public User registerUser(String id, String name, String address, String phoneNumber, String username, String password) {
        User user = new User(id, name, address, phoneNumber, username, password); // Tạo đối tượng User mới
        userList.add(user); // Thêm người dùng vào danh sách
        saveUsersToFile("users.txt"); // Lưu danh sách người dùng vào file
        writeUserDetailsToFile(user); // Ghi thông tin người dùng vào file
        System.out.println("Đăng ký thành công."); // Thông báo đăng ký thành công
        return user; // Trả về đối tượng User mới
    }
   
    private void writeUserDetailsToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_registration.txt", true))) {
            writer.write("ID của user: " + user.getId() + ", tên: " + user.getName() + ", địa chỉ: " + user.getAddress() +
                    ", số điện thoại: " + user.getPhoneNumber() + ", username: " + user.getUsername() +
                    ", password: " + user.getPassword());
            writer.newLine(); // Xuống dòng
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi thông tin người dùng vào file: " + e.getMessage()); // Thông báo lỗi ghi file
        }
    }

    // Đăng ký quản lý mới
    public void registerManager(String id, String name, String address, String phoneNumber, String username, String password) {
        Manager manager = new Manager(id, name, address, phoneNumber, username, password); // Tạo đối tượng Manager mới
        managerList.add(manager); // Thêm quản lý vào danh sách
        saveManagersToFile("managers.txt"); // Lưu danh sách quản lý vào file
        writeManagerDetailsToFile(manager); // Ghi thông tin quản lý vào file
        System.out.println("Quản lý đăng ký thành công."); // Thông báo đăng ký quản lý thành công
    }

    // Ghi thông tin quản lý vào file
    private void writeManagerDetailsToFile(Manager manager) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("manager_registration.txt", true))) {
            writer.write("ID của manager: " + manager.getId() + ", tên: " + manager.getName() + ", địa chỉ: " + manager.getAddress() +
                    ", số điện thoại: " + manager.getPhoneNumber() + ", username: " + manager.getUsername() +
                    ", password: " + manager.getPassword());
            writer.newLine(); // Xuống dòng
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi thông tin quản lý vào file: " + e.getMessage()); // Thông báo lỗi ghi file
        }
    }

    // Đăng nhập người dùng
    public User loginUser(String username, String password) {
        for (User user : userList) {
            if (user.login(username, password)) { // Kiểm tra thông tin đăng nhập
                System.out.println("Đăng nhập thành công."); // Thông báo đăng nhập thành công
                return user; // Trả về đối tượng người dùng
            }
        }
        System.out.println("Tên đăng nhập hoặc mật khẩu không đúng."); // Thông báo lỗi đăng nhập
        return null; // Trả về null nếu đăng nhập thất bại
    }

    // Đăng nhập quản lý
    public Manager loginManager(String username, String password) {
        for (Manager manager : managerList) {
            if (manager.login(username, password)) { // Kiểm tra thông tin đăng nhập
                System.out.println("Quản lý đăng nhập thành công."); // Thông báo đăng nhập thành công
                return manager; // Trả về đối tượng quản lý
            }
        }
        System.out.println("Tên đăng nhập hoặc mật khẩu không đúng."); // Thông báo lỗi đăng nhập
        return null; // Trả về null nếu đăng nhập thất bại
    }

    // Đăng xuất người dùng
    public void logoutUser(User user) {
        System.out.println("Đã đăng xuất người dùng."); // Thông báo đăng xuất thành công
    }

    // Đăng xuất quản lý
    public void logoutManager(Manager manager) {
        System.out.println("Đã đăng xuất quản lý."); // Thông báo đăng xuất thành công
    }

    // Tải người dùng từ file
    private void loadUsersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
                if (parts.length == 6) { // Đảm bảo có chính xác 6 phần để tránh lỗi ArrayIndexOutOfBoundsException
                    User user = new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]); // Tạo đối tượng User từ dữ liệu
                    userList.add(user); // Thêm người dùng vào danh sách
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file người dùng: " + e.getMessage()); // Thông báo lỗi đọc file
        }
    }

    // Tải quản lý từ file
    private void loadManagersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
                if (parts.length == 6) { // Đảm bảo có chính xác 6 phần
                    Manager manager = new Manager(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]); // Tạo đối tượng Manager từ dữ liệu
                    managerList.add(manager); // Thêm quản lý vào danh sách
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file quản lý: " + e.getMessage()); // Thông báo lỗi đọc file
        }
    }

    // Lưu người dùng vào file
    private void saveUsersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : userList) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getAddress() + "," +
                        user.getPhoneNumber() + "," + user.getUsername() + "," + user.getPassword()); // Ghi thông tin người dùng
                writer.newLine(); // Xuống dòng
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file người dùng: " + e.getMessage()); // Thông báo lỗi ghi file
        }
    }

    // Lưu quản lý vào file
    private void saveManagersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Manager manager : managerList) {
                writer.write(manager.getId() + "," + manager.getName() + "," + manager.getAddress() + "," +
                        manager.getPhoneNumber() + "," + manager.getUsername() + "," + manager.getPassword()); // Ghi thông tin quản lý
                writer.newLine(); // Xuống dòng
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file quản lý: " + e.getMessage()); // Thông báo lỗi ghi file
        }
    }


  
   }
