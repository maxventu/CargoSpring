package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

public class UserController {
    @PostConstruct
    public void init() {
    }

    @RequestMapping(value = {"user"}, method = RequestMethod.GET)
    public ModelAndView showStartPage(Model model) {
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = {"user"}, method = RequestMethod.POST)
    public String showPageAfterLogout() {
        return "index";
    }
}
