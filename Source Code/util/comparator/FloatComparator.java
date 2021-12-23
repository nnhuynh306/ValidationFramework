package util.comparator;

public class FloatComparator implements Comparator<Float, Float> {
    @Override
    public int compare(Float value1, Float value2) {
        return Float.compare(value1, value2);
    }
}
