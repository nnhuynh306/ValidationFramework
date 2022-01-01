package util.comparator;

public class ByteComparator implements Comparator<Byte, Byte>{
    @Override
    public int compare(Byte value1, Byte value2) {
        return Byte.compare(value1, value2);
    }
}
