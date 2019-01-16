package com.javamentor.delegate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BusinessDelegateTest {

    @Test
    public void whenDelegateThenProcessRightServiceName() {
        BusinessDelegate businessDelegate = new BusinessDelegate();
        Client client = new Client(businessDelegate);
        businessDelegate.setServiceType("EJB");
        assertThat(client.doTask(), is("EJB"));
        businessDelegate.setServiceType("JMS");
        assertThat(client.doTask(), is("JMS"));
    }

}