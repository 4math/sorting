package big;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Comparator;
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

                System.out.print("count: ");
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


class QuickSort implements SortingAlgorithm {

    public static Comparator<Integer> ascending = Comparator.naturalOrder();

    public static Comparator<Integer> descending = (o1, o2) -> -ascending.compare(o1, o2);

    public static void sort(int[] arr, int low, int high, int order) {
        if (low < high) {
            Comparator<Integer> comp = order == 1 ? ascending : descending;
            int part = partition(arr, low, high, comp);
            sort(arr, low, part, order);
            sort(arr, part + 1, high, order);
        }
    }

    private static int partition(int[] arr, int low, int high, Comparator<Integer> comp) {
        int pivot = arr[(high + low) / 2];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do i++; while (comp.compare(arr[i], pivot) < 0);
            do j--; while (comp.compare(arr[j], pivot) > 0);

            if (i >= j) {
                return j;
            }
            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public void sort(int[] arr, int order) {
        sort(arr, 0, arr.length - 1, order);
    }

}

class CountingSort implements SortingAlgorithm{

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

class ShakerSort implements SortingAlgorithm {

    public static Comparator<Integer> ascending = Comparator.naturalOrder();

    public static Comparator<Integer> descending = (o1, o2) -> -ascending.compare(o1, o2);

    public static void sort(int[] data, int low, int high, int order) {
        int left = low;
        int right = high;

        Comparator<Integer> comp = order == 1 ? ascending : descending;

        while(left < right) {
            for(int i = left; i < right; i++) {
                if (comp.compare(data[i], data[i + 1]) > 0) {
                    swap(data, i, i + 1);
                }
            }
            right--;

            for(int i = right; i > left; i--) {
                if (comp.compare(data[i], data[i - 1]) < 0) {
                    swap(data, i, i - 1);
                }
            }
            left++;
        }
    }

    private static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    @Override
    public void sort(int[] data, int order) {
        sort(data, 0, data.length - 1, order);
    }
}

interface SortingAlgorithm {
    //order:
    // 1 - ascending order
    // -1 or any other number - descending order
    void sort(int[] arr, int order);
}

class TestingFramework {

    private final int[] sizes = {
            10,
            100,
            1000,
            10000,
            30000,
    };

    // first dimension is the amount of sorting algorithms
    // second is the length of sizes
    // third is an iteration count
    // fourth is the size of an array
    private final int[][][][] minMaxTable;
    private final int[][][][] descendingTable;
    private final int[][][][] almostSortedTable;
    // fifth is an array with all upper tables
    private final int[][][][][] table;
    private final int iterationCount = 5;
    private final int[] seeds; // seed amount is equal to iterationCount
    // first dimension - sorting algorithm
    // second - row of a table, equal to
    // third - column of a table, equal to iterationCount
    private final double[][][] timeResults;
    private final SortingAlgorithm[] sortingAlgorithms;

    TestingFramework(SortingAlgorithm[] sortingAlgorithms, int seed) {

        this.sortingAlgorithms = sortingAlgorithms;

        Random random = new Random(seed);

        seeds = new int[iterationCount];
        for (int i = 0; i < seeds.length; i++) {
            seeds[i] = random.nextInt();
//            System.out.printf("Seed #%d: %d%n", i, seeds[i]);
        }

        minMaxTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];
        descendingTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];
        almostSortedTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];
        table = new int[][][][][]{minMaxTable, descendingTable, almostSortedTable};

        // adding 1 to iteration count to write the average value
        timeResults = new double[sortingAlgorithms.length][3 * sizes.length][iterationCount + 1];
    }

    public void test(int border) {
        createMinMaxTable(border);
        createDescendingTable(border);
        createAlmostSortedTable(border);

        int tableOffset;

        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {

            for (int col = 0; col < iterationCount; col++) {

                tableOffset = 0;
                for (int tab = 0; tab < table.length; tab++) {

                    for (int row = 0; row < sizes.length; row++) {
                        long start = System.nanoTime();
                        sortingAlgorithms[alg].sort(table[tab][alg][row][col], 1);
                        long end = System.nanoTime();

                        double timeElapsed = (end - start) / 1000.0; // results are in microseconds
                        timeResults[alg][tableOffset + row][col] = timeElapsed;
                    }

                    tableOffset += sizes.length;
                } //tab
            } //col
        } //alg

        calculateAverageTime();
    }

    public void writeTimeResultsToCSV(String filename) {
        String names = "size, qseed1, qseed2, qseed3, qseed4, qseed5, qavg, size, cseed1, cseed2, cseed3, " +
                "cseed4, cseed5, cavg, size, sseed1, sseed2, sseed3, sseed4, sseed5, savg\n";
        try (PrintWriter writer = new PrintWriter("data/" + filename)) {
            StringBuilder sb = new StringBuilder();
            sb.append(names);
            int n = sizes.length;
            for (int row = 0; row < 3 * n; row++) {
                for (int alg = 0; alg < sortingAlgorithms.length; alg++) {
                    for (int col = 0; col < iterationCount + 2; col++) {
                        if (col == 0) {
                            sb.append(sizes[row % sizes.length]);
                            sb.append(',');
                        } else {
                            sb.append(timeResults[alg][row][col - 1]);
                            sb.append(',');
                        }
                    }
                }
                sb.append('\n');
                if (row % n == n - 1) sb.append('\n');
            }
            writer.write(sb.toString());
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void writeAvgResultsToCSV(String filename) {
        String names = "size, qavg, size, cavg, size, savg\n";
        try (PrintWriter writer = new PrintWriter("data/" + filename)) {
            StringBuilder sb = new StringBuilder();
            sb.append(names);
            int n = sizes.length;
            for (int row = 0; row < 3 * n; row++) {
                for (int alg = 0; alg < sortingAlgorithms.length; alg++) {
                    for (int col = 0; col < 2; col++) {
                        if (col == 0) {
                            sb.append(sizes[row % n]);
                            sb.append(',');
                        } else {
                            sb.append(timeResults[alg][row][iterationCount]);
                            sb.append(',');
                        }
                    }
                }
                sb.append('\n');
                if (row % n == n - 1) sb.append('\n');
            }
            writer.write(sb.toString());
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void printTimeResults() {
        int n = sizes.length;
        for (int row = 0; row < 3 * n; row++) {
            for (int alg = 0; alg < sortingAlgorithms.length; alg++) {
                for (int col = 0; col < iterationCount + 1; col++) {
                    System.out.printf("%10.2f ", timeResults[alg][row][col]);
                }
                System.out.print("\t\t");
            }
            System.out.println();
            if (row % n == n - 1) System.out.println();
        }
    }

    private void calculateAverageTime() {
        int tableOffset;
        double timeSum;

        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {
            tableOffset = 0;
            for (int tab = 0; tab < table.length; tab++) {

                for (int row = 0; row < sizes.length; row++) {
                    timeSum = 0;

                    for (int col = 0; col < iterationCount; col++) {
                        timeSum += timeResults[alg][tableOffset + row][col];
                    }
                    timeResults[alg][tableOffset + row][iterationCount] = timeSum / iterationCount;
                } //row
                tableOffset += sizes.length;
            } //tab
        } //alg
    }

    private void createMinMaxTable(int border) {
        // first of all, iterate through rows (one seed), then through columns, creating an array with specific size
        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {

            for (int col = 0; col < iterationCount; col++) {

                int seed = seeds[col];
                Random random = new Random(seed);

                for (int row = 0; row < sizes.length; row++) {

                    minMaxTable[alg][row][col] = new int[sizes[row]];

                    for (int k = 0; k < sizes[row]; k++) {
                        if (border != 0) {
                            minMaxTable[alg][row][col][k] = random.nextInt(2 * border + 1) - border;
                        } else {
                            minMaxTable[alg][row][col][k] = random.nextInt();
                        }
                    }

                }
            }
        }
    }

    private void createDescendingTable(int border) {
        // first of all, iterate through rows (one seed), then through columns, creating an array with specific size
        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {

            for (int col = 0; col < iterationCount; col++) {

                int seed = seeds[col];
                Random random = new Random(seed);

                for (int row = 0; row < sizes.length; row++) {

                    descendingTable[alg][row][col] = new int[sizes[row]];

                    for (int k = 0; k < sizes[row]; k++) {
                        if (border != 0) {
                            descendingTable[alg][row][col][k] = random.nextInt(2 * border + 1) - border;
                        } else {
                            descendingTable[alg][row][col][k] = random.nextInt();
                        }
                    }

                    QuickSort.sort(descendingTable[alg][row][col], 0,
                            descendingTable[alg][row][col].length - 1, -1);

                }
            }
        }

    }


    private void createAlmostSortedTable(int border) {
        // first of all, iterate through rows (one seed), then through columns, creating an array with specific size
        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {

            for (int col = 0; col < iterationCount; col++) {

                int seed = seeds[col];
                Random random = new Random(seed);

                for (int row = 0; row < sizes.length; row++) {

                    almostSortedTable[alg][row][col] = new int[sizes[row]];

                    for (int k = 0; k < sizes[row]; k++) {
                        if (border != 0) {
                            almostSortedTable[alg][row][col][k] = random.nextInt(2 * border + 1) - border;
                        } else {
                            almostSortedTable[alg][row][col][k] = random.nextInt();
                        }
                    }

                    QuickSort.sort(almostSortedTable[alg][row][col], 0,
                            almostSortedTable[alg][row][col].length - 1, -1);

                    int changeCount = random.nextInt(almostSortedTable[alg][row][col].length / 2) + 2;

                    for (int i = 0; i < changeCount; i++) {
                        int number;
                        if (border != 0) {
                            number = random.nextInt( 2 * border + 1) - border;
                        } else {
                            number = random.nextInt();
                        }
                        int index = random.nextInt(almostSortedTable[alg][row][col].length);
                        almostSortedTable[alg][row][col][index] = number;
                    }

                }
            }
        }

    }

}
