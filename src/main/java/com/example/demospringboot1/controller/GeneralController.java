package com.example.demospringboot1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralController {
    @GetMapping("/admin")
    public ModelAndView homeOfAdmin(){
        return new ModelAndView("/admin");
    }
    @GetMapping("/user")
    public ModelAndView homeOfUser(){
        return new ModelAndView("/user");
    }
}
