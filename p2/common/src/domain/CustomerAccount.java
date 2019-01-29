/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jiaweili
 */
public class CustomerAccount implements Serializable{

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String group="0afa8de1-147c-11e8-edec-2b197906d816";
    
    
    
    public CustomerAccount(){}

    
    public CustomerAccount(String userName, String firstName, String lastName, String email, String group) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.group=group;
        
        
    }
    
    public Customer createAccount( String firstName,String lastName, String email,String group) {
    return new Customer(firstName, lastName, email,group);
  }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return "CustomerAccount{" + "userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", group=" + group + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerAccount other = (CustomerAccount) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

}
