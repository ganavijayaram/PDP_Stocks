package model.plot;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * A public class for LineGraph which implements Graph interface.
 */
public class LineGraph implements Graph {

  @Override
  public int findYears(String date1, String date2) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
    LocalDate startDate = LocalDate.parse(date1, formatter);
    LocalDate endDate = LocalDate.parse(date2, formatter);
    Period difference = Period.between(startDate, endDate);

    return difference.getYears();
  }

  @Override
  public int findMonth(String date1, String date2) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
    LocalDate startDate = LocalDate.parse(date1, formatter);
    LocalDate endDate = LocalDate.parse(date2, formatter);
    Period difference = Period.between(startDate, endDate);

    return difference.getMonths();
  }

  @Override
  public int findDays(String date1, String date2) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");

    LocalDate startDate = LocalDate.parse(date1, formatter);
    LocalDate endDate = LocalDate.parse(date2, formatter);
    Period difference = Period.between(startDate, endDate);
    return difference.getDays();
  }

  @Override
  public Double maxValue(TreeMap<Date, Double> map, Date start, Date end) {
    Double max = map.get(start);
    for (Date d : map.keySet()) {
      if (d.compareTo(start) <= 0 && d.compareTo(end) >= 0 && map.get(d) > max) {
        max = map.get(d);
      }
    }
    return max;
  }

  @Override
  public Double minValue(TreeMap<Date, Double> map, Date start, Date end) {
    Double min = map.get(start);
    for (Date d : map.keySet()) {
      if (d.compareTo(start) <= 0 && d.compareTo(end) >= 0 && map.get(d) < min) {
        min = map.get(d);
      }
    }
    return min;
  }

  @Override
  public double getBaseValue(double max, double min) {
    return (max - min) / 50.00;
  }

  @Override
  public List<String> yAxis(int numOfLines) {
    List<String> l = new ArrayList<>();
    if (numOfLines > 5 && numOfLines < 7) {
      l.add("Sunday     :");
      l.add("Monday     :");
      l.add("Tuesday    :");
      l.add("Wednesday  :");
      l.add("Thurday    :");
      l.add("Friday     :");
      l.add("Saturday   :");
      return l;
    } else if (numOfLines == 12) {
      l.add("January    :");
      l.add("February   :");
      l.add("March      :");
      l.add("April      :");
      l.add("May        :");
      l.add("June       :");
      l.add("July       :");
      l.add("August     :");
      l.add("September  :");
      l.add("October    :");
      l.add("November   :");
      l.add("December   :");
      return l;
    } else if (numOfLines > 12) {
      l.add("1 :");
      l.add("2 :");
      l.add("3 :");
      l.add("4 :");
      l.add("5 :");
      l.add("6 :");
      l.add("7 :");
      l.add("8 :");
      l.add("9 :");
      l.add("10 :");
      l.add("11 :");
      l.add("12 :");
      l.add("13 :");
      l.add("14 :");
      l.add("15 :");
      l.add("16 :");
      l.add("17 :");
      l.add("18 :");
      l.add("19 :");
      l.add("20 :");
      l.add("21 :");
      l.add("22 :");
      l.add("23 :");
      l.add("24 :");
      l.add("25 :");
      l.add("26 :");
      l.add("27 :");
      l.add("28 :");
      l.add("29 :");
      l.add("30 :");
      return l;
    }
    return new ArrayList<>();
  }
}



