package com.example.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        String idParam = req.getParameter("id");

        if (idParam == null) {
            res.setStatus(400);
            out.write("{\"error\":\"Book ID is required\"}");
            return;
        }

        int bookId = Integer.parseInt(idParam);

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id = ?");
            stmt.setInt(1, bookId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                out.write("{\"message\":\"Book deleted successfully\"}");
            } else {
                res.setStatus(404);
                out.write("{\"error\":\"Book not found\"}");
            }

        } catch (Exception e) {
            res.setStatus(500);
            out.write("{\"error\":\"Failed to delete book\"}");
            e.printStackTrace();
        }
    }
}
