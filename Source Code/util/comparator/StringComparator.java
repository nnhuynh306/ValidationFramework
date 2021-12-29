package util.comparator;

public class StringComparator implements Comparator<String, String> {

    @Override
    public int compare(String value1, String value2) {
        return value1.compareTo(value2);
    }
}
