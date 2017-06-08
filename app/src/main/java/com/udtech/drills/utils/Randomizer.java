package com.udtech.drills.utils;

import java.util.Random;
import timber.log.Timber;

/**
 * Created by Vrungel on 12.05.2017.
 */

public class Randomizer {
  private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";

  public static String randomString(final int length) {
    final Random random = new Random();
    final StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; ++i)
      sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
    Timber.e(sb.toString());
    return sb.toString();
  }

  public static Integer getRandomNumberInRange(int min, int max) {

    if (min == max) return max;

    if (min > max) {
      throw new IllegalArgumentException("max must be greater than min");
    }
    if (max == 1) return 0;


    Random r = new Random();
    int generatedNum = r.nextInt((max - min) + 1) + min;
    if (generatedNum == max) return generatedNum - 1;
    return generatedNum;
  }
}
