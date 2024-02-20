import com.example.UserBalanceServiceApplication;
import com.example.model.Balance;
import com.example.model.User;
import com.example.repository.BalanceRepository;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserBalanceServiceApplication.class)
@AutoConfigureMockMvc
public class ControllerTest {
  @Autowired private MockMvc mockMvc;
  @Autowired private UserRepository userRepository;
  @Autowired private BalanceRepository balanceRepository;

  // ------------------- POST ------------------------------------

  @Test
  public void testPostUser() throws Exception {
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
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users")
                .content(userRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));
    // Получаем сохраненного пользователя из базы данных
    User savedUser =
        userRepository
            .findByLogin("example_user")
            .get(); // Предполагается, что у вас есть UserRepository
    // создаем баланс с использованием сохраненного пользователя
    Balance balance = new Balance(savedUser, 300);
    String balanceRequestBody = objectMapper.writeValueAsString(balance);
    // Отправка POST-запроса для сохранения баланса
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/balances")
                .content(balanceRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/balances"));
    User user5 = userRepository.findByLogin("example_user").get();
    user5.setEmail("5555555");
    // Преобразование объекта User в JSON
    ObjectMapper objectMapper4 = new ObjectMapper();
    // Отправка PUT-запроса для сохранения баланса
    String requestBody = objectMapper4.writeValueAsString(user5);
    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/users")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));
  }

  // ------------------- DELETE ------------------------------------
  @Test
  @Transactional
  public void testDeleteUser() throws Exception {
    User user = new User();
    user.setLogin("deleteUser");
    user.setPasswordHash("password666");
    user.setEmail("user@example.com");
    user.setBirthDay("1990-05-15");
    user.setMobilePhone("123-456-7890");
    ObjectMapper objectMapper = new ObjectMapper();
    String userRequestBody = objectMapper.writeValueAsString(user);

    // Отправка PUT-запроса для сохранения пользователя
    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/users")
                .content(userRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));

    User newUser = userRepository.findByLogin("deleteUser").get();
    Balance balance = new Balance(newUser, 200);
    String balanceRequestBody = objectMapper.writeValueAsString(balance);
    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/balances")
                .content(balanceRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/balances"));

    String login = "deleteUser";
    String userLogin = "deleteUser";
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/balances/{userLogin}", userLogin))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/balances"));

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/users/{login}", login))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));
  }

  // ------------------- GET ------------------------------------
  @Test
  public void testGetBalancesPage() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/balances"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("balancesPage"));
  }

  @Test
  public void testGetUsersPage() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("usersPage"));
  }
}
