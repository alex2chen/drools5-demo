package com.xfboy.junit.fact;

/**
 * Ticket
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
public class Ticket {
    private int price;
    private boolean discount;

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
