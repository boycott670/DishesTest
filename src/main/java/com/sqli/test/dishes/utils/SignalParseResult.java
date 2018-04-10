package com.sqli.test.dishes.utils;

import com.sqli.test.dishes.entities.Dish;

public final class SignalParseResult extends ImmutablePair<String, Dish>
{
  private SignalParseResult(String first, Dish second)
  {
    super(first, second);
  }
  
  public String getSignalName ()
  {
    return first;
  }
  
  public Dish getSatellite ()
  {
    return second;
  }
  
  public static SignalParseResult of (final String signalName, final Dish satellite)
  {
    return new SignalParseResult(signalName, satellite);
  }
}
