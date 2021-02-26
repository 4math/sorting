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

        if(fFullTest) {
            SortingAlgorithm[] algorithms = {new QuickSort(), new CountingSort(), new ShakerSort()};
            TestingFramework tf = new TestingFramework(algorithms, 100);

            for (int i = 0; i < 3; i++) {
                long start = System.nanoTime();
                tf.test(100000);
                long end = System.nanoTime();
                System.out.println("Test " + i + " has finished in " + (end - start));
            }

            tf.printTimeResults();
            tf.writeTimeResultsToCSV("TimeResults.csv");
        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter array element count: ");
            int count = scanner.nextInt();

            int[] array = new int[count];
            System.out.println("Enter array elements: ");
            for(int i = 0 ; i < count; i++) {
                array[i] = scanner.nextInt();
            }

            System.out.println("Choose the sorting method: ");
            System.out.println("1: Quicksort method");
            System.out.println("2: Shaker sort method");
            System.out.println("3: Counting sort method");
            System.out.println("4: Modified counting method");

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
//                    CountingSort.sort(array, 1);
                    CountingSort.sortModified(array, 1);
                    break;
                case 4:
                    break;
                default:
                    return;
            }

            for (int i = 0; i < count; i++) {
                System.out.printf("%d ", array[i]);
            }
        }

    }
}
