import java.util.Comparator;

public interface ListInterface<E> {
    void sort(Comparator<? super E> c);
    // other methods in the interface
}
