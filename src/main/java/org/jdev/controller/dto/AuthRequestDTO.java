package org.jdev.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDTO {

    private String code;
    private String state;
    private String deviceId;
    private String apiAccess;

    public AuthRequestDTO(String code, String deviceId, String state, String apiAccess) {
        this.code = code;
        this.deviceId = deviceId;
        this.state = state;
        this.apiAccess = apiAccess;
    }


    public AuthRequestDTO() {
    }


}
