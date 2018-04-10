package com.sqli.test.dishes.utils;

public final class DishParseResult extends ImmutablePair<String, Double>
{
  private DishParseResult(String first, Double second)
  {
    super(first, second);
  }
  
  public String getCode ()
  {
    return first;
  }
  
  public double getOrientation ()
  {
    return second;
  }
  
  public static DishParseResult of (final String code, final double orientation)
  {
    return new DishParseResult(code, orientation);
  }
}
