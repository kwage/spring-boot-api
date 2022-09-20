package com.disney.studios.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Home {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() {
        return "index";
    }
}
