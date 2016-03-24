package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by maksim.kalenik on 18.03.2016.
 */
@Controller
public class FrontController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String showPageAfterLogout() {
        return "main";
    }
}
