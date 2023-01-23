package com.store.factories;

import com.store.avro.orders.CustomerValue;
import com.store.avro.orders.OrderValue;
import com.store.avro.orders.ProductValue;
import com.store.dto.CustomerDTO;
import com.store.dto.OrderProductDTO;
import com.store.services.crud.ProductCrudService;
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

    private final ProductCrudService productCrudService;

    public OrderValue create(List<OrderProductDTO> produts, CustomerDTO customer) {
        var orderValue = new OrderValue();

        var convertedCustomer = modelMapper.map(customer, CustomerValue.class);
        var convertedProducts = this.convertOrderProductsToProducts(produts);

        orderValue.setProducts(convertedProducts);
        orderValue.setCustomer(convertedCustomer);
        orderValue.setPrice(this.calculateOrderPrice(orderValue.getProducts()));
        orderValue.setCreationDate(LocalDate.now().toString());

        return orderValue;
    }

    private Double calculateOrderPrice(List<ProductValue> products) {
        return products.stream().mapToDouble(p -> p.getPrice() * p.getAmount()).sum();
    }

    private List<ProductValue> convertOrderProductsToProducts(List<OrderProductDTO> orderProducts) {
        final var productValues = new ArrayList<ProductValue>();

        orderProducts.forEach(orderProduct -> {
            var product = productCrudService.findById(orderProduct.getProductId()).orElseThrow(() -> new IllegalStateException("Product wasn't found with id " + orderProduct.getProductId()));
            var convertedProduct = modelMapper.map(product, ProductValue.class);

            convertedProduct.setAmount(orderProduct.getAmount());
            productValues.add(convertedProduct);
        });

        return productValues;
    }
}
