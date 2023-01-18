package com.store.services.crud;

import com.store.entity.Product;
import com.store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCrudServiceImpl extends BaseCrudServiceImpl<Product, Long, ProductRepository>
        implements ProductCrudService {

    public ProductCrudServiceImpl(ProductRepository repository) {
        super(repository);
    }
}
