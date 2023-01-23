package com.store.controller;

import com.store.dto.ProductDTO;
import com.store.entity.Product;
import com.store.services.crud.ProductCrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/poducts")
@RequiredArgsConstructor
public class ProductController {

    private final ModelMapper modelMapper;
    private final ProductCrudService productCrudService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        Objects.requireNonNull(productDTO, "A product is required to be saved!");

        var convertedProduct = modelMapper.map(productDTO, Product.class);
        var savedProduct = productCrudService.save(convertedProduct);

        return ResponseEntity.ok(modelMapper.map(savedProduct, ProductDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listAll() {
        var products = productCrudService.findAll();

        return ResponseEntity.ok(products.stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .toList());
    }
}
