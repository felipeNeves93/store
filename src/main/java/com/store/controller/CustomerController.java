package com.store.controller;

import com.store.dto.CustomerDTO;
import com.store.entity.Customer;
import com.store.services.crud.CustomerCrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ModelMapper modelMapper;
    private final CustomerCrudService customerCrudService;

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        Objects.requireNonNull(customerDTO, "A customer is required to be saved!");

        var convertedCustomer = modelMapper.map(customerDTO, Customer.class);
        var savedCustomer = customerCrudService.save(convertedCustomer);

        return ResponseEntity.ok(modelMapper.map(savedCustomer, CustomerDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> listAll() {
        var customers = customerCrudService.findAll();

        return ResponseEntity.ok(customers.stream()
                .map(p -> modelMapper.map(p, CustomerDTO.class))
                .toList());
    }

}
