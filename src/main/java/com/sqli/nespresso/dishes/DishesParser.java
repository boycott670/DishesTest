package com.sqli.nespresso.dishes;

import java.util.Map;

interface DishesParser
{
  void setOrientationParser(final OrientationParser orientationParser);

  Map<String, Dish> parseDishes(final String[] dishes);
}
