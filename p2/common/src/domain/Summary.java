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
public class Summary implements Serializable{

    private Integer numberOfSales;
    @SerializedName("total_payment")
    private double totalPayment;
    @SerializedName("customer_group_id")
    private String group;
    private String uri;

    public Summary(Integer numberOfSales, double totalPayment, String group, String uri) {
        this.numberOfSales = numberOfSales;
        this.totalPayment = totalPayment;
        this.group = group;
        this.uri = uri;
    }
    
    public Customer convertCustomer(String id, String firstName, String lastName,String email, String group) {
    return new Customer(id, firstName, lastName,email, group);
  }
    
     public CustomerAccount convertAccount(String userName, String firstName, String lastName, String email, String group) {
    return new CustomerAccount(userName, firstName,lastName,email,group);
  }

    public Summary() {
    }

 public Summary convertGroup(Integer numberOfSales, double totalPayment,String groupName,String uri) {
            if(groupName.equals("regular")){
                return new Summary(numberOfSales, totalPayment,"0afa8de1-147c-11e8-edec-2b197906d816",uri);
            }
            else{
                return new Summary(numberOfSales, totalPayment,"0afa8de1-147c-11e8-edec-201e0f00872c",uri);
            }
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
