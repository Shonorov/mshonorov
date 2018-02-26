package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ProfessionsTest {

    @Test
    public void engineerMethodsTest() {
        Engineer engineer = new Engineer("Иван");
        String result = engineer.build(new House("г.Москва, Красная площадь 1, 1"));
        String expect = "Инженер Иван строит дом по адресу: г.Москва, Красная площадь 1, 1";
        assertThat(result, is(expect));
    }

    @Test
    public void doctorMethodsTest() {
        Doctor doctor = new Doctor("Сергей");
        String result = doctor.heal(new Patient("Валера", "Ветрянка"));
        String expect = "Доктор Сергей лечит пациента Валера от болезни Ветрянка";
        assertThat(result, is(expect));
    }

    @Test
    public void teacherMethodsTest() {
        Teacher teacher = new Teacher("Владимир");
        String result = teacher.teach(new Student("Илья"));
        String expect = "Учитель Владимир учит студента Илья";
        assertThat(result, is(expect));
    }
}
