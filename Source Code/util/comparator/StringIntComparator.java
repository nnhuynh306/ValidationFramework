package util.comparator;

public class StringIntComparator implements Comparator<String, Integer> {

    @Override
    public int compare(String value1, Integer value2) {
        return Integer.compare(value1.length(), value2);
    }
}