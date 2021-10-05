package com.example.demospringboot1.controller;
import com.example.demospringboot1.model.Customer;
import com.example.demospringboot1.model.CustomerFormPicture;
import com.example.demospringboot1.model.Province;
import com.example.demospringboot1.service.customerservice.IServiceCustomer;
import com.example.demospringboot1.service.provinceservice.IServiceProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/homePage")
public class HomeController {
    @Autowired
    private IServiceCustomer iServiceCustomer;
    @Autowired
    private IServiceProvince iServiceProvince;

    @Autowired
    Environment env;

    @ModelAttribute("province1")
    public List<Province> provinces(){
        return iServiceProvince.showAll();
    }

    @GetMapping("")
    public ModelAndView home(){
        Iterable<Customer> listCustomers = iServiceCustomer.showAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("listCustomer", listCustomers);
        return modelAndView;
    }
    @GetMapping("create")
    public ModelAndView showFormCreate(){
        ModelAndView mav = new ModelAndView("customer/create");
        mav.addObject("customer",new CustomerFormPicture());
        return mav;
    }
    @PostMapping("create")
    public String createCustomer(@ModelAttribute CustomerFormPicture customerForm, RedirectAttributes redirect){
        MultipartFile multipartFileImg = customerForm.getImg();
        String fileName = multipartFileImg.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path").toString();
        try {
            FileCopyUtils.copy(multipartFileImg.getBytes(),new File(fileUpload+ fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Customer customer = new Customer();
        customer.setNameCustomer(customerForm.getNameCustomer());
        customer.setImg(fileName);
        customer.setProvince(customerForm.getProvince());
        iServiceCustomer.save(customer);
        redirect.addFlashAttribute("massage","Create new customer successfully!");
        return "redirect:/homePage";
    }
    @GetMapping("{id}/edit")
    public ModelAndView showFormUpdate(@PathVariable("id") Optional<Customer> customer){
        ModelAndView mav = new ModelAndView("/customer/edit");
        mav.addObject("editCustomer",customer.get());
        return mav;
    }
    @PostMapping("{id}/edit")
    public String UpdateCustomer(@ModelAttribute Customer customer, MultipartFile newImgFile, RedirectAttributes redirect){

        String imgFileName = newImgFile.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path").toString();
        try {
            FileCopyUtils.copy(newImgFile.getBytes(),new File(fileUpload + imgFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (imgFileName != ""){
            customer.setImg(imgFileName);
        }
        iServiceCustomer.save(customer);
        redirect.addFlashAttribute("message","Update customer successfully!");
        return "redirect:/homePage";
    }
    @GetMapping("{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Optional<Customer> customer){
        ModelAndView mav = new ModelAndView("customer/delete");
        mav.addObject("deleteCustomer",customer.get());
        return mav;
    }
    @PostMapping("{id}/delete")
    public String deleteCustomer(@ModelAttribute Customer customer,RedirectAttributes redirect){
        iServiceCustomer.delete(customer.getId());
        redirect.addFlashAttribute("message","Delete customer successfully!");
        return "redirect:/homePage";
    }
    @GetMapping("{id}/detail")
    public ModelAndView showDetailCustomer(@PathVariable("id") Optional<Customer> customer){
        ModelAndView mav = new ModelAndView("customer/detail");
        mav.addObject("detailCustomer",customer.get());
        return mav;
    }

}
