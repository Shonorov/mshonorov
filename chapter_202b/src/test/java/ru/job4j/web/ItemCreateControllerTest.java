package ru.job4j.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.repository.ItemDataRepository;
import ru.job4j.repository.UserDataRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemCreateController.class)
public class ItemCreateControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemDataRepository itemRepository;
    @MockBean
    private UserDataRepository userRepository;

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenGetThenReturnPage() throws Exception {
        this.mvc.perform(
                get("/create")
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("item_create")
        );
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "17")
    public void whenPostThenCreateUser() throws Exception {


        MockMultipartFile file = new MockMultipartFile("photo", "carphoto.jpg", null, "".getBytes());
        this.mvc.perform(
                post("/create")//.with(csrf())
                        .param("photo", "empty_value")
//                multipart("/create").file(file)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("item_list")
        );
    }
}