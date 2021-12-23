package util.comparator;

public class DoubleComparator implements Comparator<Double, Double>{
    @Override
    public int compare(Double value1, Double value2) {
        return Double.compare(value1, value2);
    }
}
