package com.supremeshop.api.controller;

import com.supremeshop.api.model.Product;
import com.supremeshop.api.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite que React se conecte desde localhost:5173
@Api(value = "Product Management System")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    // GET /api/productos - Listar todos los productos
    @GetMapping
    @ApiOperation(value = "View a list of available products", response = List.class)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    // GET /api/productos/{id} - Obtener un producto por ID
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a product by Id")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    
    // POST /api/productos - Crear un nuevo producto
    @PostMapping
    @ApiOperation(value = "Add a new product")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
    
    // PUT /api/productos/{id} - Actualizar un producto existente
    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing product")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImage(product.getImage());
            existingProduct.setDescription(product.getDescription());
            return productService.saveProduct(existingProduct);
        }
        return null;
    }
    
    // DELETE /api/productos/{id} - Eliminar un producto
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a product")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
