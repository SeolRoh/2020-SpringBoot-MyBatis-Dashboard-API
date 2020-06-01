package com.companyd.springbootmybatis.controller;

import com.companyd.springbootmybatis.entity.LoginResponseData;
import com.companyd.springbootmybatis.entity.SignUpResponseData;
import com.companyd.springbootmybatis.entity.Users;
import com.companyd.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public LoginResponseData getLogin(@RequestBody Users user){
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
//
//        System.out.println("login 후");
//        return "redirect:/";
//    }
//    //  로그아웃
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpSession session) {
//        session.invalidate();
//        System.out.println("logout실행");
//        return "redirect:/";
//    }
}
