package org.jdev.controller.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoRequestDTO {

    private String code;

    public UserInfoRequestDTO(String code) {
        this.code = code;
    }

    public UserInfoRequestDTO() {
    }



}
