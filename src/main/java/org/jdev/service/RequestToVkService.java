package org.jdev.service;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jdev.configurations.AppConfig;
import org.jdev.other.NestedUserInfoJSON;
import org.jdev.other.UserInfoJSON;
import org.jdev.other.VKapiJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestToVkService {

    private static String apiUrl = "https://id.vk.com/oauth2";

    @Autowired
    private AppConfig appconfig;

    public VKapiJSON getVkJson(String code, String code_verifier, String deviceId, String state){
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(apiUrl+"/auth");

            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            params.add(new BasicNameValuePair("code_verifier", code_verifier));
            params.add(new BasicNameValuePair("redirect_uri", "http://localhost/vkreg"));
            params.add(new BasicNameValuePair("code", code));
            params.add(new BasicNameValuePair("client_id", appconfig.getClientId()));
            params.add(new BasicNameValuePair("device_id", deviceId));
            params.add(new BasicNameValuePair("state", "state"));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


            HttpResponse response = httpclient.execute(httppost);
            String data = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            VKapiJSON myClassObj = gson.fromJson(data, VKapiJSON.class);

            return myClassObj;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getName(String accessToken){
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(apiUrl + "/user_info");

            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("client_id", appconfig.getClientId()));
            params.add(new BasicNameValuePair("access_token", accessToken));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


            HttpResponse response = httpclient.execute(httppost);
            String data = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            UserInfoJSON myClassObj = new UserInfoJSON(gson.fromJson(data, NestedUserInfoJSON.class).getUser());
            System.out.println(myClassObj);
            return myClassObj.getFirst_name();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
