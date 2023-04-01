package com.example.weeseventask.services;

import com.example.weeseventask.DTO.ProductDTO;
import com.example.weeseventask.model.Product;

import java.util.List;

public interface ProductService {
 Product saveProduct(ProductDTO productDTO);

 List<ProductDTO> getAllProducts();

 Product getProductById(Long id);

 void deleteProduct(Long id);

 Product editProduct (ProductDTO productDTO, Long id);
}
