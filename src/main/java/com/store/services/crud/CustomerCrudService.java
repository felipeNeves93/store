package com.store.services.crud;

import com.store.entity.Customer;
import com.store.repositories.CustomerRepository;
import com.store.services.crud.BaseCrudService;

public interface CustomerCrudService extends BaseCrudService<Customer, Long, CustomerRepository> {
}
