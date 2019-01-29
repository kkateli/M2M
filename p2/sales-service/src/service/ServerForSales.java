/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.SaleDAO;
import domain.Customer;
import domain.Summary;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.apitool.ApiTool;
import org.jooby.handlers.Cors;
import org.jooby.handlers.CorsHandler;
import org.jooby.json.Gzon;
import resource.CustomerResource;
import resource.SaleResource;

/**
 *
 * @author jiaweili
 */
public class ServerForSales extends Jooby {

    public ServerForSales() {

        SaleDAO saleDAO = new SaleDAO();
        
        use("*", new CorsHandler(new Cors().withMethods("*")));
        use(new Gzon());
        use(new SaleResource(saleDAO));
        use(new CustomerResource(saleDAO));

        use(new ApiTool()
                .modify(r -> r.pattern().equals("/api/sales"), route -> {

                    // Fix response type since Swagger couldn't guess
                    route.response().type(new Customer[0].getClass());

                })
                .modify(r -> r.pattern().equals("/api/sales/customer/{id}/summary"), route-> {
                    route.response().type(Summary.class);
                })
                .swagger());

    }

    public static void main(String[] args) throws IOException {

        ServerForSales serverForSales = new ServerForSales();
        serverForSales.port(8088);

        CompletableFuture.runAsync(() -> {
            serverForSales.start();
        });

        serverForSales.onStarted(() -> {
            System.out.println("\nPress Enter to stop service.");
        });

        System.in.read();
        System.exit(0);
    }

}
