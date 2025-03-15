package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;
import java.util.Optional;

public interface IProductQueryService {
    List<Product> getAllProducts();
    List<Product> getProductsByName(String name);
    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);
    List<Product> getProductsByStockQuantity(Integer quantity);
    Optional<Product> getProductById(Long id);

}

