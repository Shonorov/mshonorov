package ru.job4j.coffe;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachineTest {

    @Test
    public void when50cost35ThenChange15() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] expect = new int[] {10, 5};
        assertThat(machine.changes(50, 35), is(expect));
    }

    @Test
    public void whenNoMoneyThenNull() {
        CoffeeMachine machine = new CoffeeMachine();
        assertNull(machine.changes(35, 50));
    }
}
