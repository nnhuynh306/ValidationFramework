package util.comparator;

public class LongComparator implements Comparator<Long, Long> {
    @Override
    public int compare(Long value1, Long value2) {
        return Long.compare(value1, value2);
    }
}
