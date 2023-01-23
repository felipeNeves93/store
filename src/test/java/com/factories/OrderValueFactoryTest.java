package com.factories;

import com.store.dto.CustomerDTO;
import com.store.dto.OrderProductDTO;
import com.store.dto.ProductDTO;
import com.store.factories.OrderValueFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderValueFactoryTest {

    private OrderValueFactory orderValueFactory;

    @BeforeAll
    void setup() {
        this.orderValueFactory = new OrderValueFactory(new ModelMapper());
    }

    @Test
    void whenCreatingOrderValueTheShouldReturnCreatedOrderValue() {
        final var excpectedPrice = 10_000.0;
        final var expectedCreatedDate = LocalDate.now().toString();

        var customerDTO = this.createCustomerDTO();
        var orderProducts = Arrays.asList(OrderProductDTO.builder().product(this.createProductDTO()).amount(5).build(), OrderProductDTO.builder().product(this.createProductDTO()).amount(5).build());

        var orderValue = orderValueFactory.create(orderProducts, customerDTO);

        assertThat(orderValue.getPrice()).isEqualTo(excpectedPrice);
        assertThat(orderValue.getCreationDate()).isEqualTo(expectedCreatedDate);
        assertThat(orderValue.getProducts()).hasSize(orderProducts.size());
        assertThat(orderValue.getCustomer().getName()).isEqualTo(customerDTO.getName());
    }

    private ProductDTO createProductDTO() {
        return ProductDTO.builder()
                .price(1_000)
                .id(1L)
                .creationDate(LocalDate.now())
                .name("Product")
                .description("A simple Product")
                .stock(50)
                .build();
    }

    private CustomerDTO createCustomerDTO() {
        return CustomerDTO.builder()
                .id(1L)
                .active(true)
                .birthDate(LocalDate.now())
                .email("email@email.com")
                .createdDate(LocalDateTime.now())
                .name("Felipe")
                .lastName("Neves")
                .build();
    }


}
