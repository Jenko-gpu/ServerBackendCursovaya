package org.jdev.service;

import org.jdev.controller.dto.AuthRequestDTO;
import org.jdev.controller.dto.AuthResponseDTO;
import org.jdev.controller.dto.UserInfoRequestDTO;
import org.jdev.controller.dto.UserInfoResponseDTO;
import org.jdev.entity.User;
import org.jdev.exeptions.NotFoundAccessToken;
import org.jdev.other.VKapiJSON;
import org.jdev.repository.UserRepository;
import org.jdev.service.CashProxy.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private AuthGeneratorCodes generatorCodes;

    @Autowired
    private RequestToVkService requestToVkService;

    private final Map<String, TimeStamp<String>> registerStorage = new ConcurrentHashMap<>();
    private final Map<String, TimeStamp<Integer>> userStorage = new ConcurrentHashMap<>();

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponseDTO register(){
        String codeVerifier = generatorCodes.generateCodeVerifier(43);
        String state = generatorCodes.generateState();
        String access = jwtUtil.generateToken(generatorCodes.generateCodeVerifier(16));
        registerStorage.put(access,new TimeStamp<>(codeVerifier));
        System.out.println(codeVerifier);
        return new AuthResponseDTO(codeVerifier, state, access);
    }

    public UserInfoResponseDTO login(AuthRequestDTO request){
        String codeVerifier = registerStorage.get(request.getApiAccess()).getData();
        if (codeVerifier == null){
            throw new NotFoundAccessToken();
        }

        registerStorage.remove(request.getApiAccess());
        VKapiJSON json = requestToVkService.getVkJson(request.getCode(), codeVerifier, request.getDeviceId(), request.getState());

        userStorage.put(json.getAccess_token(), new TimeStamp<>(json.getUser_id()));
        saveUser(json);

        return new UserInfoResponseDTO(json.getAccess_token(), requestToVkService.getName(json.getAccess_token()));
    }


    private void saveUser(VKapiJSON json){

        if (userRepository.findById(json.getUser_id()).isPresent()) return;
        String name = requestToVkService.getName(json.getAccess_token());
        userRepository.save(new User(json.getUser_id(), name));
    }

    public User getUser(String accessToken){
        Integer id = userStorage.get(accessToken).getData();
        if (id == null){
            throw new NotFoundAccessToken();
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new NotFoundAccessToken();
        return user.get();
    }

    public void clearOldTokens() {
        long currentTime = System.currentTimeMillis();
        registerStorage.entrySet().removeIf(entry -> {
            TimeStamp<String> stamp = entry.getValue();
            return (currentTime - stamp.getTimestamp().getEpochSecond()*1000) > 1000*60*3;
        });
    }
}
