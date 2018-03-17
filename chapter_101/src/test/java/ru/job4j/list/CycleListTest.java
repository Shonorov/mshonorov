package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CycleListTest {

    @Test
    public void whenHasCycleThenTrue() {
        CycleList<Integer> cycleList = new CycleList<>();
        CycleElement first = new CycleElement(1);
        CycleElement second = new CycleElement(2);
        CycleElement third = new CycleElement(3);
        CycleElement four = new CycleElement(4);
        cycleList.add(first);
        cycleList.add(second);
        cycleList.add(third);
        cycleList.add(four);
        assertThat(cycleList.hasCycle(first), is(false));
        third.setNext(second);
        assertThat(cycleList.hasCycle(second), is(true));
        assertThat(cycleList.hasCycle(four), is(false));
    }
}
