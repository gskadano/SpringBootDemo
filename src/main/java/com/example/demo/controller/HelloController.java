package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String init() {
        return "Hello World!";
    }

    @GetMapping("/add")
    public String add(@RequestParam(value="firstname") String firstname
            , @RequestParam(value="lastname") String lastname) {

        userRepository.save(new User(firstname, lastname));
        return "Adding " + firstname + " " + lastname;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value="firstname") String firstname) {
        User user = userRepository.findUserByFirstname(firstname);

        return user == null ? "" : "Hello " + user.getFirstname() + " " + user.getLastname();
    }
}
