package ru.job4j.web;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.repository.ItemDataRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemListController.class)
public class ItemListControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemDataRepository repository;

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenGetThenReturnPage() throws Exception {
        this.mvc.perform(
                get("/list").param("filter", "all")
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().contentType(MediaType.APPLICATION_JSON_UTF8)
        );
    }
}