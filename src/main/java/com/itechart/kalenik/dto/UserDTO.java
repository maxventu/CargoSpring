package com.itechart.kalenik.dto;

import com.itechart.kalenik.dao.entity.Address;
import com.itechart.kalenik.dao.entity.Company;
import com.itechart.kalenik.dao.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Integer id;

    private Company company;

    private Address address;

    private String username;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    private Date birthday;

    private List<UserRole> role;
}
