import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import model.utility.Utility;

/**
 * Tests to test the utility class.
 */
public class UtilityTest {

  @Test
  public void getEqualPeriod_Year() {
    LocalDate from = LocalDate.parse("2014-03-27");
    LocalDate to = LocalDate.parse("2022-11-15");
    Set<LocalDate> set = Utility.getEqualPeriod(from, to);
    Assert.assertEquals(10, set.size());
  }

  @Test
  public void getEqualPeriod_Day() {
    LocalDate from = LocalDate.parse("2022-11-15");
    LocalDate to = LocalDate.parse("2022-11-15");
    Set<LocalDate> set = Utility.getEqualPeriod(from, to);
    Assert.assertEquals(1, set.size());
  }

  @Test
  public void getEqualPeriod_2Day() {
    LocalDate from = LocalDate.parse("2022-11-10");
    LocalDate to = LocalDate.parse("2022-11-11");
    Set<LocalDate> set = Utility.getEqualPeriod(from, to);
    Assert.assertEquals(2, set.size());
  }

  @Test
  public void getEqualPeriod_Month() {
    LocalDate from = LocalDate.parse("2014-03-27");
    LocalDate to = LocalDate.parse("2014-04-27");
    Set<LocalDate> set = Utility.getEqualPeriod(from, to);
    Assert.assertEquals(17, set.size());
  }

  @Test
  public void getEqualPeriod_35Days() {
    LocalDate from = LocalDate.parse("2014-03-27");
    LocalDate to = from.plusDays(34);
    Set<LocalDate> set = Utility.getEqualPeriod(from, to);
    Assert.assertEquals(18, set.size());
  }

  @Test
  public void getEqualPeriod_() {
    LocalDate from = LocalDate.parse("2014-03-27");
    LocalDate to = from.plusDays(90);
    Set<LocalDate> set = Utility.getEqualPeriod(from, to);
    Assert.assertEquals(14, set.size());
  }

  @Test
  public void getEqualPeriod_147Days() {
    LocalDate from = LocalDate.parse("2014-03-27");
    LocalDate to = from.plusDays(147);
    Set<LocalDate> set = Utility.getEqualPeriod(from, to);
    Assert.assertEquals(22, set.size());
  }

  @Test
  public void getEqualPeriod_150Days() {
    LocalDate from = LocalDate.parse("2014-03-27");
    LocalDate to = from.plusDays(211);
    Set<LocalDate> set = Utility.getEqualPeriod(from, to);
    Assert.assertEquals(9, set.size());
  }

  @Test
  public void scale1() {
    double scale = Utility.scale(10000, 0);
    Assert.assertEquals(200.0, scale, 0.1);
  }

  @Test
  public void scale2() {
    double scale = Utility.scale(10000, 9000);
    Assert.assertEquals(320.0, scale, 0.1);
  }

  @Test
  public void scale3() {
    double scale = Utility.scale(10000, 90);
    Assert.assertEquals(200.0, scale, 0.1);
  }

  @Test
  public void scale4() {
    double scale = Utility.scale(10000, 900);
    Assert.assertEquals(200.0, scale, 0.1);
  }

  @Test
  public void scale5() {
    double scale = Utility.scale(10000, 5000);
    Assert.assertEquals(200.0, scale, 0.1);
  }

  @Test
  public void scale6() {
    double scale = Utility.scale(10000, 9700);
    Assert.assertEquals(320.0, scale, 0.1);
  }

  @Test
  public void scale7() {
    double scale = Utility.scale(Integer.MAX_VALUE, 0);
    Assert.assertEquals(1.0E8, scale, 0.1);
  }

  @Test
  public void scale8() {
    double scale = Utility.scale(2000, 0);
    Assert.assertEquals(50, scale, 0.1);
  }

  @Test
  public void scale9() {
    double scale = Utility.scale(71000, 0);
    Assert.assertEquals(2000, scale, 0.1);
  }

}
