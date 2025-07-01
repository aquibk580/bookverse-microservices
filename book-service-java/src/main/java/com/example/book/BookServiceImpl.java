// src/main/java/com/example/book/BookServiceImpl.java

package com.example.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import book.BookOuterClass.Book;
import book.BookOuterClass.BookList;
import book.BookOuterClass.Empty;
import book.BookServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {

    @Override
    public void getBooks(Empty request, StreamObserver<BookList> responseObserver) {
        BookList.Builder bookList = BookList.newBuilder();

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = Book.newBuilder()
                        .setId(rs.getInt("id"))
                        .setTitle(rs.getString("title"))
                        .setAuthor(rs.getString("author"))
                        .setPrice(rs.getFloat("price"))
                        .build();

                bookList.addBooks(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        responseObserver.onNext(bookList.build());
        responseObserver.onCompleted();
    }

    @Override
    public void addBook(Book request, StreamObserver<Empty> responseObserver) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO books (title, author, price) VALUES (?, ?, ?)");

            stmt.setString(1, request.getTitle());
            stmt.setString(2, request.getAuthor());
            stmt.setFloat(3, request.getPrice());

            stmt.executeUpdate();
            System.out.println("✅ Book added: " + request.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        }

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteBook(Book request, StreamObserver<Empty> responseObserver) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id = ?");
            stmt.setInt(1, request.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    // Similarly add addBook() and deleteBook() later
}
