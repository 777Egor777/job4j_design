package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void  main(String[] args) {
        byte b = 100;
        short sh = 1000;
        int i = 1000000;
        long l = 1000000000000L;
        char c = 'a';
        boolean bool = true;
        float f = 1.4f;
        double d = 100.7;
        LOG.warn("Primitive types values: {}, {}, {}, {}, {}, {}, {}, {}", b, sh, i, l, c, bool, f, d);
    }
}
