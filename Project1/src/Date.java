public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    private static final int[] DAYS_IN_MONTH = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    };

    public boolean isValid() {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }

        int maxDaysInMonth = DAYS_IN_MONTH[month];

        if (month == 2 && isLeapYear(year)) {
            maxDaysInMonth = 29;
        }
        return day <= maxDaysInMonth;

    }

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
        if(this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Date other = (Date) obj;
        return year == other.year && month == other.month && day == other.day;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year,month,day);
    }

    @Override
    public int compareTo(Date other) {
        if(year != other.year) {
            return Integer.compare(year, other.year);
        }
        if (month != other.month) {
            return Integer.compare(month, other.month);
        }
        return Integer.compare(day, other.day);
    }

    public static void testDateClass() {
        System.out.println("testDate()");

        Date testDate = new Date(2024, 2, 9);
        System.out.println("Testing: " + testDate);
        System.out.println("Valid: " + testDate.isValid());
    }

    public static void main(String[] args) {
        testDateClass();
    }
}
