/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import api.SalesByUserApi;
import api.SummaryApi;
import domain.Customer;
import domain.Sale;
import domain.Summary;
import domain.Totals;
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
 * @author liji8162
 */
public class SaleTest {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SalesByUserApi salesByUserApi;
    SummaryApi summaryApi;
    Customer customer;
    Totals totals;
    Sale sale;
    List<Sale> sales;
    Summary mySummary;

    @Before
    public void setUp() {
        salesByUserApi = retrofit.create(SalesByUserApi.class);
        summaryApi = retrofit.create(SummaryApi.class);

        customer = new Customer();
        customer.setId("qw12");
        customer.setFirstName("sam");
        customer.setLastName("smith");

        totals = new Totals();
        totals.setTotalPayment(5000.9);
        totals.setTotalPrice(4000.4);
        totals.setTotalTax(1000.5);

        sale = new Sale();
        sale.setId("12345");
        sale.setSaleDate("2018");
        sale.setCustomer(customer);
        sale.setTotals(totals);
    }

    @After
    public void tearDown() {

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createAndGet() throws IOException {
        salesByUserApi.createANewSale(sale).execute().body();
        sales = salesByUserApi.getAllSalesForASpecificCustomer(customer.getId()).execute().body();
        assertTrue(sales.size() > 0);

        Boolean found = false;

        for (Sale s : sales) {
            if (s.getId().equals(sale.getId())) {
                found = true;
                break;
            }
        }
        assertTrue(found);

    }

    @Test
    public void getSummaryById() throws IOException {
        salesByUserApi.createANewSale(sale).execute().body();
        mySummary = summaryApi.getSummaryForACustomer(customer.getId()).execute().body();
        System.out.println(mySummary.getTotalPayment());
        assertEquals(mySummary.getTotalPayment(), totals.getTotalPayment());
    }
}
