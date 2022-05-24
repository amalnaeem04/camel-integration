package com.order.integration.routes;

import com.order.integration.processors.SessionTokenProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpMethod;

public class ExceptionHandlerRoute extends RouteBuilder {

    @Override
    public void configure() {

        String authToken = "";
        String slackToken = "";

        errorHandler(deadLetterChannel("jms:queue:dead")
                .maximumRedeliveries(3).redeliveryDelay(10000));


        from("direct:subroute")
                .routeId("route2")
                .log("Start sub route route2 for retrying")
                .setHeader(Exchange.HTTP_METHOD).constant(HttpMethod.POST)
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .setHeader("Authorization").simple("Bearer " + authToken)
                .process(new SessionTokenProcessor())
                .to("https://stage-lb.bitewell.com/order-service/api/v1/checkout")
                .log("Response : ${body}")
                .to("activemq:my-activemq-queue")
                .to("slack:#camel-notifications?token=RAW("+ slackToken +")");
    }
}
