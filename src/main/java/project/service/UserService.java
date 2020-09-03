package project.service;

import project.model.Accounts;

public interface UserService {
    Accounts login(String email, String password);
    Accounts findUserByToken(String token);
}
