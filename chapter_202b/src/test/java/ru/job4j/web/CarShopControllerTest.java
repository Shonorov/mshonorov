package ru.job4j.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(CarShopController.class)
public class CarShopControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenGetThenReturnPage() throws Exception {
        this.mvc.perform(
                get("/shop")
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("item_list")
        );
    }

}