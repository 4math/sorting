package dip107;

public class QuickSort implements Test {

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int part = partition(arr, low, high);
            sort(arr, low, part);
            sort(arr, part + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[ (high + low) / 2 ];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do { i++; } while(arr[i] < pivot);
            do { j--; } while(arr[j] > pivot);

            if ( i >= j) { return j; }
            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void test(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

}
