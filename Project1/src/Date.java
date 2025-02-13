import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int LEGAL_AGE = 18;

    private static final int[] DAYS_IN_MONTH = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    // Constructor
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // ✅ Main validation method (Calls individual checks)
    public boolean isValid() {
        if (!isMonthValid()) return false;
        if (!isDayValid()) return false;
        if (!isLeapYearAdjustmentValid()) return false;
        return true; // If all checks pass
    }

    // ✅ Check if the month is valid (1-12)
    private boolean isMonthValid() {
        if (month < 1 || month > 12) {
            return false;
        }
        return true;
    }

    // ✅ Check if the day is valid within the month
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

    // ✅ Check if February 29 is correctly handled in leap years
    private boolean isLeapYearAdjustmentValid() {
        if (month == 2 && isLeapYear(year) && day > 29) {
            return false;
        }
        return true;
    }

    // ✅ Check if the date is in the future
    public boolean isFutureDateValid() {
        LocalDate today = LocalDate.now();

        LocalDate inputDate = LocalDate.of(year, month, day);


        if (inputDate.isAfter(today)) {
            return false;
        }
        return true;
    }

    // ✅ Check if the user is at least 18 years old
    public boolean isLegalAgeValid() {
        LocalDate today = LocalDate.now();
        LocalDate inputDate = LocalDate.of(year, month, day);

        if (ChronoUnit.YEARS.between(inputDate, today) < LEGAL_AGE) {
            return false;
        }
        return true;
    }

    // ✅ Helper method: Check if the year is a leap year
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            }
            return true;
        }
        return false;
    }

    // ✅ Override equals() for date comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Date other = (Date) obj;
        return year == other.year && month == other.month && day == other.day;
    }

    // ✅ Override toString() for readable output
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    // ✅ Implement compareTo() for sorting
    @Override
    public int compareTo(Date other) {
        if (year != other.year) return Integer.compare(year, other.year);
        if (month != other.month) return Integer.compare(month, other.month);
        return Integer.compare(day, other.day);
    }

    public int getMonth(){
        return this.month;
    }

    public int getDay(){
        return this.day;
    }

    public int getYear(){
        return this.year;
    }

    // ✅ Test method for Date class
    public static void testDateClass() {
        System.out.println("\n🔹 Running Date Class Tests...\n");

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        // ✅ Test 1: Valid past date (age > 18)
        Date validDate = new Date(currentYear - 20, 5, 15);
        System.out.println("Testing: " + validDate);
        System.out.println("Valid? " + validDate.isValid());

        // ✅ Test 2: Invalid - User under 18
        Date underageDate = new Date(currentYear - 15, 8, 10);
        System.out.println("Testing: " + underageDate);
        System.out.println("Valid? " + underageDate.isValid());

        // ✅ Test 3: Invalid - Future Date
        Date futureDate = new Date(currentYear + 1, 1, 1);
        System.out.println("Testing: " + futureDate);
        System.out.println("Valid? " + futureDate.isValid());

        // ✅ Test 4: Invalid - Feb 30 (Non-existent date)
        Date invalidFebDate = new Date(2023, 2, 30);
        System.out.println("Testing: " + invalidFebDate);
        System.out.println("Valid? " + invalidFebDate.isValid());

        // ✅ Test 5: Invalid - Month 0
        Date invalidMonth = new Date(2023, 0, 15);
        System.out.println("Testing: " + invalidMonth);
        System.out.println("Valid? " + invalidMonth.isValid());

        System.out.println("\n✅ All Date tests completed.\n");
    }

    // ✅ Main method calls testDateClass()
    public static void main(String[] args) {
        testDateClass();
    }
}
