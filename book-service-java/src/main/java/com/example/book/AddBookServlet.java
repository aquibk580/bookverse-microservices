package com.example.book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        double price = Double.parseDouble(req.getParameter("price"));

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setDouble(3, price);
            stmt.executeUpdate();

            res.setContentType("application/json");
            res.getWriter().write("{\"message\": \"Book added successfully\"}");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            res.getWriter().write("{\"error\": \"Server error\"}");
        }
    }
}
