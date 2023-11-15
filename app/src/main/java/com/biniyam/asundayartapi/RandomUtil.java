package com.biniyam.asundayartapi;

import java.util.Random;

public class RandomUtil {

    public static int getRandomPageNumber(int totalPages) {
        Random random = new Random();
        int randomPage = random.nextInt(totalPages) + 1; // Add 1 to avoid generating 0
        return randomPage;
    }
}
