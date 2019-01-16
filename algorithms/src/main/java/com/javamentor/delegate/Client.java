package com.javamentor.delegate;

public class Client {

    private BusinessDelegate businessService;

    public Client(BusinessDelegate businessService) {
        this.businessService  = businessService;
    }

    public String doTask() {
        return businessService.doTask();
    }
}
