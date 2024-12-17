package org.jdev.controller.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoRequestDTO {

    private String code;
    private String deviceID;

    public UserInfoRequestDTO(String code, String code_verifier, String deviceID) {
        this.code = code;
        this.deviceID = deviceID;
    }


}
