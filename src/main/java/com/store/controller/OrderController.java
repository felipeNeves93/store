package com.store.controller;

import com.store.dto.OrderProductDTO;
import com.store.services.OrderService;
import com.store.services.crud.CustomerCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerCrudService customerCrudService;

    @PostMapping
    void create(@RequestBody List<OrderProductDTO> products, @RequestParam("customerId") Long customerId) {

    }
}
