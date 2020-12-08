package ru.job4j.gc;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class GCDemo {
    private final static Runtime ENVIRONMENT = Runtime.getRuntime();
    private static final long KB = 1024;
    private static final long MB = KB * KB;

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory() / MB;
        final long totalMemory = ENVIRONMENT.totalMemory() / MB;
        final long maxMemory = ENVIRONMENT.maxMemory() / MB;
        System.out.println("=== Environment state ===");
        System.out.printf("freeMemory: %d%n", freeMemory);
        System.out.printf("totalMemory: %d%n", totalMemory);
        System.out.printf("maxMemory: %d%n", maxMemory);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 500000; ++i) {
            new User(20 + i / 60000, 50 + i / 10000);
        }
        info();
    }
}
