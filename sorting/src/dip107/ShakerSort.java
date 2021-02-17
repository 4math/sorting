package dip107;

public class ShakerSort {
    private static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void sort(int[] data) {
        int l = 0;
        int r = data.length - 1;

        while(l < r) {
            for(int i = l; i < r; i++) {
                if (data[i] > data[i + 1]) {
                    swap(data, i, i + 1);
                }
            }
            r--;

            for(int i = r; i > l; i--) {
                if (data[i] < data[i - 1]) {
                    swap(data, i, i - 1);
                }
            }
            l++;
        }
    }
}
