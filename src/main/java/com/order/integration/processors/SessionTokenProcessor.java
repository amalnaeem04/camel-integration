package com.order.integration.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SessionTokenProcessor implements Processor {
    @Override
    public void process(Exchange exchange) {
        exchange.getIn().setBody("{\"userId\":\"DFHob6VYe2ZQuAnXbSxkyAMhnEF3\",\"deliveryAddress\":{\"id\":\"nZfOGemjuh517a3NsbPw\",\"street\":\"1010 Mobile St\",\"city\":\"Dallas\",\"state\":\"TX\",\"stateId\":null,\"zipCode\":\"75208\",\"longitude\":-96.8384763,\"latitude\":32.7697818,\"apartment\":\"\"},\"items\":[{\"id\":\"218893\",\"name\":\"Tuna Melt\",\"price\":{\"amount\":9.94,\"total\":9.94},\"quantity\":3,\"note\":null,\"provider\":null,\"everythingOffered\":\"\"}],\"merchant\":{\"id\":\"10000311\",\"name\":\"Cindi's NY Deli & Restaurant\",\"contact\":null,\"address\":{\"id\":null,\"street\":\"306 South Houston Street\",\"city\":\"Dallas\",\"state\":\"TX\",\"stateId\":null,\"zipCode\":\"75202\",\"longitude\":-96.80719,\"latitude\":32.77689,\"apartment\":\"\"}},\"recipes\":null,\"tip\":0.0,\"deliveryFee\":5.0,\"serviceCharges\":0.0,\"tax\":0.0,\"orderType\":\"DELIVERY\",\"serviceProvider\":\"MANUAL\",\"deliveryTime\":\"2022-05-17T10:01:45.987872+0500\",\"addOnInformation\":\"\",\"deliveryInstructions\":\"Call when arrive\"}"); //json values as required for the rest call.
    }
}
