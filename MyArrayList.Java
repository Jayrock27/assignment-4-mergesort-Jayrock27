import java.util.ArrayList;
import java.util.Comparator;

public class MyArrayList<E> implements ListInterface<E> {
    private ArrayList<E> data;

    public MyArrayList() {
        this.data = new ArrayList<>();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        if (data == null || data.size() < 2) {
            return;
        }
        mergeSort(data, c, 0, data.size() - 1);
    }

    private void mergeSort(ArrayList<E> data, Comparator<? super E> c, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(data, c, left, mid);
            mergeSort(data, c, mid + 1, right);
            merge(data, c, left, mid, right);
        }
    }

    private void merge(ArrayList<E> data, Comparator<? super E> c, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        ArrayList<E> leftList = new ArrayList<>(leftSize);
        ArrayList<E> rightList = new ArrayList<>(rightSize);

        for (int i = 0; i < leftSize; i++) {
            leftList.add(data.get(left + i));
        }
        for (int i = 0; i < rightSize; i++) {
            rightList.add(data.get(mid + 1 + i));
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (c.compare(leftList.get(i), rightList.get(j)) <= 0) {
                data.set(k, leftList.get(i));
                i++;
            } else {
                data.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            data.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightSize) {
            data.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public void add(E element) {
        data.add(element);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public static void main(String[] args) {
        MyArrayList<Double> myList = new MyArrayList<>();

        // Adding sample data to MyArrayList
        myList.add(5.4);
        myList.add(1.2);
        myList.add(3.9);
        myList.add(8.7);
        myList.add(2.5);

        System.out.println("Before sorting: " + myList);

        // Sorting using a Comparator for Double values
        myList.sort(Comparator.naturalOrder());

        System.out.println("After sorting: " + myList);
    }
}
