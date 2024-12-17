package org.jdev.service;

import org.jdev.controller.dto.AuthRequestDTO;
import org.jdev.controller.dto.AuthResponseDTO;
import org.jdev.exeptions.NotFoundAccessToken;
import org.jdev.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private AuthGeneratorCodes generatorCodes;

    @Autowired
    private VerifierService verifierService;

    private final AuthRepository authRepository;
    private final Map<String, String> registerStorage = new HashMap<>();
    private final Map<String, String> userStorage = new HashMap<>();

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public AuthResponseDTO register(){
        String codeVerifier = generatorCodes.generateCodeVerifier(43);
        String state = generatorCodes.generateState();
        String access = jwtUtil.generateToken(generatorCodes.generateCodeVerifier(16));
        registerStorage.put(access,codeVerifier);
        String challengeCode = generatorCodes.generateChallengeCode(codeVerifier);
        System.out.println(codeVerifier);
        System.out.println(challengeCode);
        return new AuthResponseDTO(codeVerifier, state, access);
    }

    public String login(AuthRequestDTO request){
        String codeVerifier = registerStorage.get(request.getApiAccess());
        if (codeVerifier == null){
            throw new NotFoundAccessToken();
        }
        System.out.println(codeVerifier);
        registerStorage.remove(request.getApiAccess());

        userStorage.put(request.getCode(), codeVerifier);

        return verifierService.getAccessToken(request.getCode(), codeVerifier, request.getDeviceId(), request.getState());
    }

}
