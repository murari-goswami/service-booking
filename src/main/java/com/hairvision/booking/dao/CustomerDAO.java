package com.hairvision.booking.dao;

import com.hairvision.booking.model.Customer;
import com.hairvision.booking.model.Customers;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {

    private static Customers list = new Customers();

    static
    {
        list.getCustomerList().add(new Customer("Shaina-1", "Shaina-1@gmail.com", "cscs2323"
                , "0123456789", " Erlangen"));
        list.getCustomerList().add(new Customer("Shaina-2", "Shaina-2@gmail.com", "cscs2323"
                , "0123456789", " Erlangen"));
    }

    public Customers getAllCustomers() {
        return list;
    }

    public void addCustomers(Customer customer) {
        list.getCustomerList().add(customer);
    }


}
