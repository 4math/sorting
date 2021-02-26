package dip107;

public class CountingSort implements SortingAlgorithm{

    @Override
    public void sort(int[] arr, int order){
        sortModified(arr, order);
    }

    public static void sortModified(int[] arr, int order) {
        int min = Integer.MAX_VALUE / 2, max = Integer.MIN_VALUE / 2;
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
