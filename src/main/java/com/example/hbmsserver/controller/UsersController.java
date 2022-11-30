package com.example.hbmsserver.controller;

import com.example.hbmsserver.dao.UserDao;
import com.example.hbmsserver.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/get")
    public UserInfoDto get(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = Long.parseLong(authentication.getName());
        var user = userDao.getOne(userId);
        return new UserInfoDto(
                user.getName()
        );
    }

    @DeleteMapping("/delete")
    public String delete(){
        return "TODO";
    }
}
