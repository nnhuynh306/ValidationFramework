package util.comparator;

import java.util.Date;

public class DateComparator implements Comparator<Date, Date>{
    @Override
    public int compare(Date value1, Date value2) {
        return value1.compareTo(value2);
    }
}
