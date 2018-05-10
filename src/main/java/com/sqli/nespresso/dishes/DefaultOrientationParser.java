package com.sqli.nespresso.dishes;

final class DefaultOrientationParser implements OrientationParser
{

  @Override
  public double parseOrientation(String orientation)
  {
    final int direction = orientation.charAt(orientation.length() - 1) == 'E' ? 1 : -1;

    return Double.valueOf(orientation.substring(0, orientation.length() - 1)) * direction;
  }

}
