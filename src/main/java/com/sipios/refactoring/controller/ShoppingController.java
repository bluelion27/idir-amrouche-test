package com.sipios.refactoring.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.sipios.refactoring.model.Body;
import com.sipios.refactoring.model.CustomerType;
import com.sipios.refactoring.model.Item;
import com.sipios.refactoring.model.ItemType;
import com.sipios.refactoring.service.ShoppingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    private ShoppingService shoppingService;

    @Autowired
    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping
    public String getPrice(@RequestBody Body body) {
        double price = 0;

        price = shoppingService.computeTotalAmount(body);

        try {
            if (body.getType() == CustomerType.STANDARD_CUSTOMER) {
                if (price > 200) {
                    throw new Exception("Price (" + price + ") is too high for standard customer");
                }
            } else if (body.getType() == CustomerType.PREMIUM_CUSTOMER) {
                if (price > 800) {
                    throw new Exception("Price (" + price + ") is too high for premium customer");
                }
            } else if (body.getType() == CustomerType.PLATINUM_CUSTOMER) {
                if (price > 2000) {
                    throw new Exception("Price (" + price + ") is too high for platinum customer");
                }
            } else {
                if (price > 200) {
                    throw new Exception("Price (" + price + ") is too high for standard customer");
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return String.valueOf(price);
    }
}



