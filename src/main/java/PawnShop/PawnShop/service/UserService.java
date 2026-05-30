package PawnShop.PawnShop.service;

import PawnShop.PawnShop.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User registerAdmin(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);
}
