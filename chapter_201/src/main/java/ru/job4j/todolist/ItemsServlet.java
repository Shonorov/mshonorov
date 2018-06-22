package ru.job4j.todolist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<Item> items;
        if (!req.getParameter("done").equals("true")) {
            items = ToDoListRepository.getInstance().getOnlyCurrentItems();
        } else {
            items = ToDoListRepository.getInstance().getAllItems();
        }
        Gson gson = new GsonBuilder().create();
        JsonArray jarray = gson.toJsonTree(items).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("items", jarray);
        writer.append(jsonObject.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null && req.getParameter("done") != null) {
            ToDoListRepository.getInstance().setDone(req.getParameter("id"), Boolean.valueOf(req.getParameter("done")));
        }
        if (req.getParameter("desc") != null) {
            ToDoListRepository.getInstance().createItem(req.getParameter("desc"));
        }
        req.getRequestDispatcher("/").forward(req, resp);
    }

}
