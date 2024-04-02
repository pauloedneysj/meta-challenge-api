package com.pauloedney.metachallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pauloedney.metachallenge.models.ProductModel;
import com.pauloedney.metachallenge.models.dtos.ProductRequestDTO;
import com.pauloedney.metachallenge.repositories.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO data) {
        var product = this.productRepository.findByCode(data.code());

        if (product != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product already exists");
        }

        ProductModel newProduct = new ProductModel(data);
        var createdProduct = this.productRepository.save(newProduct);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productList = this.productRepository.findAll();
        return ResponseEntity.ok(productList);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<ProductModel>> getProductsByUserId(@PathVariable String userId) {
        List<ProductModel> productList = this.productRepository.findByUserId(userId);
        return ResponseEntity.ok(productList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable String id, @RequestBody ProductModel productUpdates) {
        ProductModel product = this.productRepository.findById(id).orElse(null);

        if (product != null) {
            product.setName(productUpdates.getName());
            product.setCode(productUpdates.getCode());
            product.setSent(productUpdates.isSent());
            this.productRepository.save(product);
        }

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        this.productRepository.deleteById(id);
       return ResponseEntity.ok().build();
   }
}

