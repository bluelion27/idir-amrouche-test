package com.sipios.refactoring.service;

import com.sipios.refactoring.model.Body;
import com.sipios.refactoring.model.CustomerType;
import com.sipios.refactoring.model.Item;
import com.sipios.refactoring.model.ItemType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class ShoppingService {

    private double computeDiscountByCustomerType(CustomerType customerType) {
        double discount;

        switch (customerType) {
            case STANDARD_CUSTOMER:
                discount = 1;
                break;
            case PREMIUM_CUSTOMER:
                discount = 0.9;
                break;
            case PLATINUM_CUSTOMER:
                discount = 0.5;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return discount;
    }

    private boolean isDiscountSeason() {
        // if we are in winter or summer discounts periods
        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);

        return
            (cal.get(Calendar.DAY_OF_MONTH) < 15 &&
             cal.get(Calendar.DAY_OF_MONTH) > 5 &&
             cal.get(Calendar.MONTH) == 5)
            ||
            (cal.get(Calendar.DAY_OF_MONTH) < 15 &&
             cal.get(Calendar.DAY_OF_MONTH) > 5 &&
             cal.get(Calendar.MONTH) == 0);
    }

    public double computeTotalAmount (Body body) {
        double customerDiscount = computeDiscountByCustomerType(body.getType());

        double amount = 0;

        if (body.getItems() == null) {
            return 0;
        }

        for (Item item : body.getItems()) {
            amount += getItemUnitPrice(item) * item.getNb() * customerDiscount;
        }

        return amount;
    }

    private double getItemUnitPrice(Item item) {
        double unitPrice = 0;
        double seasonDiscount = 1;
        switch (item.getType()) {
            case TSHIRT:
                unitPrice = 30;
                break;
            case DRESS:
                unitPrice = 50;
                seasonDiscount = 0.8;
                break;
            case JACKET:
                unitPrice = 100;
                seasonDiscount = 0.9;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (isDiscountSeason()) {
            unitPrice = unitPrice * seasonDiscount;
        }

        return unitPrice;
    }
}
