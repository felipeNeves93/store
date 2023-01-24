package com.store.controller;

import com.store.dto.CustomerDTO;
import com.store.dto.OrderProductDTO;
import com.store.services.OrderService;
import com.store.services.crud.CustomerCrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerCrudService customerCrudService;

    private final ModelMapper modelMapper;

    @PostMapping
    void create(@RequestBody List<OrderProductDTO> products, @RequestParam("customerId") Long customerId) {
        var customer = customerCrudService.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer wasn't found with id :" + customerId + " !"));
        var convertedCustomer = modelMapper.map(customer, CustomerDTO.class);

        orderService.create(products, convertedCustomer);

    }
}
