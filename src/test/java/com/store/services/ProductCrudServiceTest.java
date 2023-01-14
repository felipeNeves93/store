package com.store.services;

import com.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ProductCrudServiceTest {

    @Autowired
    private ProductCrudService productCrudService;

    @Test
    void whenSavingShouldReturnSavedEntity() {
        var product = this.createProduct();
        var savedProduct = productCrudService.save(product);

        assertThat(savedProduct.getId()).isNotNull();
    }

    @Test
    void whenSearchingByIdShouldReturnSearchedEntity() {
        var product = this.createProduct();
        var savedProduct = productCrudService.save(product);
        var searchedProduct = productCrudService.findById(savedProduct.getId()).orElse(null);

        assertThat(searchedProduct).isNotNull();
    }

    @Test
    void whenDeletingByIdShouldDeleteTheEntity() {
        var product = this.createProduct();
        var savedProduct = productCrudService.save(product);
        var searchedProduct = productCrudService.findById(savedProduct.getId()).orElse(null);

        assertThat(searchedProduct).isNotNull();
        productCrudService.deleteById(searchedProduct.getId());

        searchedProduct = productCrudService.findById(savedProduct.getId()).orElse(null);
        assertThat(searchedProduct).isNull();
    }

    @Test
    void whenListingAllShouldReturnListContainingEntities() {
        var product = this.createProduct();
        productCrudService.save(product);

        var searchedProducts = productCrudService.findAll();
        assertThat(searchedProducts).isNotEmpty();
    }

    private Product createProduct() {
        return Product.builder()
                .name("foo product")
                .description("foo description")
                .price(5_000)
                .stock(50)
                .build();
    }
}
