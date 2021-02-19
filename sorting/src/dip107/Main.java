package dip107;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Test[] tests = {new QuickSort()};
        TestingFramework tf = new TestingFramework(tests);

        tf.createMinMaxTable();
        tf.createDescendingTable();
        tf.createAlmostSortedTable();

        tf.printTable();

//        int[] noChange = createRandomArray(10, 200);
//        QuickSort.sort(noChange, 0, noChange.length - 1, 1);
//        printArr(noChange);
//        int[] arr = createAlmostSortedArray(new QuickSort(), 10, 200);
//        printArr(arr);
//        QuickSort.sort(arr, 0, arr.length - 1, -1);
//        long start = System.nanoTime();
//        QuickSort.sort(arr, 0, arr.length - 1, 1);
//        long end = System.nanoTime();
//
//        long timeElapsed = (end - start) / 1000;
//        System.out.println(timeElapsed);
////        printArr(arr);
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%2d\t", arr[i]);
        }
        System.out.println();
    }

    public static int[] createRandomArray(int size, int seed) {
        int[] arr = new int[size];
        Random random = new Random(seed);

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }

        return arr;
    }

    public static int[] createAlmostSortedArray(Test sortingAlg, int size, int seed) {
        int[] arr = new int[size];
        Random random = new Random(seed);

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }

        sortingAlg.test(arr, 1);

        int changeCount = random.nextInt(arr.length / 2) + 2;

        System.out.println("Change count " + changeCount);
        for (int i = 0; i < changeCount; i++) {
            int number = random.nextInt();
            int index = random.nextInt(arr.length);
            arr[index] = number;
        }

        return arr;
    }
}
