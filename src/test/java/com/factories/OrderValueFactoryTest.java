package com.factories;

import com.store.dto.CustomerDTO;
import com.store.dto.OrderProductDTO;
import com.store.entity.Product;
import com.store.factories.OrderValueFactory;
import com.store.services.crud.ProductCrudService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderValueFactoryTest {

    private OrderValueFactory orderValueFactory;
    private ProductCrudService productCrudService;

    @BeforeAll
    void setup() {
        productCrudService = Mockito.mock(ProductCrudService.class);
        this.orderValueFactory = new OrderValueFactory(new ModelMapper(), productCrudService);
    }

    @Test
    void whenCreatingOrderValueTheShouldReturnCreatedOrderValue() {
        when(productCrudService.findById(any())).thenReturn(Optional.of(this.createProduct()));

        final var excpectedPrice = 10_000.0;
        final var expectedCreatedDate = LocalDate.now().toString();

        var customerDTO = this.createCustomerDTO();

        var orderProducts = Arrays.asList(OrderProductDTO.builder().productId(1L).amount(5).build(), OrderProductDTO.builder().amount(5).build());

        var orderValue = orderValueFactory.create(orderProducts, customerDTO);

        assertThat(orderValue.getPrice()).isEqualTo(excpectedPrice);
        assertThat(orderValue.getCreationDate()).isEqualTo(expectedCreatedDate);
        assertThat(orderValue.getProducts()).hasSize(orderProducts.size());
        assertThat(orderValue.getCustomer().getName()).isEqualTo(customerDTO.getName());
    }

    private Product createProduct() {
        return Product.builder()
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
