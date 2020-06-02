package com.companyd.springbootmybatis.service;

import com.companyd.springbootmybatis.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<Users> getAllUsers();
    Users getUserById(int id);
    int createUser(Users user);
    int modifyUser(Users user);
    int removeUser(int id);
    Users getLogin(Users user);


}
