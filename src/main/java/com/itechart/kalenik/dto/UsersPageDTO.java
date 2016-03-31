package com.itechart.kalenik.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersPageDTO {
    private Integer totalElements;
    private List<UserDTO> content;
}
