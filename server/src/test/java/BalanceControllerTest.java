import com.example.UserBalanceServiceApplication;
import com.example.controller.BalanceController;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserBalanceServiceApplication.class)
@AutoConfigureMockMvc
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsersPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/balances"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("balancesPage"));
    }
//
//    // 404
    @Test
    public void testPutUsersPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/balances"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/balances"));
    }

    @Test
    public void testPutUsersBodyPage() throws Exception {
        // Создание JSON объекта для отправки
        JSONObject requestBody = new JSONObject();
        requestBody.put("userLogin", "rhogoron");
        requestBody.put("amount", "100");

        mockMvc.perform(MockMvcRequestBuilders.put("/balances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody.toString()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("redirect:/balances"));
    }


}
