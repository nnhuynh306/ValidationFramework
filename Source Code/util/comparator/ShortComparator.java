package util.comparator;

public class ShortComparator implements Comparator<Short, Short> {
    @Override
    public int compare(Short value1, Short value2) {
        return Short.compare(value1, value2);
    }
}
