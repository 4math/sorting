package dip107;

public class CountingSort implements SortingAlgorithm{

    @Override
    public void sort(int[] arr, int order){
        sortDefault(arr, order);
    }

    public static void sortDefault(int[] arr, int order) {
        int offset, n = arr.length;
        int[] result = new int[n];
        int[] equal = new int[n];
        int[] less = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    equal[i]++;
                } else if (arr[i] > arr[j]) {
                    less[i]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            offset = less[i];
            for (int j = 0; j < equal[i]; j++) {
                result[offset + j] = arr[i];
            }
        }
        if (order == 1) {
            for (int i = 0; i < n; i++) {
                arr[i] = result[i];
            }
        } else {
            for (int i = 0; i < n; i++) {
                arr[n - i - 1] = result[i];
            }
        }
    }

    public static void modifiedSort(int[] arr, int order) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int[] extra = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            extra[arr[i] - min]++;
        }
        if (order == 1) {
            int q = 0;
            for (int i = 0; i < extra.length; i++) {
                for (int j = 0; j < extra[i]; j++) {
                    arr[q] = min + i;
                    q++;
                }
            }
        } else {
            int q = arr.length - 1;
            for (int i = 0; i < extra.length; i++) {
                for (int j = 0; j < extra[i]; j++) {
                    arr[q] = min + i;
                    q--;
                }
            }
        }
    }
}
