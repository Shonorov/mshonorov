package ru.job4j.cars.controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.job4j.cars.CarsRepository;
import ru.job4j.cars.model.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Car manufacturers list management servlet.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ManufacturerListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<Manufacturer> entities = CarsRepository.getInstance().getAllManufacturers();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = builder.create();
        JsonArray jarray = gson.toJsonTree(entities).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("manufacturers", jarray);
        writer.append(jsonObject.toString());
        writer.flush();
    }
}
