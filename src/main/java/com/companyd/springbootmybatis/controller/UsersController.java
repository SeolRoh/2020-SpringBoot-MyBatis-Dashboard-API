package com.companyd.springbootmybatis.controller;

import com.companyd.springbootmybatis.entity.ResponseData;
import com.companyd.springbootmybatis.entity.Users;
import com.companyd.springbootmybatis.exception.UserNotFoundException;
import com.companyd.springbootmybatis.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

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
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseData createUser(@Valid @RequestBody Users user){
        System.out.println("post 등록");
        int createdUser = service.createUser(user);
//        System.out.println("createdUser 함수로 가져온 id 값" + createdUser);
//        Users getUser = service.getUserById(createdUser);
//        if(getUser != null){
//            return getUser;
//        }
//        return getUser;
        ResponseData res = new ResponseData();
        if(createdUser >= 1){ // xml파일에다 id값 return받기로함
            res.setIsSucceed(1);
        }else{
            res.setIsSucceed(0);
        }
        return res;
    }
////    로그인
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Users user, HttpServletRequest req, RedirectAttributes rttr){
//        System.out.println("login 전");
//
//        HttpSession session = req.getSession();
//        Users login = service.getLogin(user);
//        if(login == null){
//            session.setAttribute("member",null);
//            rttr.addFlashAttribute("msg",false);
//        }else{
//            session.setAttribute("member",login);
//        }
//        return "redirect:/";
//    }
//  로그아웃
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println("logout실행");
        return "redirect:/";
    }
//  회원탈퇴
    @DeleteMapping("/users/{id}")
    public int removeUser(@PathVariable int id) {
        int user = service.removeUser(id);
        if (user == 0 ) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }
}
