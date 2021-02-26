package dip107;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

interface SortingAlgorithm {
    //order:
    // 1 - ascending order
    // -1 or any other number - descending order
    void sort(int[] arr, int order);
}

public class TestingFramework {

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
