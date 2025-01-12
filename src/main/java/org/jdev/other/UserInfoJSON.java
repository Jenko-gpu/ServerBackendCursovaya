package org.jdev.other;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoJSON {
    private String first_name;
    private String last_name;
    private String id_token;
    private String avatar;
    private Integer sex;
    private String birthday;

    public UserInfoJSON(String first_name, String last_name, String id_token, String avatar, Integer sex, String birthday) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.id_token = id_token;
        this.avatar = avatar;
        this.sex = sex;
        this.birthday = birthday;
    }

    public UserInfoJSON(UserInfoJSON userInfoJSON) {
        this.first_name = userInfoJSON.first_name;
        this.last_name = userInfoJSON.last_name;
        this.id_token = userInfoJSON.id_token;
        this.avatar = userInfoJSON.avatar;
        this.sex = userInfoJSON.sex;
        this.birthday = userInfoJSON.birthday;
    }

    @Override
    public String toString() {
        return "UserInfoJSON{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", id_token='" + id_token + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
