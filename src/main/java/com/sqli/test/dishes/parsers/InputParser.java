package com.sqli.test.dishes.parsers;

import java.util.Map;

import com.sqli.test.dishes.entities.Dish;
import com.sqli.test.dishes.utils.DishParseResult;
import com.sqli.test.dishes.utils.SignalParseResult;

public interface InputParser
{
  DishParseResult parseDish (final String dishInput);
  SignalParseResult parseSignal (final String signalInput, final Map<? super String, ? extends Dish> satellites);
}
