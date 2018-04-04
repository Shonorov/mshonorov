package ru.job4j.trade;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MarketTest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final String menu = new StringJoiner(System.lineSeparator(), "", "")
            .add("Gazprom")
            .add(String.format("%s\t\t%s\t%s", "Продажа", "Цена", "Покупка")).toString();
    /**
     * Stores output from the console to ByteArray.
     * @Before runs method before test.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    /**
     * Returns output from ByteArray back to the console.
     * @After runs method after test.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenAddThenRemove() {
        Market market = new Market();
        market.addItem("Gazprom", Type.ADD, Action.SELL, 50, 3);
        market.addItem("Gazprom", Type.DELETE, Action.SELL, 50, 3);
        market.print();
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add(String.format("%n%n")).toString();
        Assert.assertThat(out.toString(), is(result));
    }

    @Test
    public void whenSellSamePriceThenCombine() {
        Market market = new Market();
        market.addItem("Gazprom", Type.ADD, Action.SELL, 50, 3);
        market.addItem("Gazprom", Type.ADD, Action.SELL, 50, 5);

        market.print();
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add(String.format("%n%s\t\t\t%s%n", 8, 50.0)).toString();
        Assert.assertThat(out.toString(), is(result));
    }

    @Test
    public void whenSellMoreThenSellTwo() {
        Market market = new Market();
        market.addItem("Gazprom", Type.ADD, Action.BUY, 50, 3);
        market.addItem("Gazprom", Type.ADD, Action.SELL, 49, 5);

        market.print();
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add(String.format("%n%s\t\t\t%s%n", 2, 49.0)).toString();
        Assert.assertThat(out.toString(), is(result));
    }

    @Test
    public void whenSellLessThenBuyOne() {
        Market market = new Market();
        market.addItem("Gazprom", Type.ADD, Action.BUY, 50, 3);
        market.addItem("Gazprom", Type.ADD, Action.SELL, 49, 2);

        market.print();
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add(String.format(String.format("%n\t\t\t%s\t%s%n", 49.0, 1))).toString();
        Assert.assertThat(out.toString(), is(result));
    }

    @Test
    public void whenSellSameAmountThenDelete() {
        Market market = new Market();
        market.addItem("Gazprom", Type.ADD, Action.BUY, 50, 3);
        market.addItem("Gazprom", Type.ADD, Action.SELL, 50, 3);

        market.print();
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add(String.format(String.format("%n%n"))).toString();
        Assert.assertThat(out.toString(), is(result));
    }

    @Test
    public void whenDifferentPriceThenDisplay() {
        Market market = new Market();
        market.addItem("Gazprom", Type.ADD, Action.BUY, 50, 3);
        market.addItem("Gazprom", Type.ADD, Action.SELL, 51, 4);

        market.print();
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add(String.format(String.format("%n\t\t\t%s\t%s", 50.0, 3)))
                .add(String.format(String.format("%s\t\t\t%s%n", 4, 51.0))).toString();
        Assert.assertThat(out.toString(), is(result));
    }

    @Test
    public void whenDifferentPriceThenSorted() {
        Market market = new Market();
        market.addItem("Gazprom", Type.ADD, Action.BUY, 50, 3);
        market.addItem("Gazprom", Type.ADD, Action.SELL, 51, 4);
        market.addItem("Gazprom", Type.ADD, Action.SELL, 52, 5);
        market.print();
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add(String.format(String.format("%n\t\t\t%s\t%s", 50.0, 3)))
                .add(String.format(String.format("%s\t\t\t%s", 5, 52.0)))
                .add(String.format(String.format("%s\t\t\t%s%n", 4, 51.0))).toString();
        Assert.assertThat(out.toString(), is(result));
    }
}
