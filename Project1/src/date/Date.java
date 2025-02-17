package date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Eddie Ward
 * date.Date for the account. Checks if the DOB of the account holder is valid.
 *
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Quadrennial: 4 years
     */
    public static final int QUADRENNIAL = 4;
    /**
     * Centennial: 100 years
     */
    public static final int CENTENNIAL = 100;
    /**
     * Quartercentennial: 400 years
     */
    public static final int QUATERCENTENNIAL = 400;
    /**
     * Legal age = 18
     */
    public static final int LEGAL_AGE = 18;

    private static final int[] DAYS_IN_MONTH = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    /**
     Constructor
     @param year  The year of the date.
     @param month The month of the date (1-12).
     @param day   The day of the date.
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     *  Call individual checks
     @return true if the date passes all validity checks, false otherwise.
     */
    public boolean isValid() {
        if (!isMonthValid()) return false;
        if (!isDayValid()) return false;
        if (!isLeapYearAdjustmentValid()) return false;
        return true; // If all checks pass
    }

    /**
     // Check if the month valid (1-12)
     @return true if the month is valid, false otherwise.
     */
    private boolean isMonthValid() {
        if (month < 1 || month > 12) {
            return false;
        }
        return true;
    }

    /**
     //Check if the day is valid within the month
     @return true if the day is within valid range, false otherwise.
     */
    private boolean isDayValid() {
        if (day < 1) {
            return false;
        }

        int maxDays = DAYS_IN_MONTH[month];

        if (day > maxDays) {
            return false;
        }

        return true;
    }

    /**
     // Check if February 29 is correctly handled in leap years
     @return true if the leap year adjustment is valid, false otherwise.
     */
    private boolean isLeapYearAdjustmentValid() {
        if (month == 2 && isLeapYear(year) && day > 29) {
            return false;
        }
        return true;
    }

    /**
     // Check date is in the future
     @return true if the date is not in the future, false otherwise.
     */
    public boolean isFutureDateValid() {
        LocalDate today = LocalDate.now();

        LocalDate inputDate = LocalDate.of(year, month, day);


        if (inputDate.isAfter(today)) {
            return false;
        }
        return true;
    }

    /**
     // Check at least 18 years old
     * @return true if the person is 18 or older, false otherwise.
     */
    public boolean isLegalAgeValid() {
        LocalDate today = LocalDate.now();
        LocalDate inputDate = LocalDate.of(year, month, day);

        if (ChronoUnit.YEARS.between(inputDate, today) < LEGAL_AGE) {
            return false;
        }
        return true;
    }

    /**
     // Check February 29 is handled in leap years
     @return true if leap year adjustment valid.false otherwise.
     */
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            }
            return true;
        }
        return false;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Date other = (Date) obj;
        return year == other.year && month == other.month && day == other.day;
    }


    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }


    @Override
    public int compareTo(Date other) {
        if (year != other.year) return Integer.compare(year, other.year);
        if (month != other.month) return Integer.compare(month, other.month);
        return Integer.compare(day, other.day);
    }

    /**
     * Getter method to get month
     * @return month
     * */
    public int getMonth(){
        return this.month;
    }

    /**
     * Getter method to get day
     * @return day
     * */
    public int getDay(){
        return this.day;
    }

    /**
     * Getter method to get year
     * @return year
     * */
    public int getYear(){
        return this.year;
    }


    /**
     * Test methods to test date.Date class
     */
    public static void testDateClass() {
        System.out.println("Tests...\n");

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        // Valid date
        Date validDate = new Date(currentYear - 20, 5, 15);
        System.out.println("Testing: " + validDate);
        System.out.println("Valid? " + validDate.isValid());

        // Valid date
        Date validDate2 = new Date(currentYear - 40, 15, 5);
        System.out.println("Testing: " + validDate2);
        System.out.println("Valid? " + validDate.isValid());

        //User under 18
        Date underageDate = new Date(currentYear - 15, 8, 10);
        System.out.println("Testing: " + underageDate);
        System.out.println("Valid? " + underageDate.isValid());

        // Future date.Date
        Date futureDate = new Date(currentYear + 1, 1, 1);
        System.out.println("Testing: " + futureDate);
        System.out.println("Valid? " + futureDate.isValid());

        // Feb 30 (Non-existent)
        Date invalidFebDate = new Date(2023, 2, 30);
        System.out.println("Testing: " + invalidFebDate);
        System.out.println("Valid? " + invalidFebDate.isValid());

        //Month 0
        Date invalidMonth = new Date(2023, 0, 15);
        System.out.println("Testing: " + invalidMonth);
        System.out.println("Valid? " + invalidMonth.isValid());

        System.out.println("\n completed.\n");
    }

    public static void main(String[] args) {
        testDateClass();
    }
}
