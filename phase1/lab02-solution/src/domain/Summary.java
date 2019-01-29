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
public class Summary {

    private Integer numberOfSales;
    private double totalPayment;
    private String group;
    private String uri;

    public Summary(Integer numberOfSales, double totalPayment, String group, String uri) {
        this.numberOfSales = numberOfSales;
        this.totalPayment = totalPayment;
        this.group = group;
        this.uri = uri;
    }

    public Summary() {
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public String getGroup() {
        return group;
    }

    public String getUri() {
        return uri;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Summary{" + "numberOfSales=" + numberOfSales + ", totalPayment=" + totalPayment + ", group=" + group + ", uri=" + uri + '}';
    }

}
