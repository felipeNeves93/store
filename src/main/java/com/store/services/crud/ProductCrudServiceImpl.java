package com.store.services.crud;

import com.store.entity.Product;
import com.store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductCrudServiceImpl extends BaseCrudServiceImpl<Product, Long, ProductRepository>
        implements ProductCrudService {

    public ProductCrudServiceImpl(ProductRepository repository) {
        super(repository);
    }

    @Override
    public Product save(Product entity) {
        entity.setCreationDate(LocalDate.now());
        return super.save(entity);
    }
}
