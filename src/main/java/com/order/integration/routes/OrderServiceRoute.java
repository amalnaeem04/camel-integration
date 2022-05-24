package com.order.integration.routes;

import com.order.integration.processors.ExceptionProcessor;
import com.order.integration.processors.SessionTokenProcessor;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceRoute extends RouteBuilder {


    @Override
    public void configure() {

        String authToken = "";
        String slackToken = "";

        //exception handling
        onException(HttpOperationFailedException.class).process(new ExceptionProcessor())
                .redeliveryPolicyRef("testRedeliveryPolicyProfile")
                .handled(true)
                .choice()
                .when(simple("${exchange.getProperty(\"RESPONSE_CODE\")} contains '401'"))
                .to("direct:subroute")
                .end();

        //route definition
        from("timer:first-timer//start?repeatCount=1&period=10000")
                .routeId("Route1")
                .log("Http Route 1 started")
                .setHeader(Exchange.HTTP_METHOD).constant(HttpMethod.POST)
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .setHeader("Authorization").simple("Bearer " + authToken)
                .process(new SessionTokenProcessor())
                .to("https://stage-lb.bitewell.com/order-service/api/v1/checkout")
                .log("Success")
                .to("activemq:my-activemq-queue")
                        .to("slack:#camel?token=RAW("+ slackToken +")");
    }

}
