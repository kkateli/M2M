/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import dao.SaleDAO;
import domain.Customer;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author jiaweili
 */
public class SaleResource extends Jooby {

    public SaleResource(SaleDAO dao) {

        path("/api/sales", () -> {

            /**
             * add a new sale to the collection.
             *
             * @param body The sale to add to the collection.
             * @return <code>201</code> with the sale's details if successful or
             * <code>422</code> if a sale already exists with the same ID.
             */
            //customer os the body, so it only can be one
            post((req, rsp) -> {
                Sale sale = req.body(Sale.class);
                System.out.println("\n** POST called on REST service: **");
                System.out.println(sale);
//                

                // construct the URI for this sale
                String uri = "http://" + req.hostname() + ":" + req.port() + "" + req.path() + "/customer/" + sale.getId();

                // tell the sale about its URI
                sale.setUri(uri);
                

                // store the sale
                dao.addSale(sale);
                

                // return a response containing the sale
                rsp.status(Status.CREATED).send(sale);

            });

        }).produces(MediaType.json).consumes(MediaType.json);

    }
}
