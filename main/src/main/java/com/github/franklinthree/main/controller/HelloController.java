package com.github.franklinthree.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    @GetMapping("/admin/hello")
//    public String admin() {
//        return "hello admin! ";
//    }
//
//    @GetMapping("/user/hello")
//    public String user() {
//        return "hello user! ";
//    }

    @GetMapping("/hello")
    public String hello(@RequestParam String body) {
        return body;
    }

    @PostMapping("/hello")
    public String postHello(@RequestParam String body) {
        return body;
    }
}
