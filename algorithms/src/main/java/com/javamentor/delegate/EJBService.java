package com.javamentor.delegate;

public class EJBService implements BusinessService {

    @Override
    public String doProcessing() {
        System.out.println("Processing task by invoking EJB Service");
        return "EJB";
    }
}
