package org.jdev.controller;

import org.jdev.controller.dto.AuthRequestDTO;
import org.jdev.controller.dto.AuthResponseDTO;
import org.jdev.entity.Subject;
import org.jdev.service.AuthGeneratorCodes;
import org.jdev.service.AuthService;
import org.jdev.service.JwtUtils;
import org.jdev.service.VerifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
public class AccountController {




    private final AuthService authService;

    @Autowired
    public AccountController(AuthService authService){
        this.authService = authService;
    }


    @GetMapping(value = "/api/auth/reg", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> register(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);
        return new ResponseEntity<>(authService.register(), HttpStatus.OK);
    }

    @PostMapping(value = "/api/auth/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDTO request){

        authService.login(request);

        return new ResponseEntity<>("22", HttpStatus.OK);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        // Логика получения всех предметов
        return null;
    }


    @GetMapping("/api/scores/{userId}")
    public List<ScoresDTO> getScoresByUser(@PathVariable Integer userId) {
        User user =

        List<Scores> scoresList = scoresRepository.getScoresByUser(user);
        return scoresList.stream().map(score -> new ScoresDTO(
                score.getLecture_score(),
                score.getPractice_score(),
                score.getExam_score(),
                score.getUser().getName(),
                score.getSubject().getName()
        )).collect(Collectors.toList());
    }
}
