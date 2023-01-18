package com.store.services;

import com.store.dto.OrderProductDTO;
import com.store.entity.Customer;
import com.store.entity.Product;

import java.util.List;

public interface OrderService {

    void create(List<OrderProductDTO> products, Customer customer);
}
