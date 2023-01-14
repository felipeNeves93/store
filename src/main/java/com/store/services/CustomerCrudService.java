package com.store.services;

import com.store.entity.Customer;
import com.store.repositories.CustomerRepository;
import com.store.services.base.BaseCrudService;

public interface CustomerCrudService extends BaseCrudService<Customer, Long, CustomerRepository> {
}
