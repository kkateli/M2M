/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinator;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 * @author jiaweili
 */
public class CoordinatorRouter {
    public static void main(String[] args) throws Exception {
        // create default context
        CamelContext camel = new DefaultCamelContext();

// register ActiveMQ as the JMS handler
        ActiveMQConnectionFactory activeMqFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        JmsComponent jmsComponent = JmsComponent.jmsComponent(activeMqFactory);
        camel.addComponent("jms", jmsComponent);

// transfer the entire exchange, or just the body and headers?
//sey false to true so that it sends the entire message in the payload
        jmsComponent.setTransferExchange(true);

// trust all classes being used to send serialised domain objects
        activeMqFactory.setTrustAllPackages(true);

// turn exchange tracing on or off (false is off)
        camel.setTracing(false);

// enable stream caching so that things like loggers don't consume the messages
        camel.setStreamCaching(true);

// create and add the builder(s)
        camel.addRoutes(new AccountCoordinatorBuilder());
        camel.addRoutes(new SaleCoordinatorBuilder());
        

// start routing
        System.out.println("Starting router...");
        camel.start();
        System.out.println("... ready.  Press enter to shutdown.");
        System.in.read();
        camel.stop();
    }
    
}
