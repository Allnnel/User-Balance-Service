package com.example.app;

import com.example.UserBalanceServiceApplication;
import com.example.model.Balance;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.service.BalanceService;

import java.time.LocalDate;
import java.util.Date;

import static java.lang.System.out;


public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(UserBalanceServiceApplication.class)) {
            BalanceService balanceService = context.getBean(BalanceService.class);
            UserService userService = context.getBean(UserService.class);

            for(int i = 10; i != 11; i++) {
                balanceService.deleteById(i);
            }

            for(int i = 16; i != 17; i++) {
                userService.deleteById(i);
            }
//
//            User user1 = new User( "rhogoron1", "123",
//                    "rhogoron@mail.ru", LocalDate.of(1990, 5, 15), "+456789");
//            User user2 = new User( "rhogoron2", "123",
//                    "rhogoron@mail.ru", LocalDate.of(1990, 5, 15), "+456789");
//            User user3 = new User( "rhogoron3", "123",
//                    "rhogoron@mail.ru", LocalDate.of(1990, 5, 15), "+456789");
//            User user4 = new User( "rhogoron4", "123",
//                    "rhogoron@mail.ru", LocalDate.of(1990, 5, 15), "+456789");
//            User user5 = new User( "rhogoron5", "123",
//                    "rhogoron@mail.ru", LocalDate.of(1990, 5, 15), "+456789");
//            userService.save(user1);
//            userService.save(user2);
//            userService.save(user3);
//            userService.save(user4);
//            userService.save(user5);
//
//            balanceService.save(new Balance(user1, 200));
//            balanceService.save(new Balance(user2, 200));
//            balanceService.save(new Balance(user3, 500));
//            balanceService.save(new Balance(user4, 200));
//            balanceService.save(new Balance(user5, 300));


        }
    }
}

