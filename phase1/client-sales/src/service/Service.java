/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import api.CustomerApi;
import api.CustomersApi;
import domain.Customer;
import java.io.IOException;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author jiaweili
 */
public class Service {

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8082/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerApi customerApi = retrofit.create(CustomerApi.class);
        CustomersApi customersApi = retrofit.create(CustomersApi.class);
        
        
        
        Customer customer = new Customer();
        customer.setUserName("SssPPP");
        customer.setFirstName("sam");
        customer.setLastName("Smith");
        
        customersApi.postACustomer(customer);
       
        
        
        Customer newCustomer = new Customer();
        newCustomer.setUserName("Sss");
        newCustomer.setGroup("VIP");
        
        


        
        customerApi.updateAnAccount("Sss", newCustomer).execute().body();
        
        
        List<Customer> customers = customersApi.getCustomers().execute().body();
        System.out.println(customers);
        

    }

}
