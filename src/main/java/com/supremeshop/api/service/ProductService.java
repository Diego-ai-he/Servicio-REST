package com.supremeshop.api.service;

import com.supremeshop.api.model.Product;
import com.supremeshop.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    // Obtener un producto por ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    // Crear o actualizar un producto
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    // Eliminar un producto
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
