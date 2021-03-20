package fr.esiea.ex4A.api;

import fr.esiea.ex4A.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ApiControllerTest {

    private final MockMvc mockMvc;

    ApiControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void add_user_test() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.post(
                "/api/inscription").contentType(MediaType.APPLICATION_JSON).content("""
                  {
                    "userEmail": "machin@truc.com",
                    "userName": "machin",
                    "userTweeter": "machin45",
                    "userCountry": "FR",
                    "userSex": "M",
                    "userSexPref": "M"
                  }      
                """)
            )
            .andExpect(status().isCreated())
            .andExpect(content().json("""
                {
                   "userEmail": "machin@truc.com",
                   "userName": "machin",
                   "userTweeter": "machin45",
                   "userCountry": "FR",
                   "userSex": "M",
                   "userSexPref": "M"
                 }
                """));
    }
    @Test
    void get_matches_test() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/matches")
                .queryParam("userName", "machin")
                .queryParam("userCountry", "FR")
            )
            .andExpect(status().isOk());
    }
}
