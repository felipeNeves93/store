package com.store.services.crud;

import com.store.entity.Customer;
import com.store.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerCrudServiceImpl extends BaseCrudServiceImpl<Customer, Long, CustomerRepository> implements CustomerCrudService {
    public CustomerCrudServiceImpl(CustomerRepository repository) {
        super(repository);
    }
}
