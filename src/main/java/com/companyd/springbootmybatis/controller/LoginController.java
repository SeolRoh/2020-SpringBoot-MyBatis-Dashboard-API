package com.companyd.springbootmybatis.controller;

import com.companyd.springbootmybatis.entity.Users;
import com.companyd.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    UserService service;

    //    로그인
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Users user, HttpServletRequest req, RedirectAttributes rttr){
        System.out.println("login 전");

        HttpSession session = req.getSession();
        Users login = service.getLogin(user);
        if(login == null){
            session.setAttribute("member",null);
            rttr.addFlashAttribute("msg",false);
        }else{
            session.setAttribute("member",login);
        }

        System.out.println("login 후");
        return "redirect:/";
    }
}
