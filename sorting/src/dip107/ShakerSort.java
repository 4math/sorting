package dip107;

import java.util.Comparator;

public class ShakerSort implements SortingAlgorithm {

    public static Comparator<Integer> ascending = Comparator.naturalOrder();

    public static Comparator<Integer> descending = (o1, o2) -> -ascending.compare(o1, o2);

    public static void sort(int[] data, int low, int high, int order) {
        int left = low;
        int right = high;

        Comparator<Integer> comp = order == 1 ? ascending : descending;

        while(left < right) {
            for(int i = left; i < right; i++) {
                if (comp.compare(data[i], data[i + 1]) > 0) {
                    swap(data, i, i + 1);
                }
            }
            right--;

            for(int i = right; i > left; i--) {
                if (comp.compare(data[i], data[i - 1]) < 0) {
                    swap(data, i, i - 1);
                }
            }
            left++;
        }
    }

    private static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    @Override
    public void sort(int[] data, int order) {
        sort(data, 0, data.length - 1, order);
    }
}
