package com.companyd.springbootmybatis.controller;

import com.companyd.springbootmybatis.entity.LoginResponseData;
import com.companyd.springbootmybatis.entity.SignUpResponseData;
import com.companyd.springbootmybatis.entity.Users;
import com.companyd.springbootmybatis.exception.UserNotFoundException;
import com.companyd.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
public class UsersController {
    @Autowired
    UserService service;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        List<Users> list = service.getAllUsers();
        return list;
    }

    @GetMapping("/users/{id}")
    public Users getUsersById(@PathVariable int id) {
        Users user = service.getUserById(id);
        if (user == null ) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

//    @PostMapping("/users")
//    public int createUser(@Valid @RequestBody Users user) {
//        System.out.println(user.toString());
//        int createdUser = service.createUser(user);
////        if (createdUser == 0) {
////            throw new UserNotFoundException("No-CreatedUser");
////        }
//        return createdUser;
//    }


//  회원가입
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public SignUpResponseData createUser(@Valid @RequestBody Users user){
        System.out.println("post 등록");
        int createdUser = service.createUser(user);
//        System.out.println("createdUser 함수로 가져온 id 값" + createdUser);
//        Users getUser = service.getUserById(createdUser);
//        if(getUser != null){
//            return getUser;
//        }
//        return getUser;
        SignUpResponseData res = new SignUpResponseData();
        if(createdUser >= 1){ // xml파일에다 id값 return받기로함
            res.setIsSucceed(1);
        }else{
            res.setIsSucceed(0);
            // UserNotFoundException("No-CreatedUser");
        }
        return res;
    }

//  회원탈퇴
    @DeleteMapping("/delete/{id}")
    public int removeUser(@PathVariable int id) {
        int user = service.removeUser(id);
        if (user == 0 ) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

    @GetMapping("/abc")
    public List<Users> getUsers() {
        List<Users> list = service.getAllUsers();
        return list;
    }

    @PostMapping("/login")
    public LoginResponseData getLogin(@RequestBody Users user){

        System.out.println("login test");
        Users login = service.getLogin(user);
        LoginResponseData res = new LoginResponseData();
        if(user.getPassword().equals(login.getPassword())){
            res.setIsSucceed(1);
            System.out.println("로그인 성공");
        }else if(!user.getPassword().equals(login.getPassword())){
            res.setIsSucceed(0); System.out.println("비번이 서로 달라 로그인 실패");
        }else if (user.getEmail() == null || user.getPassword() == null){
            res.setIsSucceed(0); System.out.println("둘중에 하나 널값들어옴");
        }
        return res;
    }
}
