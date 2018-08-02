package ru.job4j.web;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.*;
import ru.job4j.repository.ItemDataRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemListController.class)
public class ItemListControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemDataRepository repository;

    private Item item;

    @Before
    public void creteItem() {
        String str = "01-01-2010 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Car car = new Car(LocalDateTime.parse(str, formatter), "4WD", 10000);
        Body body = new Body("sedan", "white", "left");
        Engine engine = new Engine("disel", 3.0, 200, 100);
        GearBox gearBox = new GearBox("robot", 6);
        Manufacturer manufacturer = new Manufacturer("Lada", "Russia");
        Model model = new Model("Kalina", LocalDateTime.parse(str, formatter), true);
        car.setModel(model);
        car.setEngine(engine);
        car.setGearbox(gearBox);
        car.setBody(body);
        car.setManufacturer(manufacturer);
        User user = new User("test", "test", "test");
        user.setId(1);
        Item item = new Item("Kalina, 2010", "Want to sell test car");
        item.setId(1);
        item.setAuthor(user);
        item.setCar(car);
        this.item = item;
    }

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenFindAllThenReturnJson() throws Exception {
        given(
            this.repository.findAll()
        ).willReturn(
            new ArrayList<Item>(
                Lists.newArrayList(item)
            )
        );

        this.mvc.perform(
                get("/list").param("filter", "all")
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
            jsonPath("$", hasSize(1))
        ).andExpect(
            jsonPath("$[0].header", is("Kalina, 2010"))
        ).andExpect(
            jsonPath("$[0].text", is("Want to sell test car"))
        );
    }

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenFindLastDayThenReturnJson() throws Exception {
        given(
            this.repository.findByCreatedGreaterThanEqual(notNull())
        ).willReturn(
            new ArrayList<Item>(
                Lists.newArrayList(item)
            )
        );

        this.mvc.perform(
            get("/list").param("filter", "lastday")
        ).andExpect(
            status().isOk()
        ).andExpect(
            content().contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
            jsonPath("$", hasSize(1))
        ).andExpect(
            jsonPath("$[0].header", is("Kalina, 2010"))
        ).andExpect(
            jsonPath("$[0].text", is("Want to sell test car"))
        );
    }

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenFindWithPhotoThenReturnJson() throws Exception {
        given(
            this.repository.findByCarPhotoNotNull()
        ).willReturn(
            new ArrayList<Item>()
        );

        this.mvc.perform(
            get("/list").param("filter", "photoonly")
        ).andExpect(
            status().isOk()
        ).andExpect(
            content().contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
            jsonPath("$", hasSize(0))
        );
    }

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenFindByManufacturerThenReturnJson() throws Exception {
        given(
            this.repository.findByCarManufacturerName("Lada")
        ).willReturn(
            new ArrayList<Item>(
                Lists.newArrayList(item)
            )
        );

        this.mvc.perform(
            get("/list").param("filter", "manufacturer: Lada")
        ).andExpect(
            status().isOk()
        ).andExpect(
            content().contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
            jsonPath("$", hasSize(1))
        ).andExpect(
            jsonPath("$[0].header", is("Kalina, 2010"))
        ).andExpect(
            jsonPath("$[0].text", is("Want to sell test car"))
        );
    }

    @Test
    @WithMockUser(username = "user", authorities = "1")
    public void whenWrongFilterThenReturnJson() throws Exception {
        this.mvc.perform(
            get("/list").param("filter", "something")
        ).andExpect(
            status().isOk()
        ).andExpect(
            content().contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
            jsonPath("$", hasSize(0))
        );
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "1")
    public void whenPostThenChangeStatus() throws Exception {

        given(
            this.repository.findById(1)
        ).willReturn(
            Optional.of(item)
        );

        this.mvc.perform(
            multipart("/list").with(csrf())
                .param("id", "1")
                .param("status", "false")
        ).andExpect(
            status().isOk()
        ).andExpect(
            view().name("item_list")
        );
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "1")
    public void whenPostThenChangeStatusError() throws Exception {

        given(
            this.repository.findById(1)
        ).willReturn(
            Optional.of(item)
        );

        this.mvc.perform(
            multipart("/list").with(csrf())
                .param("id", "2")
                .param("status", "false")
        ).andExpect(
            status().isOk()
        ).andExpect(
            view().name("item_list")
        ).andExpect(
            request().sessionAttribute("error", "Status change error!")
        );
    }

}