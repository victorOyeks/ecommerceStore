package com.example.weeseventask.DTO;

import lombok.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private String price;
    private String description;
    private String imageURL;

}
