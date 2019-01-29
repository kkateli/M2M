/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import dao.SaleDAO;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author jiaweili
 */
public class CustomerResource extends Jooby {

    public CustomerResource(SaleDAO dao) {

        path("/api/sales/customer", () -> {

            /**
             * Get all sales for a specific customer.
             *
             * @param id The customer's ID.
             * @return <code>200</code> with the sale list matching the given id
             * in the body, or a <code>404</code> if the ID does not match an
             * existing customer.
             */
            get("/:id", (req) -> {

                String id = req.param("id").value();
                if (dao.ifCustomerExists(id)) {
                    return dao.getSalesById(id);
                } else {
                    throw new Err(Status.NOT_FOUND, "No customer matches that ID");
                }
            });

            /**
             * Get s summary for a specific customer.
             *
             * @param id The customer's ID.
             * @return <code>200</code> with the summary matching the given id
             * in the body, or a <code>404</code> if the ID does not match an
             * existing customer.
             */
            get("/:id/summary", (req) -> {

                String id = req.param("id").value();

                if (dao.ifCustomerExists(id)) {
                    return dao.getSummaryById(id);
                } else {
                    throw new Err(Status.NOT_FOUND, "No customer matches that ID");
                }

            });

        }).produces(MediaType.json).consumes(MediaType.json);
    }

}
