package util.comparator;

public class IntegerComparator implements Comparator<Integer, Integer> {
    @Override
    public int compare(Integer value1, Integer value2) {
        return Integer.compare(value1, value2);
    }
}
