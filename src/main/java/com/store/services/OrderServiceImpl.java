package com.store.services;

import com.store.dto.CustomerDTO;
import com.store.dto.OrderProductDTO;
import com.store.factories.OrderValueFactory;
import com.store.messaging.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Value("${kafka.topics.order-created}")
    private String orderCreatedTopic;
    private final MessageService<SpecificRecord> messageService;
    private final OrderValueFactory orderValueFactory;

    @Override
    public void create(List<OrderProductDTO> products, CustomerDTO customer) {
        Objects.requireNonNull(customer, "Customer is required to create an order!");

        var orderValue = orderValueFactory.create(products, customer);

        messageService.send(orderCreatedTopic, orderValue);
    }
}
