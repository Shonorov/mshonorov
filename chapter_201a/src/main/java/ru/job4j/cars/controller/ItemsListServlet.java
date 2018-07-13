package ru.job4j.cars.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.job4j.cars.CarsRepository;
import ru.job4j.cars.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Item list management servlet.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ItemsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String filter = req.getParameter("filter");
        String key = filter.contains("manufacturer") ? "manufacturer" : filter;
        Map<String, Supplier<List<Item>>> filterMap = new HashMap<String, Supplier<List<Item>>>() {
            {
                put("all", () -> CarsRepository.getInstance().getAllItems());
                put("lastday", () -> CarsRepository.getInstance().getAllItemsLastDay(1L));
                put("photoonly", () -> CarsRepository.getInstance().getAllItemsByPhoto(true));
                put("manufacturer", () -> CarsRepository.getInstance().getItemsByManufacturer(filter.substring(14, filter.length())));
            }
        };
        List<Item> items = filterMap.get(key).get();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = builder.create();
        JsonArray jarray = gson.toJsonTree(items).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("items", jarray);
        writer.append(jsonObject.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarsRepository.getInstance().setStatus(req.getParameter("id"), Boolean.valueOf(req.getParameter("status")));
        req.getRequestDispatcher("/WEB-INF/views/item_list.html").forward(req, resp);
    }
}
