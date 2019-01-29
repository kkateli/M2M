package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import domain.CustomerAccount;
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

    CustomersApiService service;
    CustomerAccount customerAccount;
    List<CustomerAccount> customers;

    public AccountTest() {
    }

    @Before
    public void setUp() {
        
        service = retrofit.create(CustomersApiService.class);

        customerAccount = new CustomerAccount();
        customerAccount.setUserName("Sssa");
        customerAccount.setFirstName("sam");
        customerAccount.setLastName("Smith");

    }

    @After
    public void tearDown() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void getCustomers() throws IOException {
        customers = service.getCustomers().execute().body();
        assertTrue(customers.size() == 2);

    }

    @Test
    public void postCustomer() throws IOException {
        service.postACustomer(customerAccount).execute().body();
        customers = service.getCustomers().execute().body();
        System.out.print("Customers=" + customers);

        Boolean found = false;

        for (CustomerAccount c : customers) {
            if (c.getUserName().equals(customerAccount.getUserName())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void updateCustomer() throws IOException {
        CustomerAccount updateCustomer = new CustomerAccount();
        updateCustomer.setUserName("kkkka");
//        updateCustomer.setGroup("VIP");
        service.updateAnAccount("kkkka", updateCustomer).execute().body();

        customers = service.getCustomers().execute().body();

//        assertEquals(updateCustomer.getGroup(), customers.get(1).getGroup());

    }
}
