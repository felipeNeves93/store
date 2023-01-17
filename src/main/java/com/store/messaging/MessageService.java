package com.store.messaging;

public interface MessageService<T> {

    void send(String destination, T payload);
}
