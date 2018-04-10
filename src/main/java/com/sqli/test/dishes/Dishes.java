package com.sqli.test.dishes;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.sqli.test.dishes.entities.Dish;
import com.sqli.test.dishes.parsers.DefaultInputParser;
import com.sqli.test.dishes.parsers.InputParser;
import com.sqli.test.dishes.presenters.DefaultSignalStrengthPresenter;
import com.sqli.test.dishes.presenters.SignalStrengthPresenter;
import com.sqli.test.dishes.utils.DishParseResult;

public final class Dishes
{
  private final InputParser inputParser = new DefaultInputParser();
  private final SignalStrengthPresenter signalStrengthPresenter = new DefaultSignalStrengthPresenter();
  
  public static final Dish ASTRA = Dish.stationaryDish(19.2);
  public static final Dish HOTBIRD = Dish.stationaryDish(13);
  public static final Dish NILESAT = Dish.stationaryDish(-7);
  
  private final Map<? super String, Dish> dishes;
  
  public Dishes (final String... dishesInputs)
  {
    dishes = Arrays.stream(dishesInputs)
      .map(inputParser::parseDish)
      .collect(Collectors.toMap(
          DishParseResult::getCode,
          dishParseResult -> Dish.ofInitialOrientation(dishParseResult.getOrientation())));
    
    dishes.put("A", ASTRA);
    dishes.put("H", HOTBIRD);
    dishes.put("N", NILESAT);
  }
  
  public String signal (final String signalInput)
  {
    final double signalStrength = dishes.entrySet()
      .stream()
      .filter(entry -> !Arrays.asList("A", "H", "N").contains(entry.getKey()))
      .map(Entry::getValue)
      .mapToDouble(inputParser.parseSignal(signalInput, dishes).getSatellite()::distanceTo)
      .min()
      .getAsDouble();
    
    return signalStrengthPresenter.presentSignalStrength(signalStrength);
  }
}
