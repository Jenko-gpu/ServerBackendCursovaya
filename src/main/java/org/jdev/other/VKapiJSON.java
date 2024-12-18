package org.jdev.other;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VKapiJSON {
    private String refresh_token;
    private String access_token;
    private String id_token;
    private String token_type;
    private Integer expires_in;
    private Integer user_id;
    private String state;
    private String scope;

    public VKapiJSON(String refresh_token, String access_token, String id_token, String token_type, Integer expires_in, Integer user_id, String state, String scope) {
        this.refresh_token = refresh_token;
        this.access_token = access_token;
        this.id_token = id_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.user_id = user_id;
        this.state = state;
        this.scope = scope;
    }
}
