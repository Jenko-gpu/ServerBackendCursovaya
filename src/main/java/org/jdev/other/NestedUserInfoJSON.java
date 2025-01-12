package org.jdev.other;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NestedUserInfoJSON {
    private UserInfoJSON user;

    public NestedUserInfoJSON(UserInfoJSON user){
        this.user = user;
    }

}
