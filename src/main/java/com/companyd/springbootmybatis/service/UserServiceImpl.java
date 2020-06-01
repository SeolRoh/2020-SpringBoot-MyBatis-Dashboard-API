package com.companyd.springbootmybatis.service;

import com.companyd.springbootmybatis.entity.Users;
import com.companyd.springbootmybatis.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper mapper;

    @Override
    public List<Users> getAllUsers() {

        return mapper.selectAllUsers();

    }

    @Override
    public Users getUserById(int id) {

        return mapper.selectUserById(id);

    }

    @Override
    public int createUser(Users user) {
        mapper.insertUser(user);
        System.out.println("생성쿼리 실행!");
        int idData = user.getId();
        System.out.println("생성된 id 값 : " + idData);
        return idData;

    }

    @Override
    public int modifyUser(Users user) {

        return mapper.updateUser(user);
        // update -> delete -> insert
        // mapper.deleteUser(user.getUserId());
        // mapper.insertUser(user);

    }

    @Override
    public int removeUser(int id) {

        return mapper.deleteUser(id);

    }

    @Override
    public Users getLogin(Users user){
        return mapper.login(user);//조회된 값이 담기게
    }
}
