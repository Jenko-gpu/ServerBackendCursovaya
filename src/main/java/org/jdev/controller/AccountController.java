package org.jdev.controller;

import org.jdev.controller.dto.*;
import org.jdev.entity.Scores;
import org.jdev.entity.Shedule;
import org.jdev.entity.Subject;
import org.jdev.entity.User;
import org.jdev.other.DataPrepared;
import org.jdev.service.AuthService;
import org.jdev.service.ScoreService;
import org.jdev.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin()
public class AccountController {
    private boolean notLoaded = true;

    private final DataPrepared dataPrepared;

    private final AuthService authService;
    private final ScoreService scoreService;
    private final SheduleService sheduleService;

    @Autowired
    public AccountController(DataPrepared dataPrepared, AuthService authService, ScoreService scoreService, SheduleService sheduleService){
        this.dataPrepared = dataPrepared;
        this.authService = authService;
        this.scoreService = scoreService;
        this.sheduleService = sheduleService;
    }


    @GetMapping(value = "/api/auth/reg", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> register(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);
        return new ResponseEntity<>(authService.register(), HttpStatus.OK);
    }

    @PostMapping(value = "/api/auth/login")
    public UserInfoResponseDTO login(@RequestBody AuthRequestDTO request){

        UserInfoResponseDTO userInfoResponseDTO = authService.login(request);

        if (notLoaded) { // Заполнение тестовыми данными TODO УБРАТЬ ПОСЛЕ
            dataPrepared.userTest(authService.getUser(userInfoResponseDTO.getCode()));
            notLoaded = false;
        }

        return userInfoResponseDTO;
    }

    @PostMapping(value = "/api/subjects")
    public List<Subject> getAllSubjects(@RequestBody UserInfoRequestDTO request) {
        return null; // TODO
    }

    @PostMapping(value = "/api/scores")
    public List<ScoresDTO> getScoresByUser(@RequestBody UserInfoRequestDTO request) {

        User user = authService.getUser(request.getCode());
        List<Scores> scoresList = scoreService.getScores(user);
        return scoresList.stream().map(score -> new ScoresDTO(
                score.getLecture_score(),
                score.getPractice_score(),
                score.getExam_score(),
                score.getId().getSubject().getTeacher(),
                score.getId().getSubject().getName()
        )).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/shedule")
    public List<SheduleDTO> getShedule(@RequestBody SheduleRequestDTO request) {
        User user = authService.getUser(request.getCode());
        List<Shedule> sheduleList = sheduleService.getShedule(user, request.getDayFrom(), request.getDayTo());
        return sheduleList.stream().map(shedule -> new SheduleDTO(
                shedule.getSubject().getName(),
                shedule.getDate().toString(),
                shedule.getStartTime().toString(),
                shedule.getEndTime().toString(),
                shedule.getSubject().getTeacher()
        )).collect(Collectors.toList());
    }

}
