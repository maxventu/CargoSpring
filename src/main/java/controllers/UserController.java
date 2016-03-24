package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
  

    @RequestMapping(value = {"users"}, method = RequestMethod.GET)
    public String showUsers() {
        return "users";
    }
    /*@RequestMapping(value = {"users"}, method = RequestMethod.POST)
    public String showPageAfterLogout() {
        return "main";
    }*/
}
