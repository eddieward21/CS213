public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
};

public static final int QUADRENNIAL = 4;
public static final int CENTENNIAL = 100;
public static final int QUATERCENTENNIAL = 400;

private static final int[] DAYS_IN_MONTH = {
        0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31;
};

public Date(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
}

