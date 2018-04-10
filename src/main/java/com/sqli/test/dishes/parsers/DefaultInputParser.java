package com.sqli.test.dishes.parsers;

import java.util.Map;

import com.sqli.test.dishes.entities.Dish;
import com.sqli.test.dishes.utils.DishParseResult;
import com.sqli.test.dishes.utils.SignalParseResult;

public final class DefaultInputParser implements InputParser
{
  @Override
  public double parseOrientation(String orientationInput)
  {
    double orientation = Double.valueOf(orientationInput.substring(0, orientationInput.length() - 1));
    
    orientation *= orientationInput.charAt(orientationInput.length() - 1) == 'E' ? 1 : -1;
    
    return orientation;
  }

  @Override
  public DishParseResult parseDish(String dishInput)
  {
    final String[] tokens = dishInput.split(",");
    
    return DishParseResult.of(tokens[0], parseOrientation(tokens[1]));
  }

  @Override
  public SignalParseResult parseSignal(String signalInput, Map<? super String, ? extends Dish> satellites)
  {
    final String[] tokens = signalInput.split(", ");
    
    return SignalParseResult.of(tokens[0], satellites.get(tokens[1]));
  }
}
