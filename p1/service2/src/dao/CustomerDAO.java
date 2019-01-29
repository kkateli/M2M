/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jiaweili
 */
public class CustomerDAO {

    private static final Map<String, Customer> customers = new TreeMap<>();

    /*
	 * data for testing
     */
    static {
        if (customers.isEmpty()) {
            customers.put("kkkka", new Customer("kkkka", "Kate", "Li", "kateli24@outlook.com", "regular customer", "api/customers/customer/kkkka"));
        }
    }

    /**
     * A client needs to be able to create a new customer account.
     *
     * @param customer The customer being added.
     */
    public void addAccount(Customer customer) {
        customers.put(customer.getUserName(), customer);
    }

    /**
     * A client needs to be able to get all registered customer accounts.
     *
     * @return All accounts ordered by user name.
     */
    public List<Customer> getAccounts() {
        return new ArrayList(customers.values());
    }

    /**
     * Updates a customer.
     *
     * @param username The user name of the customer to replace.
     * @param newCustomer The product to replace it with.
     */
    public void updateAccount(String username, Customer newCustomer) {
        customers.put(username, newCustomer);
    }

    /**
     * Checks if a customer exists.
     *
     * @param username The username of the customer being checked.
     * @return <code>true</code> if product exists, <code>false</code> if not.
     */
    public boolean exists(String username) {
        return customers.containsKey(username);
    }

}
