package com.github.franklinthree.main.controller.user;


import com.github.franklinthree.main.model.s2c.ErrorResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private Gson defGson;
    @PostMapping("/user/login")
    public String login(@RequestParam("body") String body) {

        ErrorResponse errorResponse = new ErrorResponse(10001, "用户名或密码错误:" + body);
        return defGson.toJson(errorResponse);
    }
}
