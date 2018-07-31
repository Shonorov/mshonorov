package ru.job4j.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.Manufacturer;
import ru.job4j.repository.ManufacturerDataRepository;

import java.util.ArrayList;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ManufacturerListController.class)
public class ManufacturerListControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ManufacturerDataRepository repository;

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenGetThenReturnPage() throws Exception {
        given(
                this.repository.findAll()
        ).willReturn(
                new ArrayList<Manufacturer>(
                        Lists.newArrayList(new Manufacturer("Lada", "Russia"))
                )
        );

        this.mvc.perform(
                post("/manufacturers").with(csrf())
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                jsonPath("$", hasSize(1))
        ).andExpect(
                jsonPath("$[0].name", is("Lada"))
        ).andExpect(
                jsonPath("$[0].country", is("Russia"))
        );
    }
}