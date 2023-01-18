package com.store.services.crud;

import com.store.entity.Product;
import com.store.repositories.ProductRepository;
import com.store.services.crud.BaseCrudService;

public interface ProductCrudService extends BaseCrudService<Product, Long, ProductRepository> {
}
