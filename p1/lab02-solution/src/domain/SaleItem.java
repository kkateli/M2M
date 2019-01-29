/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author jiaweili
 */
public class SaleItem {

    private String productId;
    private double quantity;
    private double price;

    public SaleItem(String productId, double quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;

    }

    public String getProductId() {
        return productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SaleItem{" + "productId=" + productId + ", quantity=" + quantity + ", price=" + price + '}';
    }

}
