package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.domain.Item;
import ru.job4j.repository.ItemDataRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;

/**
 * Item list spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class ItemListController {

    @Autowired
    private ItemDataRepository repository;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Item> getItems(@RequestParam("filter") String filter) {
        String key = filter.contains("manufacturer") ? "manufacturer" : filter;
        Map<String, Supplier<List<Item>>> filterMap = new HashMap<String, Supplier<List<Item>>>() {
            {
                put("all", () -> (List<Item>) repository.findAll());
                put("lastday", () -> repository.findByCreatedGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
                put("photoonly", () -> repository.findByCarPhotoNotNull());
                put("manufacturer", () -> repository.findByCarManufacturerName(filter.substring(14, filter.length())));
            }
        };
        return filterMap.keySet().contains(key) ? filterMap.get(key).get() : new ArrayList<Item>();
    }

    @RequestMapping (value = "/list", method = RequestMethod.POST)
    public String changeStatus(@RequestParam("id") String id, @RequestParam("status") String status, HttpServletRequest request) {
        Optional<Item> result = repository.findById(Integer.valueOf(id));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = "";
        if (principal instanceof UserDetails) {
            currentUserId = ((UserDetails) principal).getAuthorities().toArray()[0].toString();
        }
        if (result.isPresent() && String.valueOf(result.get().getAuthor().getId()).equals(currentUserId)) {
            Item update = repository.findById(Integer.valueOf(id)).get();
            update.setSold(Boolean.valueOf(status));
            repository.save(update);
        } else {
            request.getSession().setAttribute("error", "Status change error!");
        }
        return "item_list";
    }
}
