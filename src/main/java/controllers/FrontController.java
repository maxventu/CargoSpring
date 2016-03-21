package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

/**
 * Created by maksim.kalenik on 18.03.2016.
 */
@Controller
public class FrontController {
    @PostConstruct
    public void init() {
    }

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public ModelAndView showStartPage(Model model) {
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = {"/", "index"}, method = RequestMethod.POST)
    public String showPageAfterLogout() {
        return "index";
    }
}
