package com.github.franklinthree.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExampleController {
    @RequestMapping("/example")
    public @ResponseBody String example() {
        return "This is an example";
    }
}
