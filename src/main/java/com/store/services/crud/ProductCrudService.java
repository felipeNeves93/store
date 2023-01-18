package com.store.services;

import com.store.entity.Product;
import com.store.repositories.ProductRepository;
import com.store.services.base.BaseCrudService;

public interface ProductCrudService extends BaseCrudService<Product, Long, ProductRepository> {
}
