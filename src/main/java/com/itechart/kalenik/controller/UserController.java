package com.itechart.kalenik.controller;

import com.itechart.kalenik.dao.entity.UserRole;
import com.itechart.kalenik.dto.UsersPageDTO;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.itechart.kalenik.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UsersPageDTO getUsers(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        logger.debug("getUsers: page "+page+", size: "+size);
        return userService.findUsers(getUserPageRequest(page, size));
    }

    private PageRequest getUserPageRequest(Integer page, Integer size) {
        return new PageRequest(page, size);
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<String> getPrincipal(Principal principal) {
        if (principal == null) {
            return null;
        }
        List<UserRole> userRoleList = userService.findByUsername(principal.getName()).getUserRoleList();
        List<String> roles = new ArrayList<>();
        for(UserRole role : userRoleList){
            roles.add(role.getName());
        }
return roles;
        /*if(userRoleList.size()>0)
        return roles.get(0);
        else return null;*/
    }
}
