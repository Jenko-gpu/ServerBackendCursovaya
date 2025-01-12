package org.jdev.controller.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SheduleRequestDTO extends UserInfoRequestDTO {

    private String code;
    private String dayFrom;
    private String dayTo;

    public SheduleRequestDTO(String code, String dayFrom, String dayTo) {
        this.code = code;
        this.dayFrom = dayFrom;
        this.dayTo = dayTo;
    }
}
