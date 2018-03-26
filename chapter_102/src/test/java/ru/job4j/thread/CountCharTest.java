package ru.job4j.thread;

import org.junit.Test;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CountCharTest {
    @Test
    public void whenParentStopThenChildStop() {
        CountChar countChar = new CountChar("Язык программирования Java и JVM (Java Virtual Machine) разработаны с поддержкой параллельных вычислений, и все вычисления выполняются в контексте потока. Несколько потоков могут совместно использовать объекты и ресурсы; каждый поток выполняет свои инструкции (код), но потенциально может получить доступ к любому объекту в программе. В обязанности программиста входит координация (или «синхронизация») потоков во время операций чтения и записи разделяемых объектов. Синхронизация потоков нужна для того, чтобы гарантировать, что одновременно к объекту может обращаться только один поток, и чтобы предотвратить обращение потоков к неполностью обновленным объектам в то время, как с ними работает другой поток. В языке Java есть встроенные конструкции поддержки синхронизации потоков.");
        Time time = new Time(1);
        Thread count = new Thread(countChar);
        Thread stop = new Thread(time);
        stop.start();
        count.start();
        try {
            stop.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
