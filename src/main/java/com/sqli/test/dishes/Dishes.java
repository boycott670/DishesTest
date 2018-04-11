package com.sqli.test.dishes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import com.sqli.test.dishes.entities.Dish;
import com.sqli.test.dishes.parsers.DefaultInputParser;
import com.sqli.test.dishes.parsers.InputParser;
import com.sqli.test.dishes.presenters.DefaultSignalStrengthPresenter;
import com.sqli.test.dishes.presenters.SignalStrengthPresenter;
import com.sqli.test.dishes.utils.DishParseResult;
import com.sqli.test.dishes.utils.SignalParseResult;

public final class Dishes
{
  private final InputParser inputParser = new DefaultInputParser();
  private final SignalStrengthPresenter signalStrengthPresenter = new DefaultSignalStrengthPresenter();
  
  public static final Dish ASTRA = Dish.stationaryDish(19.2);
  public static final Dish HOTBIRD = Dish.stationaryDish(13);
  public static final Dish NILESAT = Dish.stationaryDish(-7);
  
  private final Map<? super String, ? extends Dish> dishes;
  private final Map<? super String, ? extends Dish> satellites;
  
  public Dishes (final String... dishesInputs)
  {
    dishes = Collections.unmodifiableMap(Arrays.stream(dishesInputs)
      .map(inputParser::parseDish)
      .collect(Collectors.toMap(
          DishParseResult::getCode,
          dishParseResult -> Dish.ofInitialOrientation(dishParseResult.getOrientation()))));
    
    satellites = Collections.unmodifiableMap(Arrays.asList(
      SignalParseResult.of("A", ASTRA),
      SignalParseResult.of("H", HOTBIRD),
      SignalParseResult.of("N", NILESAT))
        .stream()
        .collect(Collectors.toMap(SignalParseResult::getSignalName, SignalParseResult::getSatellite)));
  }
  
  public String signal (final String signalInput)
  {
    final Dish targetSatellite = inputParser.parseSignal(signalInput, satellites).getSatellite();
    
    final double signalStrength = dishes.values()
      .stream()
      .mapToDouble(targetSatellite::distanceTo)
      .min()
      .getAsDouble();
    
    return signalStrengthPresenter.presentSignalStrength(signalStrength);
  }
  
  public void move (final String dishCode, final String orientation)
  {
    dishes.get(dishCode).move(inputParser.parseOrientation(orientation));
  }
}
