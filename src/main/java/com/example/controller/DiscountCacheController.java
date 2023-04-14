package com.example.controller;

import com.example.entity.ProductDiscount;
import com.example.repository.ProductDiscountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/product-discount")
public class DiscountCacheController {
    Logger logger = LoggerFactory.getLogger(DiscountCacheController.class);

    static Map<String, Integer> discountMap = new HashMap<>();

    @Autowired
    ProductDiscountRepository productDiscountRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadDiscountMap() {
        logger.info("Loading of Discount Cache started.");
        List<ProductDiscount> prodList = productDiscountRepository.findAll();
        System.out.println(prodList);
        for (ProductDiscount prodDiscount : prodList) {
            discountMap.put(prodDiscount.getProductName(), prodDiscount.getDiscountVal());
        }
        logger.info("Loading of Discount Cache completed.");
        logger.info("Discount Map : "+discountMap);
    }

    @GetMapping("/cache-refresh")
    //@Scheduled(fixedDelayString = "PT1M")
    public void refreshDiscountMapCache() {
        logger.info("Refresh of Discount Cache started at : "+new Date());
        discountMap = new HashMap<>();
        loadDiscountMap();
        logger.info("Refresh of Discount Cache completed at :" +new Date());
    }

    public static Map<String,Integer> getDiscountMap(){
        return discountMap;
    }

}
