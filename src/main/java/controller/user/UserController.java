package controller.user;

import dao.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/users"}, method = RequestMethod.GET, headers="Accept=*/*", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody  List<User> getUsers() {
        logger.debug("finding all users");
        return userService.findAll();
    }
}
