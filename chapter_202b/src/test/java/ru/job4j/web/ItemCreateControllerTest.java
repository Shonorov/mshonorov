package ru.job4j.web;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.domain.Item;
import ru.job4j.domain.Manufacturer;
import ru.job4j.domain.User;
import ru.job4j.repository.ItemDataRepository;
import ru.job4j.repository.UserDataRepository;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
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
    @WithMockUser(username = "user", password = "user", authorities = "1")
    public void whenPostThenCreateUser() throws Exception {

        User user = new User("user","user", "user");
        user.setId(1);

        given(
            this.userRepository.findById(1)
        ).willReturn(
            Optional.of(user)
        );

        MultiValueMap<String, String> params =  new LinkedMultiValueMap<>();
        params.put("manufactured", Collections.singletonList("2010-10-10"));
        params.put("drive", Collections.singletonList("4WD"));
        params.put("price", Collections.singletonList("10000"));
        params.put("bodytype", Collections.singletonList("sedan"));
        params.put("bodycolor", Collections.singletonList("white"));
        params.put("wheelside", Collections.singletonList("left"));
        params.put("enginetype", Collections.singletonList("disel"));
        params.put("enginevolume", Collections.singletonList("3.0"));
        params.put("enginepower", Collections.singletonList("200"));
        params.put("enginemilage", Collections.singletonList("100"));
        params.put("gearboxtype", Collections.singletonList("robot"));
        params.put("gearcount", Collections.singletonList("6"));
        params.put("manufacturer", Collections.singletonList("Lada"));
        params.put("model", Collections.singletonList("Russia"));
        params.put("releasedate", Collections.singletonList("2010-10-10"));
        params.put("manufacturing", Collections.singletonList("true"));
        params.put("header", Collections.singletonList("Kalina, 2010"));
        params.put("text", Collections.singletonList("Want to sell test car"));

        MockMultipartFile file = new MockMultipartFile("photo", "carphoto.jpg", null, "".getBytes());

        this.mvc.perform(
            multipart("/create").file(file).with(csrf()).params(params)
                .param("photo", "empty_photo")
        ).andExpect(
            status().isOk()
        ).andExpect(
            view().name("item_list")
        );
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "1")
    public void whenPostWrongParamsThenCreateFails() throws Exception {

        MockMultipartFile file = new MockMultipartFile("photo", "carphoto.jpg", null, "".getBytes());

        this.mvc.perform(
            multipart("/create").file(file).with(csrf())
                .param("photo", "empty_photo")
        ).andExpect(
            status().isOk()
        ).andExpect(
            view().name("item_list")
        ).andExpect(
            request().sessionAttribute("error", "Item create error!")
        );
    }
}