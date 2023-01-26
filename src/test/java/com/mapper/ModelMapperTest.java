package com.mapper;

import com.store.avro.orders.CustomerValue;
import com.store.avro.orders.ProductValue;
import com.store.dto.CustomerDTO;
import com.store.dto.ProductDTO;
import com.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;

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
    void whenConvertProductDTOToProductValueThenShouldReturnConvertedValue() {
        var productDTO = ProductDTO.builder()
                .price(1_000)
                .id(1L)
                .creationDate(LocalDate.now())
                .name("Product")
                .image(new MockMultipartFile("Mock File", new byte[0]))
                .description("A simple Product")
                .stock(50)
                .build();

        var productValue = modelMapper.map(productDTO, ProductValue.class);

        assertThat(productValue).isNotNull();
        assertThat(productValue.getName()).isEqualTo(productDTO.getName());
        assertThat(productValue.getPrice()).isEqualTo(productDTO.getPrice());
    }

    @Test
    void whenConvertProductDTOToProductThenShouldReturnConvertedValue() {
        var productDTO = ProductDTO.builder()
                .price(1_000)
                .id(1L)
                .creationDate(LocalDate.now())
                .name("Product")
                .image(new MockMultipartFile("Mock File", new byte[0]))
                .description("A simple Product")
                .stock(50)
                .build();

        var product = modelMapper.map(productDTO, Product.class);

        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo(productDTO.getName());
        assertThat(product.getPrice()).isEqualTo(productDTO.getPrice());
        assertThat(product.getDescription()).isEqualTo(productDTO.getDescription());
        assertThat(product.getStock()).isEqualTo(productDTO.getStock());
        assertThat(product.getId()).isEqualTo(productDTO.getId());
        assertThat(product.getCreationDate()).isEqualTo(productDTO.getCreationDate());
    }
}
