package com.itechart.kalenik.service;


import com.itechart.kalenik.dao.repository.UserRepository;
import com.itechart.kalenik.dao.entity.User;
import com.itechart.kalenik.dto.UserDTO;
import com.itechart.kalenik.dto.UsersPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public UsersPageDTO findUsers(Pageable pageable){
        Page<User> userPage = userRepository.findByDeletedNotNull(pageable);
        return convertUsersToUsersPageDTO((PageImpl<User>)userPage);
    }

    private UserDTO convertUserToUserDTO(User user){
        UserDTO tempUser = new UserDTO();
        tempUser.setId(user.getId());
        tempUser.setUsername(user.getUsername());
        tempUser.setName(user.getName());
        tempUser.setSurname(user.getSurname());
        tempUser.setPatronymic(user.getPatronymic());
        tempUser.setAddress(user.getAddress());
        tempUser.setBirthday(user.getBirthday());
        tempUser.setCompany(user.getCompany());
        tempUser.setEmail(user.getEmail());
        tempUser.setRole(user.getUserRoleList());
        return tempUser;
    }

    private UsersPageDTO convertUsersToUsersPageDTO(PageImpl<User> page){
        UsersPageDTO userPageDTO = new UsersPageDTO();
        userPageDTO.setTotalElements((int)page.getTotalElements());
        List<UserDTO> content = new ArrayList<>();
        for(User user : page.getContent()){
            content.add(convertUserToUserDTO(user));
        }
        userPageDTO.setContent(content);
        return userPageDTO;
    }
}

