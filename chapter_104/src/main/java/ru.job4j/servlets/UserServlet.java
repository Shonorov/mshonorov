package ru.job4j.servlets;

import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Presentation layout for web application
 * Servlet for users management with REST API.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserServlet extends HttpServlet {
    /**
     * Map of action parameters and functions.
     * Application logic singleton.
     */
    private HashMap<String, Function<String, Boolean>> dispatch = new HashMap<>();
    private final ValidateService logic = ValidateService.getInstance();

    /**
     * Write all users.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException servlet error.
     * @throws IOException output error.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.format("%s | %s | %s | %s | %s", "id", "name", "login", "email", "createDate")).append(System.lineSeparator());
        for (User user : logic.findAll()) {
            writer.append(user.toString()).append(System.lineSeparator());
        }
        writer.flush();
    }

    /**
     * Do action depended on "action" parameter.
     * Can "create", "update", "delete" user.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException servlet error.
     * @throws IOException output error.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.initiate(req, resp);
        String action = req.getParameter("action");
        this.dispatch.get(action).apply(action);
    }

    /**
     * Add user to local store.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     * @return create user function.
     */
    private Function<String, Boolean> createUser(HttpServletRequest req, HttpServletResponse resp) {
        return new Function<String, Boolean>() {
            @Override
            public Boolean apply(String msg) {
                boolean result = false;
                User current = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
                String message;
                if (logic.add(current)) {
                    message = "User created!";
                    result = true;
                } else {
                    message = "User already exists!";
                }
                response(message, resp);
                return result;
            }
        };
    }
    /**
     * Update user in local store.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     * @return update user function.
     */
    private Function<String, Boolean> updateUser(HttpServletRequest req, HttpServletResponse resp) {
        return new Function<String, Boolean>() {
            @Override
            public Boolean apply(String msg) {
                boolean result = false;
                User current = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
                String message = "";
//                if (logic.update(current, req.getParameter("newname"), req.getParameter("newlogin"), req.getParameter("newemail"))) {
//                    message = "User updated!";
//                    result = true;
//                } else {
//                    message = "User not found!";
//                }
                response(message, resp);
                return result;
            }
        };
    }
    /**
     * Remove user from local store.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     * @return delete user function.
     */
    private Function<String, Boolean> deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        return new Function<String, Boolean>() {
            @Override
            public Boolean apply(String msg) {
                boolean result = false;
                User current = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
                String message;
                if (logic.delete(current)) {
                    message = "User deleted!";
                    result = true;
                } else {
                    message = "User not found!";
                }
                response(message, resp);
                return result;
            }
        };
    }

    /**
     * Fill available functions map.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     */
    private void initiate(HttpServletRequest req, HttpServletResponse resp) {
        this.load("create", this.createUser(req, resp));
//        this.load("update", this.updateUser(req, resp));
        this.load("delete", this.deleteUser(req, resp));
    }

    /**
     * Add function to the map.
     * @param type action type.
     * @param handle action function.
     */
    private void load(String type, Function<String, Boolean> handle) {
        this.dispatch.put(type, handle);
    }

    /**
     * Write action response.
     * @param message message text.
     * @param resp HttpServletResponse.
     */
    private void response(String message, HttpServletResponse resp) {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(message).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
