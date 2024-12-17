package org.jdev.controller.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponseDTO {

    private String codeChallenge;
    private String state;
    private String apiAccess;


    public AuthResponseDTO(String codeChallenge, String state, String apiAccess){
        this.codeChallenge = codeChallenge;
        this.state = state;
        this.apiAccess = apiAccess;
    }
}
