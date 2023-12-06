package cn.acdog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StartController {
    @GetMapping
    public String start() {
        return "原神 启动！";
    }
}
