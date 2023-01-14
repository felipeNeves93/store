package com.store.services;

import com.store.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class CustomerCrudServiceTest {

    @Autowired
    private CustomerCrudService customerCrudService;

    @Test
    void whenSavingCustomerShouldReturnSavedCustomer() {
        var customer = this.createCustomer("whenSavingCustomerShouldReturnSavedCustomer@email.com");
        var savedCustomer = customerCrudService.save(customer);

        assertThat(savedCustomer.getId()).isNotNull();
    }

    @Test
    void whenSearchingByIdShouldReturnSearchedEntity() {
        var customer = this.createCustomer("whenSearchingByIdShouldReturnSearchedEntity@email.com");
        var savedCustomer = customerCrudService.save(customer);
        var searchedCustomer = customerCrudService.findById(savedCustomer.getId()).orElse(null);

        assertThat(searchedCustomer).isNotNull();
    }

    @Test
    void whenDeletingByIdShouldDeleteTheEntity() {
        var customer = this.createCustomer("whenDeletingByIdShouldDeleteTheEntity@email.com");
        var savedCustomer = customerCrudService.save(customer);
        var searchedCustomer = customerCrudService.findById(savedCustomer.getId()).orElse(null);

        assertThat(searchedCustomer).isNotNull();
        customerCrudService.deleteById(searchedCustomer.getId());

        searchedCustomer = customerCrudService.findById(savedCustomer.getId()).orElse(null);
        assertThat(searchedCustomer).isNull();
    }

    @Test
    void whenListingAllShouldReturnListContainingEntities() {
        var customer = this.createCustomer("whenListingAllShouldReturnListContainingEntities@email.com");
        customerCrudService.save(customer);

        var searchedCustomers = customerCrudService.findAll();
        assertThat(searchedCustomers).isNotEmpty();
    }

    private Customer createCustomer(String email) {
        return Customer.builder()
                .email(email)
                .name("Felipe")
                .birthDate(LocalDate.now())
                .active(true)
                .lastName("Neves")
                .build();
    }
}
