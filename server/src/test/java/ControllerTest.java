import com.example.UserBalanceServiceApplication;

import com.example.model.Balance;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.PrintStream;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserBalanceServiceApplication.class)
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;


    // ------------------- GET ------------------------------------
    @Test
    public void testGetBalancesPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/balances"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("balancesPage"));
    }

    @Test
    public void testGetUsersPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("usersPage"));
    }



    // ------------------- POST ------------------------------------



    @Test
    public void testPostUser() throws Exception {
        // Создание объекта User
        User user = new User();
        user.setLogin("example_user");
        user.setPasswordHash("password666");
        user.setEmail("user@example.com");
        user.setBirthDay("1990-05-15");
        user.setMobilePhone("123-456-7890");

        // Преобразование объекта User в JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String userRequestBody = objectMapper.writeValueAsString(user);

        // Отправка POST-запроса для сохранения пользователя
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(userRequestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));

        // Получаем сохраненного пользователя из базы данных
        User savedUser = userRepository.findByLogin("example_user").get(); // Предполагается, что у вас есть UserRepository

        // Теперь создаем баланс с использованием сохраненного пользователя
        Balance balance = new Balance(savedUser, 300);
        String balanceRequestBody = objectMapper.writeValueAsString(balance);

        // Отправка POST-запроса для сохранения баланса
        mockMvc.perform(MockMvcRequestBuilders.post("/balances")
                        .content(balanceRequestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/balances"));


        User user5 = userRepository.findByLogin("example_user").get();
        user5.setEmail("5555555");
        // Преобразование объекта User в JSON
        ObjectMapper objectMapper4 = new ObjectMapper();
        String requestBody = objectMapper4.writeValueAsString(user5);

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));
    }






}