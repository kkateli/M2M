/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinator;

import domain.Sale;
import domain.Summary;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 *
 * @author jiaweili
 */
public class SaleCoordinatorBuilder extends RouteBuilder {

    @Override
    public void configure() {
        //a copy of forwarded messages stay on the server (IMAP)   
//        from("imaps://outlook.office365.com?username=liji8162@student.otago.ac.nz"
//                + "&password=" + getPassword("Enter your E-Mail password")
//                + "&searchTerm.subject=Vend:SaleUpdate"
//                + "&debugMode=false" // set to true if you want to see the authentication details
//                + "&folderName=INBOX") // change to whatever folder your Vend messages end up in
//                .convertBodyTo(String.class)
//                .log("Found new E-mail: ${body}")
//                .to("jms:queue:sale-property");

//mock server
        from("imap://localhost?username=test@localhost"
                + "&port=3143"
                + "&password=password"
                + "&consumer.delay=5000"
                + "&searchTerm.subject=update-items")
                .to("jms:queue:sale-property");

        from("jms:queue:sale-property")
                .unmarshal().json(JsonLibrary.Gson, Sale.class)
                .log("sale-property: ${body}")
                .to("jms:queue:sale-copy");

        //create a regular customer sale  
        from("jms:queue:sale-copy")
                .marshal().json(JsonLibrary.Gson)
                .log("sale-property-json: ${body}")// only necessary if object needs to be converted to JSON
                .removeHeaders("*") // remove headers to stop them being sent to the service
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http4://localhost:8088/api/sales")
                .to("jms:queue:sales-response");

        //divide a sale to regular and vip      
        from("jms:queue:sales-response")
                .unmarshal().json(JsonLibrary.Gson, Sale.class)
                .setProperty("group").simple("${body.customer.group}")
                .choice()
                .when().simple("${exchangeProperty.group} == '0afa8de1-147c-11e8-edec-2b197906d816'")
                .to("jms:queue:group-regular")
                .otherwise()
                .to("jms:queue:group-vip");
        /*
         *regular customers
         */

//get summary for regular customer               
        from("jms:queue:group-regular")
                .setProperty("id").simple("${body.customer.id}")
                .setHeader("customerId").simple("${body.customer.id}")
                .setHeader("firstName").simple("${body.customer.firstName}")
                .setHeader("lastName").simple("${body.customer.lastName}")
                .setHeader("email").simple("${body.customer.email}")
                .removeHeaders("*", "customerId", "firstName", "lastName", "email") // remove headers to stop them being sent to the service
                .setBody(constant(null)) // can't pass a body in a GET request
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .recipientList()
                .simple("http4://localhost:8088/api/sales/customer/${exchangeProperty.id}/summary")
                .to("jms:queue:summary-regular-response");


////convert group to group ID        
        from("jms:queue:summary-regular-response")
                .unmarshal().json(JsonLibrary.Gson, Summary.class)
                .setProperty("numberOfSales").simple("${body.numberOfSales}")
                .setProperty("totalPayment").simple("${body.totalPayment}")
                .setProperty("group").simple("${body.group}")
                .setProperty("uri").simple("${body.uri}")
                .bean(Summary.class, "convertGroup(${exchangeProperty.numberOfSales},"
                        + "${exchangeProperty.totalPayment},"
                        + "${exchangeProperty.group},"
                        + "${exchangeProperty.uri})")
                .log("regular-group-converted: ${body}")
                .to("jms:queue:summary-regular-converted");

//find the group of sales where customer group changed
        from("jms:queue:summary-regular-converted")
                .choice()
                .when().simple("${body.group} == '0afa8de1-147c-11e8-edec-2b197906d816'")
                .to("jms:queue:regular-same")
                .otherwise()
                .to("jms:queue:regular-changed");
      //********************  
        
        from("jms:queue:regular-same")
                .multicast()
                .to("jms:queue:regular-changed-vend", "jms:queue:regular-changed-rest");

        from("jms:queue:regular-changed-vend")
                .setProperty("customerId").header("customerId")
                .setProperty("firstName").header("firstName")
                .setProperty("lastName").header("lastName")
                .setProperty("email").header("email")
                .setProperty("group").simple("${body.group}")
                .bean(Summary.class, "convertCustomer(${exchangeProperty.customerId},"
                        + "${exchangeProperty.firstName},"
                        + "${exchangeProperty.lastName},"
                        + "${exchangeProperty.email},"
                        + "${exchangeProperty.group})")
                .log("regular-customer-vend: ${body}")
                .to("jms:queue:customer-regular-changed-vend");
        
        from("jms:queue:regular-changed-rest")
                .setProperty("customerId").header("customerId")
                .setProperty("firstName").header("firstName")
                .setProperty("lastName").header("lastName")
                .setProperty("email").header("email")
                .setProperty("group").simple("${body.group}")
                .bean(Summary.class, "convertAccount(${exchangeProperty.customerId},"
                        + "${exchangeProperty.firstName},"
                        + "${exchangeProperty.lastName},"
                        + "${exchangeProperty.email},"
                        + "${exchangeProperty.group})")
                .log("regular-customer-rest: ${body}")
                .to("jms:queue:customer-regular-changed-rest");
        
        
        /*
         *vip customers
         */

//get summary for vip customer     
        from("jms:queue:group-vip")
                .setProperty("id").simple("${body.customer.id}")
                .setHeader("customerId").simple("${body.customer.id}")
                .setHeader("firstName").simple("${body.customer.firstName}")
                .setHeader("lastName").simple("${body.customer.lastName}")
                .setHeader("email").simple("${body.customer.email}")
                .removeHeaders("*", "customerId", "firstName", "lastName", "email") // remove headers to stop them being sent to the service
                .setBody(constant(null)) // can't pass a body in a GET request
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .recipientList()
                .simple("http4://localhost:8088/api/sales/customer/${exchangeProperty.id}/summary")
                .to("jms:queue:summary-vip-response");

//convert group to group ID        
        from("jms:queue:summary-vip-response")
                .unmarshal().json(JsonLibrary.Gson, Summary.class)
                .setProperty("numberOfSales").simple("${body.numberOfSales}")
                .setProperty("totalPayment").simple("${body.totalPayment}")
                .setProperty("group").simple("${body.group}")
                .setProperty("uri").simple("${body.uri}")
                .bean(Summary.class, "convertGroup(${exchangeProperty.numberOfSales},"
                        + "${exchangeProperty.totalPayment},"
                        + "${exchangeProperty.group},"
                        + "${exchangeProperty.uri})")
                .log("vip-group-converted: ${body}")
                .to("jms:queue:summary-vip-converted");

//find the group of sales where customer group changed
        from("jms:queue:summary-vip-converted")
                .choice()
                .when().simple("${body.group} == '0afa8de1-147c-11e8-edec-201e0f00872c'")
                .to("jms:queue:vip-same")
                .otherwise()
                .to("jms:queue:vip-changed");
        
        from("jms:queue:vip-changed")
                .multicast()
                .to("jms:queue:vip-changed-vend", "jms:queue:vip-changed-rest");

        from("jms:queue:vip-changed-vend")
                .setProperty("customerId").header("customerId")
                .setProperty("firstName").header("firstName")
                .setProperty("lastName").header("lastName")
                .setProperty("email").header("email")
                .setProperty("group").simple("${body.group}")
                .bean(Summary.class, "convertCustomer(${exchangeProperty.customerId},"
                        + "${exchangeProperty.firstName},"
                        + "${exchangeProperty.lastName},"
                        + "${exchangeProperty.email},"
                        + "${exchangeProperty.group})")
                .log("vip-customer-vend: ${body}")
                .to("jms:queue:customer-vip-changed-vend");
        
        from("jms:queue:vip-changed-rest")
                .setProperty("customerId").header("customerId")
                .setProperty("firstName").header("firstName")
                .setProperty("lastName").header("lastName")
                .setProperty("email").header("email")
                .setProperty("group").simple("${body.group}")
                .bean(Summary.class, "convertAccount(${exchangeProperty.customerId},"
                        + "${exchangeProperty.firstName},"
                        + "${exchangeProperty.lastName},"
                        + "${exchangeProperty.email},"
                        + "${exchangeProperty.group})")
                .log("vip-customer-rest: ${body}")
                .to("jms:queue:customer-vip-changed-rest");
        
        

       from("jms:queue:customer-regular-changed-vend")
                .setProperty("id").simple("${body.id}")
                .marshal().json(JsonLibrary.Gson)
               .log("regular-json: ${body}")
                .removeHeaders("*") // remove headers to stop them being sent to the service
                .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .recipientList().simple("https4://domain_prefix.vendhq.com/api/2.0/customers/${exchangeProperty.id}") // send to service
                .to("jms:queue:changed-regular-response-vend");

//        from("jms:queue:customer-vip-changed")
//                .setProperty("id").simple("${body.id}")
//                .marshal().json(JsonLibrary.Gson)
//                .removeHeaders("*") // remove headers to stop them being sent to the service
//                .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
//                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//                .recipientList().simple("https://domain_prefix.vendhq.com/api/2.0/customers/${exchangeProperty.id}") // send to service
//                .to("jms:queue:changed-vip-response");
    }

    public static String getPassword(String prompt) {
        JPasswordField txtPasswd = new JPasswordField();
        int resp = JOptionPane.showConfirmDialog(null, txtPasswd, prompt,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resp == JOptionPane.OK_OPTION) {
            String password = new String(txtPasswd.getPassword());
            return password;
        }
        return null;

    }

}
