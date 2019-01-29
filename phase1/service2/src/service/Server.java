/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CustomerDAO;
import domain.Customer;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.apitool.ApiTool;
import org.jooby.json.Gzon;
import resource.CustomerResource;
import resource.CustomersResource;

/**
 *
 * @author jiaweili
 */
public class Server extends Jooby {

    public Server() {

        CustomerDAO dao = new CustomerDAO();

        use(new Gzon());
        use(new CustomerResource(dao));
        use(new CustomersResource(dao));

        use(new ApiTool()
                .modify(r -> r.pattern().equals("/api/customers"), route -> {

                    // Fix response type since Swagger couldn't guess
                    route.response().type(new Customer[0].getClass());

                })
                .swagger());

    }

    public static void main(String[] args) throws IOException {

        Server server = new Server();

        server.port(8082);

        CompletableFuture.runAsync(() -> {
            server.start();
        });

        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop service.");
        });

        System.in.read();
        System.exit(0);

    }

}
