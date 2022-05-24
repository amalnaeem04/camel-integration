package com.order.integration.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;

public class ExceptionProcessor implements Processor {
    @Override
    public void process(Exchange exchange) {
        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        HttpOperationFailedException httpOperationFailedException = (HttpOperationFailedException)e;
        exchange.setProperty("RESPONSE_CODE", Integer.toString(httpOperationFailedException.getStatusCode()));
    }
}
