/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RolfMoikjær
 */
@WebServlet(name = "Servlet1", urlPatterns = {"/api/quote/*"})
public class Servlet1 extends HttpServlet {

    private Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    String getParam(HttpServletRequest res) {
        String[] parts = res.getRequestURI().split("/");
        String parameter = null;
        if (parts.length == 5) {
            parameter = parts[4];
        }
        return parameter;
    }

    protected void makeResponse(String responseString, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(responseString);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = getParam(request);
        int id = Integer.parseInt(idString);

        String quote = quotes.get(id);
        JsonObject json = new JsonObject();
        json.addProperty("quote", quote);

        makeResponse(new Gson().toJson(json), response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Scanner jsonScanner = new Scanner(request.getInputStream());
        String json = "";
        while (jsonScanner.hasNext()) {
            json += jsonScanner.nextLine();
        }
        //Get the quote text from the provided Json
        JsonObject newQuote = new JsonParser().parse(json).getAsJsonObject();
        String quote = newQuote.get("quote").getAsString();
        Integer nextId = quotes.size()+1;
        quotes.put(nextId++, quote);

        JsonObject json1 = new JsonObject();
        json1.addProperty("quote", quotes.get(quotes.size()));
        makeResponse(new Gson().toJson(json1), response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = getParam(request);
        int id = Integer.parseInt(idString);

        Scanner jsonScanner = new Scanner(request.getInputStream());
        String json = "";
        while (jsonScanner.hasNext()) {
            json += jsonScanner.nextLine();
        }

        JsonObject newQuote = new JsonParser().parse(json).getAsJsonObject();
        String quote = newQuote.get("quote").getAsString();
        quotes.put(id, quote);

        JsonObject json1 = new JsonObject();
        json1.addProperty("quote", quote);
        makeResponse(new Gson().toJson(json1), response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = getParam(request);
        int id = Integer.parseInt(idString);
        
        String removed = quotes.get(id);
        JsonObject json1 = new JsonObject();        
        json1.addProperty("quote", removed);
        
        quotes.remove(id);

        makeResponse(new Gson().toJson(json1), response);
    }

}
