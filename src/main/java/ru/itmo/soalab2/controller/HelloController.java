package ru.itmo.soalab2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "/soa-lab2", method = RequestMethod.GET)
    public String get() {
        return "/index.html";
    }
}
