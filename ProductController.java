package com.example.escontroller;

  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Product;
import com.example.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product add(@RequestBody Product p) {
        return service.save(p);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }
}
