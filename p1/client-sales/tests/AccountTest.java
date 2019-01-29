/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import api.CustomerApi;
import api.CustomersApi;
import domain.Customer;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author jiaweili
 */
public class AccountTest {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8082/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    CustomerApi customerApi;
    CustomersApi customersApi;
    Customer customer;
    List<Customer> customers;

    public AccountTest() {
    }

    @Before
    public void setUp() {
        customerApi = retrofit.create(CustomerApi.class);
        customersApi = retrofit.create(CustomersApi.class);

        customer = new Customer();
        customer.setUserName("Sssz");
        customer.setFirstName("sam");
        customer.setLastName("Smith");

    }

    @After
    public void tearDown() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void getCustomers() throws IOException {
        customers = customersApi.getCustomers().execute().body();
        assertTrue(customers.size() == 2);

    }

    @Test
    public void postCustomer() throws IOException {
        customersApi.postACustomer(customer).execute().body();
        customers = customersApi.getCustomers().execute().body();
        System.out.print("Customers=" + customers);

        Boolean found = false;

        for (Customer c : customers) {
            if (c.getUserName().equals(customer.getUserName())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void updateCustomer() throws IOException {
        Customer updateCustomer = new Customer();
        updateCustomer.setUserName("kkkka");
        updateCustomer.setGroup("VIP");
        customerApi.updateAnAccount("kkkka", updateCustomer).execute().body();

        customers = customersApi.getCustomers().execute().body();

        assertEquals(updateCustomer.getGroup(), customers.get(1).getGroup());

    }
}
