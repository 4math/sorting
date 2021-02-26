package dip107;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Name");
        System.out.println("Name");
        System.out.println("Name");

        boolean fFullTest = false;
        boolean fOutputAvg = false;

        for (String arg : args) {
            if (arg.compareTo("-fOutputAvg") == 0) {
                fOutputAvg = true;
            }

            if (arg.compareTo("-fFullTest") == 0) {
                fFullTest = true;
            }
        }
        SortingAlgorithm[] algorithms = {new QuickSort(), new CountingSort(), new ShakerSort()};
        TestingFramework tf = new TestingFramework(algorithms, 100);

        if (fFullTest) {
            test(tf, 100);
            tf.writeTimeResultsToCSV("TimeResults.csv");
        } else if (fOutputAvg) {
            test(tf, 100);
            tf.writeAvgResultsToCSV("AvgResults.csv");
        } else {

            try {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter array element count: ");
                int count = scanner.nextInt();

                int[] array = new int[count];
                System.out.println("items: ");
                for (int i = 0; i < count; i++) {
                    array[i] = scanner.nextInt();
                }

                System.out.println("Choose the sorting method: ");
                System.out.println("1: Quicksort method");
                System.out.println("2: Shaker sort method");
                System.out.println("3: Counting sort method");

                int choice = scanner.nextInt();
                while (choice < 1 || choice > 3) {
                    System.out.println("Choose the sorting method: ");
                    choice = scanner.nextInt();
                }

                switch (choice) {
                    case 1:
                        QuickSort.sort(array, 0, array.length - 1, 1);
                        break;
                    case 2:
                        ShakerSort.sort(array, 0, array.length - 1, 1);
                        break;
                    case 3:
                        CountingSort.sortModified(array, 1);
                        break;
                    default:
                        return;
                }

                System.out.println("result: ");
                for (int i = 0; i < count; i++) {
                    System.out.printf("%d ", array[i]);
                }
            } catch (Exception e) {
                System.out.println("input-output error");
            }

        }
    }

    private static void test(TestingFramework tf, int border) {
        for (int i = 0; i < 3; i++) {
            long start = System.nanoTime();
            tf.test(border);
            long end = System.nanoTime();
            System.out.println("Test " + i + " has finished in " + (end - start) / 10e6);
        }
        tf.printTimeResults();
    }
}
