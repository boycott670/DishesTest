package com.sqli.test.dishes.presenters;

import java.util.Collections;

public final class DefaultSignalStrengthPresenter implements SignalStrengthPresenter
{
  @Override
  public String presentSignalStrength(double signalStrength)
  {
    final int scaledSignalStrength = Integer.valueOf(String.format("%.0f", signalStrength / .1));
    
    if (scaledSignalStrength >= 10)
    {
      return "No signal !";
    }
    
    return String.format("|%s%s|",
        String.join("", Collections.nCopies(10 - scaledSignalStrength, "*")),
        String.join("", Collections.nCopies(scaledSignalStrength, ".")));
  }
}
