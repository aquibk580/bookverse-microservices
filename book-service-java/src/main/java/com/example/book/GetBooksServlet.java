package com.example.book;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        out.write("[");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            boolean first = true;
            while (rs.next()) {
                if (!first) out.write(",");
                out.write(String.format(
                    "{\"id\": %d, \"title\": \"%s\", \"author\": \"%s\", \"price\": %.2f}",
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getDouble("price")
                ));
                first = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.write("]");
    }
}
