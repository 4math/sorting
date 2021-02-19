package dip107;

public class QuickSort implements Test {

    public static void sort(int[] arr, int low, int high, int order) {
        if (low < high) {
            int part;
            if (order == 1) {
                part = partitionAscending(arr, low, high);
            } else {
                part = partitionDescending(arr, low, high);
            }
            sort(arr, low, part, order);
            sort(arr, part + 1, high, order);
        }
    }

    private static int partitionAscending(int[] arr, int low, int high) {
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


    private static int partitionDescending(int[] arr, int low, int high) {
        int pivot = arr[ (high + low) / 2 ];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do { i++; } while(arr[i] > pivot);
            do { j--; } while(arr[j] < pivot);

            if ( i >= j) { return j; }
            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void test(int[] arr, int order) {
        sort(arr, 0, arr.length - 1, order);
    }

}
