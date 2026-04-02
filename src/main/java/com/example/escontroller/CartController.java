package com.example.escontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Cart;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @PostMapping("/add")
    public Cart add(@RequestParam Long userId,
                    @RequestParam Long productId,
                    @RequestParam int qty) {

        Cart cart = new Cart();
        cart.setUser(userRepo.findById(userId).get());
        cart.setProduct(productRepo.findById(productId).get());
        cart.setQuantity(qty);

        return cartRepo.save(cart);
    }

    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable Long userId) {
        return cartRepo.findByUser(userRepo.findById(userId).get());
    }
}
