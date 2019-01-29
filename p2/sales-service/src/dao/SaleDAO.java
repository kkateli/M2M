/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import domain.Sale;
import domain.Summary;
import domain.Totals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jiaweili
 */
public class SaleDAO {

    private static final Map<String, ArrayList<Sale>> salesById = new TreeMap<String, ArrayList<Sale>>();
    private static final List<Sale> saleList = new ArrayList<>();
    Summary summary;

    /*
	 * Some dummy data for testing
     */
    public SaleDAO() {
//        List<Sale> sample = new ArrayList<Sale>();
//        Sale sale = new Sale("01", "20100319", "api/sales/customer/WD1234",
//                new Customer("WD1234", "Kate", "Li", "kateli24@outlook.com"),
//                new Totals(35.5, 1.5, 37.0));
//        addSale(sale);

    }

    /**
     * A client needs to be able to add a new sale.
     *
     * @param newSale The sale being added.
     */
    public void addSale(Sale newSale) {
        saleList.add(newSale);
        if (salesById.containsKey(newSale.getCustomer().getId())) {
            List<Sale> sales = salesById.get(newSale.getCustomer().getId());
            sales.add(newSale);
        } else {
            ArrayList<Sale> newSaleList = new ArrayList<>();
            newSaleList.add(newSale);
            salesById.put(newSale.getCustomer().getId(), newSaleList);
        }
    }

    /**
     * A client needs to be able to get all sales for a specific customer
     *
     * @param id The ID of the customer.
     * @return The collection of sales matching the given customer ID, or null
     * if no match found.
     */
    public Collection<Sale> getSalesById(String id) {
        return salesById.get(id);
    }

    /**
     * A client needs to be able to a summary of the sale data for a specific
     * customer
     *
     * @param id The ID of the customer.
     * @return The summary matching the given customer ID, or null if no match
     * found.
     */
    public Summary getSummaryById(String id) {
        summary = new Summary();
        double total = 0;
        int number = 0;
        String uri;
        for (Sale s : saleList) {
            if (s.getCustomer().getId().equals(id)) {
                number++;
                total += s.getTotals().getTotalPayment();
                uri="http://localhost:8088/api/sales/customer/"+s.getCustomer().getId();
         
                summary.setNumberOfSales(number);
                summary.setTotalPayment(total);
                summary.setUri(uri);
            }
        }

        if (summary.getTotalPayment() >= 5000) {
            summary.setGroup("VIP");
        }else{
            summary.setGroup("regular");
        }

        return summary;

    }

    /**
     * Checks if a customer exists.
     *
     * @param id The ID of the customer being checked.
     * @return <code>true</code> if customer exists, <code>false</code> if not.
     */
    public boolean ifCustomerExists(String id) {
        return salesById.containsKey(id);
    }

}

