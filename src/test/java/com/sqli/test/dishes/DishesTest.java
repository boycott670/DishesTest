package com.sqli.test.dishes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DishesTest
{
  @Test
  public void RTMShouldBeWellDisplayed() {
    // FORMAT : "id,orientation"
    Dishes dishes = new Dishes("1,7W");
    // FORMAT : "name, SATELLITE"
    assertEquals("|**********|", dishes.signal("RTM, N"));
  }
  
  @Test
  public void ZDFShouldBeDisplayedBadlyAndEuronewsShouldNotBeDisplayed() {
    Dishes dishes = new Dishes("1,7W", "2,19E");
    assertEquals("No signal !", dishes.signal("Euronews, H"));
    assertEquals("|********..|", dishes.signal("ZDF, A"));
  }
  
  @Test
  public void shouldFindTheBestQuality() throws Exception {
    Dishes dishes = new Dishes("1,19E", "2,19.3E");
    assertEquals("|*********.|", dishes.signal("ZDF, A"));
  }
}
