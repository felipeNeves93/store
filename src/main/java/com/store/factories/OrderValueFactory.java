package com.store.factories;

import com.store.avro.orders.CustomerValue;
import com.store.avro.orders.OrderValue;
import com.store.avro.orders.ProductValue;
import com.store.dto.CustomerDTO;
import com.store.dto.OrderProductDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderValueFactory {

    private final ModelMapper modelMapper;

    public OrderValue create(List<OrderProductDTO> produts, CustomerDTO customer) {
        var orderValue = new OrderValue();
        var orderPrice = this.calculateOrderPrice(produts);

        var convertedCustomer = modelMapper.map(customer, CustomerValue.class);
        var convertedProducts = this.convertOrderProductsToProducts(produts);

        orderValue.setProducts(convertedProducts);
        orderValue.setCustomer(convertedCustomer);
        orderValue.setPrice(orderPrice);
        orderValue.setCreationDate(LocalDate.now().toString());

        return orderValue;
    }

    private Double calculateOrderPrice(List<OrderProductDTO> products) {
        return products.stream()
                .mapToDouble(p -> p.getProduct().getPrice() * p.getAmount())
                .sum();

    }

    private List<ProductValue> convertOrderProductsToProducts(List<OrderProductDTO> products) {
        final var productValues = new ArrayList<ProductValue>();

        products.forEach(orderProduct -> {
            var productValue = modelMapper.map(orderProduct.getProduct(), ProductValue.class);
            productValue.setAmount(orderProduct.getAmount());

            productValues.add(productValue);
        });

        return productValues;
    }
}
