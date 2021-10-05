package com.example.demospringboot1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Page403Controller {
    @GetMapping("/Error403")
    public ModelAndView page403(){
        return new ModelAndView("page403");
    }
}
