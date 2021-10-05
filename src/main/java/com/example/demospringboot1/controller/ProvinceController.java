package com.example.demospringboot1.controller;

import com.example.demospringboot1.model.Province;
import com.example.demospringboot1.service.customerservice.IServiceCustomer;
import com.example.demospringboot1.service.provinceservice.IServiceProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/homeProvince")
public class ProvinceController {
    @Autowired
    private IServiceProvince serviceProvince;
    @Autowired
    private IServiceCustomer serviceCustomer;
    @GetMapping("")
    public ModelAndView showListProvince(){
        Iterable<Province> provinces = serviceProvince.showAll();
        ModelAndView mv = new ModelAndView("/province/list");
        mv.addObject("listProvince", provinces);
        return mv;
    }

    @GetMapping("create")
    public ModelAndView showCreateProvinceForm(){
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }
    @PostMapping("create")
    public String createProvince(@ModelAttribute("province") Province province, RedirectAttributes redirect){
        serviceProvince.save(province);
        redirect.addFlashAttribute("message","Create province success!!");
        return "redirect:/homeProvince";
    }

    @GetMapping("{id}/edit")
    public ModelAndView showEditProvinceForm(@PathVariable("id") Optional<Province> province){
//        (@PathVariable Long id);
//        Optional<Province> province1 = serviceProvince.findById(id);
        ModelAndView md = new ModelAndView("province/edit");
        md.addObject("province",province.get());
        return md;
    }
    @PostMapping("{id}/edit")
    public String editProvince(@ModelAttribute("province") Province province, RedirectAttributes redirect){
        serviceProvince.save(province);
        redirect.addFlashAttribute("message","Edit province success!!");
        return "redirect:/homeProvince";
    }


    @GetMapping("{id}/delete")
    public ModelAndView showDeleteProvinceForm(@PathVariable("id")Optional<Province> province){
        ModelAndView md = new ModelAndView("province/delete");
        md.addObject("province",province.get());
        return md;
    }
    @PostMapping("{id}/delete")
    public String deleteProvince(@ModelAttribute("province") Province province, RedirectAttributes redirect){
        serviceProvince.delete(province.getId());
        redirect.addFlashAttribute("message","Delete province success!!");
        return "redirect:/homeProvince";
    }

    @GetMapping("{id}/detail")
    public ModelAndView showDetailProvinceForm(@PathVariable("id")Optional<Province> province){
        ModelAndView md = new ModelAndView("province/detail");
        md.addObject("customers",serviceCustomer.findAllByProvince(province.get()));
        return md;
    }
}
