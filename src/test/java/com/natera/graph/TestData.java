package com.natera.graph;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author pavels
 */
public final class TestData {

    private TestData() {
        throw new IllegalStateException("Do not instance");
    }

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static String randomString(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String randomString() {
        return randomString(10);
    }

}
