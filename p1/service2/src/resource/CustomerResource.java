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
public class CustomerResource extends Jooby {

    public CustomerResource(CustomerDAO dao) {

        path("/api/customers/account", () -> {

            /**
             * Update an existing account.
             *
             * @param id The product's ID.
             * @param body The new details for the item.
             * @return <code>204</code> for a successful operation.
             * <code>404</code> if the ID does not match an existing product.
             * <code>409</code> if user has attempted to change the ID (which is
             * not allowed).
             */
            put("/:username", (req, rsp) -> {
                String username = req.param("username").value();
                Customer newCustomer = req.body().to(Customer.class);

                if (!dao.exists(username)) {
                    rsp.status(Status.NOT_FOUND);

                    // make sure user is not changing the username, which could cause data
                    // corruption (PKs should always be immutable).
                } else if (!username.equals(newCustomer.getUserName())) {
                    rsp.status(Status.CONFLICT);
                } else {
                    rsp.status(Status.NO_CONTENT);
                    dao.updateAccount(username, newCustomer);
                }
            });

        }).produces(MediaType.json).consumes(MediaType.json);

    }

}
