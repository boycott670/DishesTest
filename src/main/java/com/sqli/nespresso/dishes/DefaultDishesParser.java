package com.sqli.nespresso.dishes;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

final class DefaultDishesParser implements DishesParser
{
  private OrientationParser orientationParser;

  @Override
  public void setOrientationParser(OrientationParser orientationParser)
  {
    this.orientationParser = orientationParser;
  }

  @Override
  public Map<String, Dish> parseDishes(String[] dishes)
  {
    return Arrays.stream(dishes)
        .map(dish -> dish.split(","))
        .collect(Collectors.toMap(dishTokens -> dishTokens[0],
            dishTokens -> new Dish(orientationParser.parseOrientation(dishTokens[1]))));
  }

}
