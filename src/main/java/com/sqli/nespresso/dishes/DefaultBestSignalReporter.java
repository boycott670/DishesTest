package com.sqli.nespresso.dishes;

import java.util.Collections;
import java.util.Optional;
import java.util.function.BiFunction;

final class DefaultBestSignalReporter implements BestSignalReporter
{

  @Override
  public String reportBestSignal(double bestSignal)
  {
    BiFunction<Character, Integer, String> repeatChar = (c, n) -> String.join("",
        Collections.nCopies(n, String.valueOf(c)));

    return Optional.of(Long.valueOf(Math.round(bestSignal / .1))
        .intValue())
        .filter(numberOfDots -> numberOfDots < 10)
        .map(numberOfDots -> String.format("|%s%s|", repeatChar.apply('*', 10 - numberOfDots),
            repeatChar.apply('.', numberOfDots)))
        .orElse("No signal !");
  }

}
