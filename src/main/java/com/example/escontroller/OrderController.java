package com.example.escontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Cart;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.repository.CartRepository;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/create/{userId}")
    public Order createOrder(@PathVariable Long userId) {

        User user = userRepo.findById(userId).get();
        List<Cart> cartItems = cartRepo.findByUser(user);

        Order order = new Order();
        order.setUser(user);

        List<Product> products = cartItems.stream()
                .map(Cart::getProduct)
                .toList();

        double total = cartItems.stream()
                .mapToDouble(c -> c.getProduct().getPrice() * c.getQuantity())
                .sum();

        order.setProducts(products);
        order.setTotalPrice(total);
        order.setStatus("CREATED");

        cartRepo.deleteAll(cartItems);

        return orderRepo.save(order);
    }}
