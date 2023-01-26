package com.store.controller;

import com.store.dto.ProductDTO;
import com.store.entity.Product;
import com.store.services.ImageService;
import com.store.services.crud.ProductCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ModelMapper modelMapper;
    private final ProductCrudService productCrudService;

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        Objects.requireNonNull(productDTO, "A product is required to be saved!");

        var convertedProduct = modelMapper.map(productDTO, Product.class);
        if (productDTO.getImage() != null && productDTO.getImage().getSize() > 0) {
            try {
                convertedProduct.setImage(imageService.compressImage(productDTO.getImage().getBytes()));
            } catch (IOException e) {
                log.error("An error ocurred while saving a product with image!", e);
            }
        }
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
