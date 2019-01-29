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
public class Customer implements Serializable {
    
    
    private String id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private String email;
    @SerializedName("customer_group_id")
    private String group;

    
    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.group = group;
    }

    public Customer(String id, String firstName, String lastName, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.group = group;
    }
    
    

    public Customer(String group) {
        this.group = group;
    }
    
    public String getId() {
        return id;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", group=" + group + '}';
    }

    

}
