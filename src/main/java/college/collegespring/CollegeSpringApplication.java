package college.collegespring;


import college.boot.SpringApplication;

import java.util.concurrent.atomic.AtomicInteger;


public class CollegeSpringApplication {

    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private final int threadLocalHashCode = nextHashCode();

    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    public static void main(String[] args) {
        SpringApplication.run(CollegeSpringApplication.class, args);
    }
}
