/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author jiaweili
 */
public class Sale implements Serializable{

    private String id;
    @SerializedName("sale_date")
    private String saleDate;
    private String uri;
    private Customer customer;
    private Totals totals;
    @SerializedName("register_sale_products")
    private Collection<SaleItem> items = new HashSet<>();

    public Sale(String id, String saleDate, String uri, Customer customer, Totals totals) {
        this.id = id;
        this.saleDate = saleDate;
        this.uri = uri;
        this.customer = customer;
        this.totals = totals;
    }

    public Sale() {

    }

    public Totals getTotals() {
        return totals;
    }

    public String getId() {
        return id;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public String getUri() {
        return uri;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Collection<SaleItem> getItems() {
        return items;
    }

    public void setTotals(Totals totals) {
        this.totals = totals;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItems(Collection<SaleItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", saleDate=" + saleDate + ", uri=" + uri + ", customer=" + customer + ", totals=" + totals + ", items=" + items + '}';
    }

}
