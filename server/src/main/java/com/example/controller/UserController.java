package com.example.controller;

import com.example.model.Balance;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController( UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод контроллера для обработки GET-запроса по адресу "/users".
     * Получает список пользователей из сервиса и передает его в представление.
     *
     * @param model объект Model, предоставляемый Spring MVC для передачи данных в представление.
     * @return имя представления, которое будет отображено пользователю.
     */
    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "usersPage";
    }


    /**
     * Метод контроллера для обработки POST-запроса по адресу "/users".
     * Этот метод принимает данные формы, отправленные пользователем, и выполняет необходимые действия.
     * Сохраняет нового пользователя в базе данных.
     *
     * @param user объект пользователя, переданный из формы на веб-странице.
     * @return имя представления или URL-адрес для перенаправления.
     */
    @PostMapping("/users")
    public String postUsersPage(@RequestBody User user) {
        userService.save(user);
        return "redirect:/users";
    }

    /**
     * Метод контроллера для обновления данных пользователя по адресу "/users".
     *
     * @param user объект пользователя, содержащий обновленные данные.
     * @return имя представления или URL-адрес для перенаправления.
     */
    @PutMapping("/users")
    public String putUsersPage(@RequestBody User user) {
        userService.update(user);
        return "redirect:/users";
    }

    /**
     * Метод контроллера для удаления пользователя по логину.
     * Принимает логин пользователя в качестве параметра из пути URL и передает его
     * сервису пользователя для выполнения операции удаления. После успешного удаления
     * пользователя перенаправляет пользователя на страницу пользователей.
     *
     * @param login Логин пользователя, которого нужно удалить.
     * @return Строка перенаправления на страницу пользователей.
     */
    @DeleteMapping("/users/{login}")
    public String deleteUsersPage(@PathVariable String login) {
        userService.deleteByLogin(login);
        return "redirect:/users";
    }

}
