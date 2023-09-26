package com.gsuretech.springbootdemo.controller;


import com.gsuretech.springbootdemo.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public User user() {
        User user = new User();
        user.setId("1");
        user.setName("Abdul");
        user.setEmailId("abdul@gmail.com");

        return user;
    }

    @GetMapping("/{id}/{id2}")
    public String pathVariable(@PathVariable String id,
                               @PathVariable String id2) {
        return "The path variable is " + id + " : " + id2;

    }
    @GetMapping("/requestParam")
    public String requestParams(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String emailId){
        return "Your name is : " + name + " and EmailId is : " + emailId;
    }
}
