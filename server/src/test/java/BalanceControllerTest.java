import com.example.UserBalanceServiceApplication;
import com.example.controller.BalanceController;
import com.example.model.Balance;
import com.example.model.User;
import com.example.service.BalanceService;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserBalanceServiceApplication.class)
//@WebMvcTest(BalanceController.class)
@AutoConfigureMockMvc
 class BalanceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BalanceService balanceService;
    @MockBean
    private UserService userService;

    private Balance balance1;
    private Balance balance2;

    @BeforeEach
    void setUp() {
        User user1 = new User(1, "rhogoron", "123123", "rhogoron@mail.ru",
                Date.valueOf("2002-02-17"), "+123123");
        User user2 = new User(2, "abella", "123123", "abella@mail.ru",
                Date.valueOf("2001-05-05"), "+123423");

        when(userService.findById(1)).thenReturn(user1);
        when(userService.findById(2)).thenReturn(user2);

        balance1 = new Balance(user1);
        balance1.setId(1L);
        balance1.setAmount(100.0);

        balance2 = new Balance(user2);
        balance2.setId(2L);
        balance2.setAmount(200.0);
    }


    @Test
    public void testGetUsersPage() throws Exception {
        out.println("1");
        List<Balance> balances = Arrays.asList(balance1, balance2);
        when(balanceService.getAllBalances()).thenReturn(balances);

        mockMvc.perform(MockMvcRequestBuilders.get("/balances"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("balances"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("balances"));
    }
}
