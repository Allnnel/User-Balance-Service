package com.example.controller;

import com.example.model.Balance;
import com.example.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;
    @Autowired
    public BalanceController( BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    /**
     * Метод для отображения страницы с балансами пользователей.
     * Получает список всех балансов с помощью сервиса и добавляет его в модель,
     * затем возвращает имя представления для отображения страницы балансов.
     *
     * @param model Модель для передачи данных представлению.
     * @return Имя представления для отображения страницы с балансами.
     */
    @GetMapping("/balances")
    public String getUsersPage(Model model) {
        List<Balance> balances = balanceService.getAllBalances();
        model.addAttribute("balances",balances);
        return "balancesPage";
    }

    /**
     * Метод для обработки запроса на добавление нового баланса пользователя.
     * Принимает объект баланса в качестве параметра, сохраняет его с помощью сервиса
     * и перенаправляет на страницу с балансами.
     *
     * @param balance Объект баланса для сохранения.
     * @return Строка перенаправления на страницу с балансами.
     */
    @PostMapping("/balances")
    public String postUsersPage(Balance balance) {
        balanceService.save(balance);
        return "redirect:/balances";
    }


    /**
     * Метод для обработки запроса на обновление баланса пользователя.
     * Принимает объект баланса в качестве параметра, обновляет его с помощью сервиса
     * и перенаправляет на страницу с балансами.
     *
     * @param balance Объект баланса для обновления.
     * @return Строка перенаправления на страницу с балансами.
     */
    @PutMapping("/balances")
    public String putUsersPage(Balance balance) {
        balanceService.update(balance);
        return "redirect:/balances";
    }

    /**
     * Метод для обработки запроса на удаление баланса пользователя по логину.
     * Принимает логин пользователя в качестве параметра, передает его сервису для
     * выполнения операции удаления и перенаправляет на страницу с балансами.
     *
     * @param userLogin Логин пользователя, чей баланс нужно удалить.
     * @return Строка перенаправления на страницу с балансами.
     */
    @DeleteMapping("/balances/{userLogin}")
    public String deleteUsersPage(@PathVariable String userLogin){
        balanceService.deleteByUserLogin(userLogin);
        return "redirect:/balances";
    }


}
