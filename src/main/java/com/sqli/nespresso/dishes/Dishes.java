package com.sqli.nespresso.dishes;

import java.util.Map;

public final class Dishes
{
  private static enum Satellite
  {
    A(19.2), H(13), N(-7);

    private final double orientation;

    private Satellite(final double orientation)
    {
      this.orientation = orientation;
    }

    private double signal(final Dish dish)
    {
      return Math.abs(orientation - dish.getOrientation());
    }
  }

  private final DishesParser dishesParser = new DefaultDishesParser();

  private final OrientationParser orientationParser = new DefaultOrientationParser();

  private final BestSignalReporter bestSignalReporter = new DefaultBestSignalReporter();

  private final Map<String, Dish> dishes;

  public Dishes(final String... dishes)
  {
    dishesParser.setOrientationParser(orientationParser);

    this.dishes = dishesParser.parseDishes(dishes);
  }

  public String signal(final String canal)
  {
    final Satellite satellite = Satellite.valueOf(canal.split(", ")[1]);

    final double bestSignal = dishes.values()
        .stream()
        .mapToDouble(satellite::signal)
        .min()
        .getAsDouble();

    return bestSignalReporter.reportBestSignal(bestSignal);
  }
  
  public void move(final String dish, final String orientation)
  {
    dishes.get(dish).move(orientationParser.parseOrientation(orientation));
  }
}
