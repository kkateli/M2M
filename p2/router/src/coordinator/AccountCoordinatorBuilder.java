/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinator;

import domain.Customer;
import domain.CustomerAccount;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 *
 * @author jiaweili
 */
public class AccountCoordinatorBuilder extends RouteBuilder {

    @Override
    public void configure() {
        
        
        from("jetty:http://localhost:8882/customers?enableCORS=true")
                // make message in-only so web browser doesn't have to wait on a non-existent response
                .setExchangePattern(ExchangePattern.InOnly)
                .log("accountlist: ${body}")
                .to("jms:queue:account-list");
        
        //in order to include group in object message and Json
        from("jms:queue:account-list")
                // convert JSON to domain object
                .unmarshal().json(JsonLibrary.Gson, CustomerAccount.class)
                .log("accountobject: ${body}")
                .to("jms:queue:account-object");
        
        //convert object to Json
        from("jms:queue:account-object")
                // convert to JSON using marshal method
                .marshal().json(JsonLibrary.Gson)
                // ensure the message body is a string
                .convertBodyTo(String.class)
                .log("account-json: ${body}")
                // send to a queue that expects JSON
                .to("jms:queue:account-json");
        
        from("jms:queue:account-json")
                .multicast()
                .to("jms:queue:account-json-copy1", "jms:queue:account-json-copy2");

        from("jms:queue:account-json-copy1")
                // remove all headers
                .removeHeaders("*")
                 .log("accountcopy1: ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http4://localhost:8082/api/customers")
                .to("jms:queue:accounts-response"); 

        from("jms:queue:account-json-copy2")
                // convert JSON to domain object
                .unmarshal().json(JsonLibrary.Gson, CustomerAccount.class)
                .log("accountobject: ${body}")
                .to("jms:queue:account");

        from("jms:queue:account")
                .bean(CustomerAccount.class, "createAccount(${body.firstName},${body.lastName},${body.email},${body.group})")
                .log("vend-account: ${body}")
                .to("jms:queue:vend-account");

        from("jms:queue:vend-account")
                // convert from domain object to JSON
                .marshal().json(JsonLibrary.Gson)
                .log("vend-account-json: ${body}")
                .to("jms:queue:vend-account-json");
        
        from("jms:queue:vend-account-json")
                // remove headers so they don't get sent to Vend
                .removeHeaders("*")
                // add authentication token to authorization header
                .setHeader("Authorization", constant("Bearer KiQSsELLtocyS2WDN5w5s_jYaBpXa0h2ex1mep1a"))
                // marshal to JSON
                //.marshal().json(JsonLibrary.Gson) // only necessary if the message is an object, not JSON
                
                .setHeader(Exchange.CONTENT_TYPE).constant("application/json")
                // set HTTP method
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                // send it
                .to("https4://info303otago.vendhq.com/api/customers")
                // store the response
                .to("jms:queue:vend-response");

//        

//
        


    }
}
