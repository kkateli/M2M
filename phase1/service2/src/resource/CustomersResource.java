/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import dao.CustomerDAO;
import domain.Customer;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author jiaweili
 */
public class CustomersResource extends Jooby {

    public CustomersResource(CustomerDAO dao) {

        path("/api/customers", () -> {

            /**
             * A client needs to be able to get all registered customer
             * accounts.
             *
             * @return <code>200</code> with the customers in the collection.
             */
            get(() -> {
                return dao.getAccounts();
            });

            /**
             * A client needs to be able to create a new customer account.
             *
             * @param body The account to add to the collection.
             * @return <code>201</code> with the customer's details (including
             * the URI) if successful or <code>422</code> if a customer already
             * exists with the same ID.
             */
            post((req, rsp) -> {
                Customer customer = req.body(Customer.class);

                // construct the URI for this account
                String uri = "http://" + req.hostname() + ":" + req.port() + "" + req.path() + "/account/" + customer.getUserName();

                // tell the customer about its URI
                customer.setUri(uri);

                // store the account
                dao.addAccount(customer);

                // return a response containing the account
                rsp.status(Status.CREATED).send(customer);

            });

        }).produces(MediaType.json).consumes(MediaType.json);

    }

}
