package io.demo.datetime;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.Date;

public class DateTimeDemoTest extends TestCase {

  public void testDateTime() {

    DateTimeDemo.addOneWeek();
    System.out.println("Next Month: "+ DateTimeDemo.addOneMonth());

    DateTimeDemo.getSecondSaturdayOfNextMonth();
    DateTimeDemo.getInstantMilliseconds();
    DateTimeDemo.getInstantOfLocalDateTimeInMilliseconds();

    DateTimeDemo.dateDemo();

    DateTimeDemo.adjusters();
    DateTimeDemo.backwardCompatability();


    LocalDate nextTuesday = DateTimeDemo.getNextTuesday();
    System.out.println("Next Tuesday: " + nextTuesday);

//    nextTuesday

  }
}