package com.example.book;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class BookGrpcServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(9090)
                .addService(new BookServiceImpl())
                .build();

        System.out.println("🚀 gRPC Server started on port 9090");
        server.start();
        server.awaitTermination();
    }
}
