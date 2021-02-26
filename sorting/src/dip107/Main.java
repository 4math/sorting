package dip107;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SortingAlgorithm[] algorithms = {new QuickSort(), new CountingSort(), new ShakerSort()};
        TestingFramework tf = new TestingFramework(algorithms, 100);

        for (int i = 0; i < 3; i++) {
            tf.test();
        }

        tf.printTimeResults();
        tf.printTimeResultsCSV();

        boolean fFullTest = false;
        boolean fOutputMedian = false;

        for (String arg : args) {
            if (arg.compareTo("-fOutputMedian") == 0) {
                fOutputMedian = true;
            }

            if (arg.compareTo("-fFullTest") == 0) {
                fFullTest = true;
            }
        }

        if(fFullTest) {

        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter array element count: ");
            int count = scanner.nextInt();

            int[] array = new int[count];
            System.out.println("Enter array elements: ");
            for(int i = 0 ; i < count; i++) {
                array[i] = scanner.nextInt();
            }

            System.out.println("Choose the sorting method: ");
            System.out.println("1: Hoara method");
            System.out.println("2: Shaker method");
            System.out.println("3: Counting method");
            System.out.println("4: Modified counting method");

            int choice = scanner.nextInt();
            while (choice < 1 || choice > 3) {
                System.out.println("Choose the sorting method: ");
                choice = scanner.nextInt();
            }

            switch (choice) {
                case 1:
                    break;
                case 2:
                    ShakerSort.sort(array, 0, array.length, 1);
                    break;
                case 3:
//                    CountingSort.sort(array, 1);
                    break;
                case 4:
                    CountingSort.sortModified(array, 1);
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
