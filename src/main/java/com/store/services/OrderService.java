package com.store.services;

import com.store.dto.CustomerDTO;
import com.store.dto.OrderProductDTO;

import java.util.List;

public interface OrderService {

    void create(List<OrderProductDTO> products, CustomerDTO customer);
}
