package com.wind.grpc.service;

import com.wind.grpc.service.helloworld.GreeterGrpc;
import com.wind.grpc.service.helloworld.HelloReply;
import com.wind.grpc.service.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wind
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    /** 原子Integer */
    public AtomicInteger count = new AtomicInteger(0);

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        System.out.println("call sayHello");
        System.out.println(req.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        System.out.println(count.incrementAndGet() + Thread.currentThread().getName());
    }
}
