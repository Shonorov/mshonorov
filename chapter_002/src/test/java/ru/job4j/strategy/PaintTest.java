package ru.job4j.strategy;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class PaintTest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Stores output from the console to ByteArray.
     * @Before runs method before test.
     */
    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }
    /**
     * Returns output from ByteArray back to the console.
     * @After runs method after test.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }
//    Before code refactoring.
//
//    @Test
//    public void whenDrawSquare() {
//        // получаем ссылку на стандартный вывод в консоль.
//        PrintStream stdout = System.out;
//        // Создаем буфур для хранения вывода.
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        //Заменяем стандартный вывод на вывод в пямять для тестирования.
//        System.setOut(new PrintStream(out));
//        // выполняем действия пишушиее в консоль.
//        new Paint().draw(new Square());
//        // проверяем результат вычисления
//        assertThat(
//                new String(out.toByteArray()),
//                is(
//                        new StringBuilder()
//                                .append("++++").append(System.lineSeparator())
//                                .append("+  +").append(System.lineSeparator())
//                                .append("+  +").append(System.lineSeparator())
//                                .append("++++").append(System.lineSeparator())
//                                .toString()
//                )
//        );
//        // возвращаем обратно стандартный вывод в консоль.
//        System.setOut(stdout);
//    }

    /**
     * Test after code refactoring.
     */
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("++++").append(System.lineSeparator())
                                .append("+  +").append(System.lineSeparator())
                                .append("+  +").append(System.lineSeparator())
                                .append("++++").append(System.lineSeparator())
                                .toString()
                )
        );
    }
//    Before code refactoring.
//
//    @Test
//    public void whenDrawTriangle() {
//        PrintStream stdout = System.out;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//        new Paint().draw(new Triangle());
//        assertThat(
//                new String(out.toByteArray()),
//                is(
//                        new StringBuilder()
//                                .append("   ^  ").append(System.lineSeparator())
//                                .append("  ^ ^ ").append(System.lineSeparator())
//                                .append(" ^   ^").append(System.lineSeparator())
//                                .append("^^^^^^^").append(System.lineSeparator())
//                                .toString()
//                )
//        );
//        System.setOut(stdout);
//    }
    /**
     * Test after code refactoring.
     */
    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   ^  ").append(System.lineSeparator())
                                .append("  ^ ^ ").append(System.lineSeparator())
                                .append(" ^   ^").append(System.lineSeparator())
                                .append("^^^^^^^").append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
