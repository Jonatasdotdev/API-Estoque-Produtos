package com.example.demo.controller;

import com.example.demo.dto.ProductCreateDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.model.Product;
import com.example.demo.service.IProductCommandService;
import com.example.demo.service.IProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductQueryService productQueryService;

    @Autowired
    private IProductCommandService productCommandService;

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productQueryService.getAllProducts().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return productQueryService.getProductById(id)
                .map(product -> ResponseEntity.ok(convertToResponseDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductCreateDto productCreateDto) {
        Product product = convertToEntity(productCreateDto);
        Product createdProduct = productCommandService.createProduct(product);
        return convertToResponseDto(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDto productCreateDto) {
        Product product = convertToEntity(productCreateDto);
        Product updatedProduct = productCommandService.updateProduct(id, product);
        return ResponseEntity.ok(convertToResponseDto(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productCommandService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    private Product convertToEntity(ProductCreateDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        return product;
    }

    private ProductResponseDto convertToResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        return dto;
    }
}