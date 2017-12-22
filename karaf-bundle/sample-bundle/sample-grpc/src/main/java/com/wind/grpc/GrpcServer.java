package com.wind.grpc;

import com.wind.grpc.service.GreeterImpl;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author wind
 */
public class GrpcServer {

    private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());

    /**
     * The port on which the server should run
     */
    private int port = 50051;
    private Server server;

    private static List<BindableService> serviceList = new ArrayList<>();

    private void start() throws IOException {
        ServerBuilder<?> builder = ServerBuilder.forPort(port);
        serviceList.add(new GreeterImpl());
        serviceList.forEach(service -> builder.addService(service));
        server = builder.build().start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();
    }
}
