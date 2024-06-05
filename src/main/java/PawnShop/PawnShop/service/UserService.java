package PawnShop.PawnShop.service;

import PawnShop.PawnShop.model.security.User;

public interface UserService {
    User registerUser(User user);
//    List<User> getUsers();
//    void deleteUser(String email);
    User authenticateUser(String email, String password);
}
