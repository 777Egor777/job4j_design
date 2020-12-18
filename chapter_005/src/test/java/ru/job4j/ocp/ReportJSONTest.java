package ru.job4j.ocp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.rsp.*;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportJSONTest {
    private static Store store;
    private static Calendar now;
    private static Employee ivan;
    private static FormatReportEngine engine;

    @Before
    public void doBeforeEachTest() {
        store = new MemStore();
        now = Calendar.getInstance();
        ivan = new Employee("Ivan", now, now, 100);
        store.add(ivan);
        engine = new ReportJSON(store);
    }

    @Test
    public void generate() {
        String result = engine.generate(emp -> true);
        String expected = "{" + System.lineSeparator()
                        + "  \"employees\": [" + System.lineSeparator()
                        + "    {" + System.lineSeparator()
                        + "      \"name\": \"Ivan\"," + System.lineSeparator()
                        + "      \"hired\": \"" + ivan.getHired() + "\"," + System.lineSeparator()
                        + "      \"fired\": \"" + ivan.getFired() + "\"," + System.lineSeparator()
                        + "      \"salary\": \"" + ivan.getSalary() + "\"" + System.lineSeparator()
                        + "    }" + System.lineSeparator()
                        + "  ]" + System.lineSeparator()
                        + "}";
        assertThat(result, is(expected));
    }

    @Test
    public void makeField() {
        String result = engine.makeField("key", "value");
        String expected = "      \"key\": \"value\"";
        assertThat(result, is(expected));
    }

    @Test
    public void makeItem() {
        String result = engine.makeItem(engine, ivan);
        String expected = "    {" + System.lineSeparator()
                        + "      \"name\": \"Ivan\"," + System.lineSeparator()
                        + "      \"hired\": \"" + ivan.getHired() + "\"," + System.lineSeparator()
                        + "      \"fired\": \"" + ivan.getFired() + "\"," + System.lineSeparator()
                        + "      \"salary\": \"" + ivan.getSalary() + "\"" + System.lineSeparator()
                        + "    }";
        assertThat(result, is(expected));
    }
}