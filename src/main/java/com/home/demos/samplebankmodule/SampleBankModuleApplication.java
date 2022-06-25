package com.home.demos.samplebankmodule;

import com.home.demos.samplebankmodule.rest.cards.CardResource;
import com.home.demos.samplebankmodule.rest.clients.ClientResource;
import com.home.demos.samplebankmodule.rest.payments.PaymentResource;

import java.time.LocalDateTime;

import static spark.Spark.*;

public class SampleBankModuleApplication {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now() + ": SPARK server starting");
        long start = System.currentTimeMillis();
        int port = 8080;
        port(port);

        path("/", () -> {
            path("/payments", () -> {
                post("", PaymentResource.create());
                get("/:clientId", PaymentResource.findAllForClient());

            });

            path("/cards", () -> {
                post("", CardResource.create());
                get("/:clientId", CardResource.findAllForClient());
            });

            path("/clients", () -> {
                post("", ClientResource.create());
                get("/:clientId", ClientResource.findAll());
            });
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(exception.getMessage());
        });

        awaitInitialization();
        System.out.println(
                LocalDateTime.now()
                        + ": SPARK server started in "
                        + (((double) System.currentTimeMillis() - (double) start) / 1000) + "s. "
                        + "on port: " + port
        );
    }
}
