package io.demo.datetime;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateTimeDemo {

	public static void addOneWeek() {
		LocalDate today = LocalDate.now();
		LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);

		System.out.println("Next week: "+nextWeek);
	}
	
	public static LocalDate addOneMonth() {
		LocalDate today = LocalDate.now();
		LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
		return nextMonth;
	}
	
	public static LocalDate getNextTuesday() {
		LocalDate today = LocalDate.now();
		LocalDate nextTuesday = today.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
		return nextTuesday;
	}
	
	public static void getSecondSaturdayOfNextMonth() {
		LocalDate today = LocalDate.now();
		LocalDate firstInYear  = LocalDate.of(today.getYear(), today.getMonth(), 1);
		LocalDate secondSaturday = firstInYear
				.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
				.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		
		System.out.println("secondSaturday: " + secondSaturday);
	}

	public static void getInstantMilliseconds() {
		
		Date currentDate=new Date();
		
		//Get the instant of current date in terms of milliseconds
		Instant now = currentDate.toInstant();	
		
		System.out.println(now);
	}
	
	public static void getInstantOfLocalDateTimeInMilliseconds() {
		Date currentDate=new Date();
		Instant now = currentDate.toInstant();	
		ZoneId currentZone = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZone);
		System.out.println("Zoned date: " + zonedDateTime);
	}
	
	public static void dateDemo() {

		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("Now: " + currentTime);
		
		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();
		
		System.out.println("Month: " + month + " Day: " + day + " Seconds: " + seconds);
	
		LocalDate date1 = currentTime.toLocalDate();
		System.out.println("Date1: " + date1);
		
		LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("Date2: " + date2);

		LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println("Date3: " + date3);

		LocalTime time1 = LocalTime.of(22, 15);
		System.out.println("Time1: " + time1);
		
		LocalTime time2 = LocalTime.parse("22:15:30");
		System.out.println("Time2: " + time2);
		
		ZonedDateTime zonedate1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
		System.out.println("zonedate1: " + zonedate1);
		
		ZoneId zoneId = ZoneId.of("Europe/Paris");
		System.out.println("currentZone: " + zoneId);

		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("currentZone: " + currentZone);
	}	
	
	
	public static void adjusters() {
		LocalDate today = LocalDate.now();
		System.out.println("today: " + today);
		
		//next Tuesday
		LocalDate nextTuesday = today.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
		System.out.println("nextTuesday: " + nextTuesday);

		//Second Saturday of next month
		LocalDate firstInMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);
		LocalDate secondSaturday = firstInMonth
				.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
				.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println("Second Saturday on: " + secondSaturday);
	}
	
	
	public static void backwardCompatability() {
		//Get the current date
	      Date currentDate = new Date();
	      System.out.println("Current date: " + currentDate);
			
	      //Get the instant of current date in terms of milliseconds
	      Instant now = currentDate.toInstant();
	      ZoneId currentZone = ZoneId.systemDefault();
			
	      LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZone);
	      System.out.println("Local date: " + localDateTime);
			
	      ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZone);
	      System.out.println("Zoned date: " + zonedDateTime);
	}
}
