package org.jdev.service;

import java.security.SecureRandom;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;


@Service
public class AuthGeneratorCodes {
    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
    private static final int LENGTH = 128;
    private static final SecureRandom secureRandom = new SecureRandom();




    public AuthGeneratorCodes(){

    }

    public String generateCodeVerifier(){
        return generateCodeVerifier(LENGTH);
    }


    public String generateChallengeCode(String codeVerifier){
        return DigestUtils.sha256Hex(codeVerifier);
    }


    public String generateCodeVerifier(int lenght){
        if (0 >= lenght || lenght  > LENGTH ){
            throw new RuntimeException("Длинна не может быть меньше нуля или больше 128");
        }
        StringBuilder codeVerifier = new StringBuilder(lenght);

        for (int i = 0; i < lenght; i++){
            int randomIndex = secureRandom.nextInt(ALLOWED_CHARS.length());
            codeVerifier.append(ALLOWED_CHARS.charAt(randomIndex));
        }

        return codeVerifier.toString();
    }

    public String generateState(){
        return "state"; // TODO Пределать, по возможности
    }
}
