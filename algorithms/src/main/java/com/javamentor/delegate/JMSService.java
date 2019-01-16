package com.javamentor.delegate;

public class JMSService implements BusinessService {

    @Override
    public String doProcessing() {
        System.out.println("Processing task by invoking JMS Service");
        return "JMS";
    }
}
