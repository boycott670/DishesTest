package com.sqli.test.dishes.presenters;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class DefaultSignalStrengthPresenter implements SignalStrengthPresenter
{
  @Override
  public String presentSignalStrength(double signalStrength)
  {
    final int scaledSignalStrength = Integer.valueOf(String.format("%.0f", signalStrength / .1));
    
    if (scaledSignalStrength > 10)
    {
      return "No signal !";
    }
    
    final String stars = IntStream.rangeClosed(scaledSignalStrength + 1, 10)
        .mapToObj(__ -> "*")
        .collect(Collectors.joining());
      
    final String dots = IntStream.rangeClosed(1, scaledSignalStrength)
        .mapToObj(__ -> ".")
        .collect(Collectors.joining());
    
    return String.format("|%s%s|", stars, dots);
  }
}
