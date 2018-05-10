package com.sqli.nespresso.dishes;

final class Dish
{
  private double orientation;

  Dish(double orientation)
  {
    this.orientation = orientation;
  }

  void move(final double orientation)
  {
    this.orientation += orientation;
  }

  double getOrientation()
  {
    return orientation;
  }
}
