package ru.job4j.rsp.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SalaryConverterTest {

    @Test
    public void convertSalary() {
        SalaryConverter converter = new SalaryConverter(2);
        double salary = 85;
        double expected = salary * 2;
        double result = converter.convert(salary);
        assertEquals(result, expected, 0.0001);
    }
}