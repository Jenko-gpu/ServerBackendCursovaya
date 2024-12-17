package org.jdev.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class VerifierService {

    private static String apiUrl = "https://id.vk.com/oauth2/auth";

    public String getAccessToken(String code, String code_verifier, String deviceId, String state){
        try {
            /*
            URL url = new URL("https://id.vk.com/oauth2/auth");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            StringBuilder stringBuilder = new StringBuilder("grant_type=authorization_code")
                    .append("&code_verifier=").append(URLEncoder.encode(code_verifier, StandardCharsets.UTF_8))
                    .append("&redirect_uri=").append(URLEncoder.encode("http://localhost/vkreg", StandardCharsets.UTF_8))
                    .append("&code=").append(URLEncoder.encode(code, StandardCharsets.UTF_8))
                    .append("&client_id=").append("52681983")
                    .append("&device_id=").append(URLEncoder.encode(deviceId, StandardCharsets.UTF_8))
                    .append("&state=").append("state");
            System.out.println(stringBuilder.toString());
            String params = stringBuilder.toString();
            byte[] postData = params.getBytes(StandardCharsets.UTF_8);

            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }
            */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("https://id.vk.com/oauth2/auth");

            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            params.add(new BasicNameValuePair("code_verifier", code_verifier));
            params.add(new BasicNameValuePair("redirect_uri", "http://localhost/vkreg"));
            params.add(new BasicNameValuePair("code", code));
            params.add(new BasicNameValuePair("client_id", "52681983"));
            params.add(new BasicNameValuePair("device_id", deviceId));
            params.add(new BasicNameValuePair("state", "state"));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "!";
    }
}
