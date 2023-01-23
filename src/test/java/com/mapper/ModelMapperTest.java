package com.mapper;

import com.store.avro.orders.CustomerValue;
import com.store.avro.orders.ProductValue;
import com.store.dto.CustomerDTO;
import com.store.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ModelMapperTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void whenConvertCustomeDTOToCustomerValueThenShouldReturnConvertedValue() {
        var customerDTO = CustomerDTO.builder()
                .id(1L)
                .active(true)
                .birthDate(LocalDate.now())
                .email("email@email.com")
                .createdDate(LocalDateTime.now())
                .name("Felipe")
                .lastName("Neves")
                .build();

        var convertedAvroCustomer = modelMapper.map(customerDTO, CustomerValue.class);

        assertThat(convertedAvroCustomer).isNotNull();
        assertThat(convertedAvroCustomer.getId()).isEqualTo(customerDTO.getId().intValue());
        assertThat(convertedAvroCustomer.getName()).isEqualTo(customerDTO.getName());
    }

    @Test
    void whenConvertProductDTOToProductValuelThenShouldReturnConvertedValue() {
        var productDTO = ProductDTO.builder()
                .price(1_000)
                .id(1L)
                .creationDate(LocalDate.now())
                .name("Product")
                .description("A simple Product")
                .stock(50)
                .build();

        var productValue = modelMapper.map(productDTO, ProductValue.class);

        assertThat(productValue).isNotNull();
        assertThat(productValue.getName()).isEqualTo(productDTO.getName());
        assertThat(productValue.getPrice()).isEqualTo(productDTO.getPrice());
    }
}
