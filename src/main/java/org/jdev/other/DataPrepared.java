package org.jdev.other;

import org.jdev.entity.Scores;
import org.jdev.entity.Shedule;
import org.jdev.entity.Subject;
import org.jdev.entity.User;
import org.jdev.repository.ScoresRepository;
import org.jdev.repository.SheduleRepository;
import org.jdev.repository.SubjectRepository;
import org.jdev.repository.UserRepository;
import org.jdev.service.AuthService;
import org.jdev.service.RequestToVkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

@Service
public class DataPrepared {


    private final ScoresRepository scoresRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final SheduleRepository sheduleRepository;
    private final RequestToVkService requestToVkService;


    @Autowired
    public DataPrepared(ScoresRepository scoresRepository, UserRepository userRepository, SubjectRepository subjectRepository, SheduleRepository sheduleRepository, RequestToVkService requestToVkService) {
        this.scoresRepository = scoresRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.sheduleRepository = sheduleRepository;
        this.requestToVkService = requestToVkService;
    }

    public void userTest(User user) {

        // Создание предметов
        Subject math = new Subject(1, "Математика", "Петров Петр");
        Subject physics = new Subject(2, "Физика", "Сидоров Сидор");
        Subject chemistry = new Subject(3, "Химия", "Алексеев Алексей");
        Subject other = new Subject(4, "Сетевые протоколы", "Иванов Иван");

        subjectRepository.saveAll(Arrays.asList(math, physics, chemistry, other));
        // Создание оценок для пользователя
        Scores score1 = new Scores(user, math, 85, 90, 88);
        Scores score2 = new Scores(user, physics, 78, 82, 80);
        Scores score3 = new Scores(user, chemistry, 92, 95, 90);
        Scores score4 = new Scores(user, other, 0, 0 ,0);
        scoresRepository.saveAll(Arrays.asList(score1, score2, score3, score4));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(calendar.get(Calendar.YEAR)-1900,calendar.get(Calendar.MONTH),  Math.max(calendar.get(Calendar.DAY_OF_MONTH)-3*6,0)));
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){

                if (random.nextInt()%3 == 0) {
                    sheduleRepository.save(new Shedule(LocalTime.of(i,j), LocalTime.of(i+1,j + 30),
                            new Date(calendar.get(Calendar.YEAR)-1900,calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)),
                            math,i*j%2));

                }
                if (random.nextInt()%3 == 0) {
                    sheduleRepository.save(new Shedule(LocalTime.of(i+3,j), LocalTime.of(i+4,j + 30),
                            new Date(calendar.get(Calendar.YEAR)-1900,calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)),
                            physics,i*j%2));
                }
                if (random.nextInt()%5 == 0) {
                    sheduleRepository.save(new Shedule(LocalTime.of(i+5,j), LocalTime.of(i+6,j + 30),
                            new Date(calendar.get(Calendar.YEAR)-1900,calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)),
                            chemistry,i*j%2));
                }
                if (random.nextInt()%3 == 0) {
                    sheduleRepository.save(new Shedule(LocalTime.of(i + 7, j), LocalTime.of(i + 8, j + 30),
                            new Date(calendar.get(Calendar.YEAR)-1900,calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)),
                            chemistry, i*j % 2));
                }

                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

        }

        System.out.println("Тестовые данные успешно загружены!");

    }

    public void PrepareOther(){
        int baseId = 303800000;
        for (int i = 0; i < 10; i++){
            userRepository.save(new User(baseId + i,"noData"));
        }
    }




}
