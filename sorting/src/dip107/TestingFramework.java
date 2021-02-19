package dip107;

import java.util.Random;

interface Test {
    void test(int[] arr, int order);
}

public class TestingFramework {

    public final int[] sizes = {
            10,
            100,
            1000,
            10000,
            100000,
            1000000,
    };

    // first is the amount of sorting algorithms
    // second is the length of sizes
    // third is an iteration count
    // fourth is the size of an array
    private final int[][][][] minMaxTable;
    private final int[][][][] descendingTable;
    private final int[][][][] almostSortedTable;
    private final int[][][][][] table;
    private final int iterationCount = 5;
    private final int[] seeds;
    private final double[][][] timeResults;
    private final Test[] sortingAlgorithms;

    TestingFramework(Test[] sortingAlgorithms, int seed) {

        this.sortingAlgorithms = sortingAlgorithms;

        // It is possible to control the generator
        Random random = new Random(seed);

        seeds = new int[iterationCount];
        for (int i = 0; i < seeds.length; i++) {
            seeds[i] = random.nextInt();
            System.out.printf("Seed #%d: %d%n", i, seeds[i]);
        }

        minMaxTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];
        descendingTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];
        almostSortedTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];
        table = new int[][][][][]{minMaxTable, descendingTable, almostSortedTable};

        timeResults = new double[sortingAlgorithms.length][3 * sizes.length][iterationCount];
    }

    public void test() {
        createMinMaxTable();
        createDescendingTable();
        createAlmostSortedTable();

        for (int i = 0; i < iterationCount; i++) {
            sortingAlgorithms[0].test(table[0][0][i][0], 1);
        }

        int tableOffset;

        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {

            for (int col = 0; col < iterationCount; col++) {

                tableOffset = 0;
                for (int tab = 0; tab < table.length; tab++) {

                    for (int row = 0; row < sizes.length; row++) {
                        long start = System.nanoTime();
                        sortingAlgorithms[alg].test(table[tab][alg][row][col], 1);
                        long end = System.nanoTime();

                        double timeElapsed = (end - start) / 1000.0;
                        timeResults[alg][tableOffset + row][col] = timeElapsed;
                    }

                    tableOffset += sizes.length;
                }
            }
        }
    }

    public void printTimeResults() {
        int n = sizes.length;
        for (int row = 0; row < 3 * n; row++) {
            for (int alg = 0; alg < sortingAlgorithms.length; alg++) {
                for (int col = 0; col < iterationCount; col++) {
                    System.out.printf("%10.2f ", timeResults[alg][row][col]);
                }
                System.out.print("\t\t");
            }
            System.out.println();
            if (row % n == n - 1) System.out.println();
        }
    }

    private void createMinMaxTable() {
        // first of all, iterate through rows (one seed), then through columns, creating an array with specific size
        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {

            for (int col = 0; col < iterationCount; col++) {

                int seed = seeds[col];
                Random random = new Random(seed);

                for (int row = 0; row < sizes.length; row++) {

                    minMaxTable[alg][row][col] = new int[sizes[row]];

                    for (int k = 0; k < sizes[row]; k++) {
                        minMaxTable[alg][row][col][k] = random.nextInt();
                    }

                }
            }
        }
    }

    private void createDescendingTable() {
        // first of all, iterate through rows (one seed), then through columns, creating an array with specific size
        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {

            for (int col = 0; col < iterationCount; col++) {

                int seed = seeds[col];
                Random random = new Random(seed);

                for (int row = 0; row < sizes.length; row++) {

                    descendingTable[alg][row][col] = new int[sizes[row]];

                    for (int k = 0; k < sizes[row]; k++) {
                        descendingTable[alg][row][col][k] = random.nextInt();
                    }

                    sortingAlgorithms[alg].test(descendingTable[alg][row][col], -1);

                }
            }
        }

    }


    private void createAlmostSortedTable() {
        // first of all, iterate through rows (one seed), then through columns, creating an array with specific size
        for (int alg = 0; alg < sortingAlgorithms.length; alg++) {

            for (int col = 0; col < iterationCount; col++) {

                int seed = seeds[col];
                Random random = new Random(seed);

                for (int row = 0; row < sizes.length; row++) {

                    almostSortedTable[alg][row][col] = new int[sizes[row]];

                    for (int k = 0; k < sizes[row]; k++) {
                        almostSortedTable[alg][row][col][k] = random.nextInt();
                    }

                    sortingAlgorithms[alg].test(almostSortedTable[alg][row][col], -1);

                    int changeCount = random.nextInt(almostSortedTable[alg][row][col].length / 2) + 2;

                    for (int i = 0; i < changeCount; i++) {
                        int number = random.nextInt();
                        int index = random.nextInt(almostSortedTable[alg][row][col].length);
                        almostSortedTable[alg][row][col][index] = number;
                    }

                }
            }
        }

    }

}
