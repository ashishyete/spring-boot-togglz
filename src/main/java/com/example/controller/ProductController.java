package com.example.controller;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DiscountCacheController cacheController;

    @Autowired
    private FeatureManager featureManager;

    public static final Feature PROMO_DISCOUNT = new NamedFeature("PROMO_DISCOUNT");

    @GetMapping("/all")
    public List<Product> getProducts() {
        System.out.println(productRepository.findAll());
        if(featureManager.isActive(PROMO_DISCOUNT)){
            return promoDiscountProducts();
        }
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<Product> promoDiscountProducts() {
        List<Product> discountedProducts = new ArrayList<>();
        Map<String, Integer> hMap = DiscountCacheController.discountMap;
        productRepository.findAll().stream().forEach(product -> {
            if (hMap.containsKey(product.getProductName().toUpperCase())) {
                product.setDiscountedPrice(product.getPrice() - (product.getPrice() * hMap.get(product.getProductName().toUpperCase()) / 100));
                product.setAppliedDiscount(hMap.get(product.getProductName()) + " % ");
            }
            discountedProducts.add(product);
        });
        return discountedProducts;
    }


}
