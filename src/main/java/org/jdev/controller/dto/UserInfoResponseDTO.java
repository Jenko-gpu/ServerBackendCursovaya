package org.jdev.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoResponseDTO {
    private String code;
    private String name;

    public UserInfoResponseDTO(){

    }

    public UserInfoResponseDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
