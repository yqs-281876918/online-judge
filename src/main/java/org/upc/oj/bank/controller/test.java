package org.upc.oj.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class test {
    @RequestMapping("/tests")
    public String test(){
            System.out.println("pp");
        return "ppppp";
    }
}
