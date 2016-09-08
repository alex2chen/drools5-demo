package com.xfboy.junit.fact;

/**
 * Purchase
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
public class Purchase {
    private Customer customer;
    private Ticket ticket;

    public Purchase(Customer customer) {
        this.customer = customer;
        this.ticket = new Ticket();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
