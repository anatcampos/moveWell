package mindera.porto.moveWell.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import mindera.porto.moveWell.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends BaseControllerTest {

    //TO DO fazer o autowired dos repositorios
    //TO DO fazer
    @Test
    void shoouldCreateUser() throws Exception {
        User user = new User();
        user.setName("Teresa");
        user.setPassword("123456");
        user.setAge(23);
        user.setUsername("teresacc");

        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))) //pega no user e passa para String
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Teresa"))
                .andExpect(jsonPath("$.age").value(23))
                .andExpect(jsonPath("$.username").value("teresacc"));
    }
}
