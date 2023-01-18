package com.store.services;

import com.store.dto.OrderProductDTO;
import com.store.entity.Customer;
import com.store.messaging.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MessageService<SpecificRecord> messageService;

    @Override
    public void create(List<OrderProductDTO> products, Customer customer) {
        var orderPrice = this.calculateOrderPrice(products);
    }

    private Double calculateOrderPrice(List<OrderProductDTO> products) {
        return products.stream()
                .mapToDouble(p -> p.getProduct().getPrice() * p.getAmount())
                .sum();

    }
}
