package model.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

/**
 * Calendar utility used to perform utility methods on calendar, period of time such as
 * get all working days for a period of time.
 */
public class Utility {

  /**
   * Returns equally spaced dates from the start and end range of the period.
   *
   * @param from the start date of the time range.
   * @param to   the end date of the time range.
   * @return the equally spaced periods of time i.e. monthly, daily, weekly, etc.
   */
  public static Set<LocalDate> getEqualPeriod(LocalDate from, LocalDate to) {

    int delta = 0;
    if (isRangeBetween(ChronoUnit.YEARS, from, to, 1)) {
      delta = 365;
    } else if (isRangeBetween(ChronoUnit.MONTHS, from, to, 4)) {
      delta = 120;
    } else if (isRangeBetween(ChronoUnit.MONTHS, from, to, 1)) {
      delta = 30;
    } else if (isRangeBetween(ChronoUnit.WEEKS, from, to, 1)) {
      delta = 7;
    } else if (ChronoUnit.DAYS.between(from, to) >= 30) {
      delta = 2;
    } else {
      delta = 1;
    }

    Set<LocalDate> days = new HashSet<>();
    for (LocalDate date = from; !date.isAfter(to); date = date.plusDays(delta)) {
      days.add(date);
    }
    if (!days.contains(to)) {
      days.add(to);
    }
    return days;
  }

  /**
   * Returns if the range is between the Chrono unit type (i.e. day, weeks, months)
   *
   * @param type the type of scale to be used.
   * @param from the start date of the interval.
   * @param to the start date of the interval.
   * @param factor the factor diving the range
   * @return if the range is between max and min scale.
   */
  private static boolean isRangeBetween(ChronoUnit type, LocalDate from, LocalDate to,
                                        int factor) {
    long days = type.between(from, to) / factor;
    return days <= 30 && days >= 5;
  }

  /**
   * Calculate and update values for tick spacing and nice
   * minimum and maximum data points on the axis.
   *
   * @param max Max value
   * @param min Min Value
   * @return scale of the data.
   */
  public static double scale(int max, int min) {
    var range = evaluate(max - min, false);
    var tickSpacing = evaluate(range / 50, true);
    while (max / tickSpacing > 50) {
      tickSpacing = tickSpacing * 2;
    }
    return tickSpacing;
  }

  /**
   * Returns a "nice" number approximately equal to range Rounds
   * the number if round = true Takes the ceiling if round = false.
   *
   * @param range the data range
   * @param round whether to round the result
   * @return a "nice" number to be used for the data range
   */
  private static double evaluate(double range, boolean round) {
    double exponent;
    double fraction;

    exponent = Math.floor(Math.log10(range));
    fraction = range / Math.pow(10, exponent);

    return fraction(round, fraction) * Math.pow(10, exponent);
  }

  private static int fraction(boolean round, double fraction) {
    int finalFraction = 0;
    if (round) {
      if (fraction < 1.5) {
        finalFraction = 1;
      } else if (fraction < 3) {
        finalFraction = 2;
      } else if (fraction < 7) {
        finalFraction = 5;
      } else {
        finalFraction = 10;
      }
    } else {
      if (fraction <= 1) {
        finalFraction = 1;
      } else if (fraction <= 2) {
        finalFraction = 2;
      } else if (fraction <= 5) {
        finalFraction = 5;
      } else {
        finalFraction = 10;
      }
    }
    return finalFraction;
  }


}
