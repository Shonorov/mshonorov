package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 * @author Mikhail Shonorov
 * @version $Id$
 * @since 0.1
 */

public class CalculateTest {
	/**
	 * Test echo.
	 */
	@Test
	public void whenSetNameThenEchoPlusName() {
		Calculate calc = new Calculate();
		String input = "Mikhail Shonorov";
		String expect = "Echo, echo, echo : Mikhail Shonorov";
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}