package com.sqli.test.dishes.entities;

public class Dish
{
  private static class StationaryDish extends Dish
  {
    private StationaryDish(final double orientation)
    {
      super(orientation);
    }
    
    @Override
    public void move(double deltaOrientation)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private double orientation;

  private Dish(double orientation)
  {
    this.orientation = orientation;
  }
  
  public void move (final double deltaOrientation)
  {
    orientation += deltaOrientation;
  }
  
  public static Dish stationaryDish (final double orientation)
  {
    return new StationaryDish(orientation);
  }
  
  public static Dish ofInitialOrientation (final double initialOrientation)
  {
    return new Dish(initialOrientation);
  }
  
  public final double distanceTo (final Dish targetDish)
  {
    return Math.abs(orientation - targetDish.orientation);
  }
}
