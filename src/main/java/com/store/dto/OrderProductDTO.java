package com.store.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDTO {
    private int amount;
    private ProductDTO product;
}
