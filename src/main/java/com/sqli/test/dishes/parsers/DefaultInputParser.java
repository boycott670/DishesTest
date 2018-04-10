package com.sqli.test.dishes.parsers;

import java.util.Map;

import com.sqli.test.dishes.entities.Dish;
import com.sqli.test.dishes.utils.DishParseResult;
import com.sqli.test.dishes.utils.SignalParseResult;

public final class DefaultInputParser implements InputParser
{
  @Override
  public DishParseResult parseDish(String dishInput)
  {
    final String[] tokens = dishInput.split(",");
    
    double orientation = Double.valueOf(tokens[1].substring(0, tokens[1].length() - 1));
    
    orientation *= tokens[1].charAt(tokens[1].length() - 1) == 'E' ? 1 : -1;
    
    return DishParseResult.of(tokens[0], orientation);
  }

  @Override
  public SignalParseResult parseSignal(String signalInput, Map<? super String, ? extends Dish> satellites)
  {
    final String[] tokens = signalInput.split(", ");
    
    return SignalParseResult.of(tokens[0], satellites.get(tokens[1]));
  }
}
