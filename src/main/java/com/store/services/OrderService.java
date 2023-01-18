package com.store.services;

import com.store.entity.Customer;
import com.store.entity.Product;

import java.util.List;

public interface StoreService {

    void createOrder(List<Product> products, Customer customer);
}
