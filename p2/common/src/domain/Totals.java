/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author jiaweili
 */
public class Totals implements Serializable{

    @SerializedName("total_price")
    private double totalPrice;
    @SerializedName("total_tax")
    private double totalTax;
    @SerializedName("total_payment")
    private double totalPayment;

    public Totals(){}

    public Totals(double totalPrice, double totalTax, double totalPayment) {
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.totalPayment = totalPayment;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public String toString() {
        return "Totals{" + "totalPrice=" + totalPrice + ", totalTax=" + totalTax + ", totalPayment=" + totalPayment + '}';
    }

}
