package dip107;

public class ShakerSort {
    private static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void sort(int[] data) {
        int left = 0;
        int right = data.length - 1;

        while(left < right) {
            for(int i = left; i < right; i++) {
                if (data[i] > data[i + 1]) {
                    swap(data, i, i + 1);
                }
            }
            right--;

            for(int i = right; i > left; i--) {
                if (data[i] < data[i - 1]) {
                    swap(data, i, i - 1);
                }
            }
            left++;
        }
    }
}
