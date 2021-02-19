package dip107;

import java.util.Random;

interface Test {
    void test(int[] arr, int order);
}

public class TestingFramework {

    //    public final int seed = 5614592;
    public final int[] sizes = {
            10,
            100,
            1000,
//            10000,
//            100000,
//            1000000,
    };
    public final int[][][][] minMaxTable;
    public final int[][][][] descendingTable;
    public final int[][][][] almostSortedTable;
    public final int iterationCount = 5;
    public final int[] seeds;
    public double[][] timeResults;
    public final Test[] sortingAlgorithms;

    TestingFramework(Test[] sortingAlgorithms) {

        this.sortingAlgorithms = sortingAlgorithms;

        // It is possible to control the generator
        Random random = new Random(100);

        seeds = new int[iterationCount];
        for (int i = 0; i < seeds.length; i++) {
            seeds[i] = random.nextInt();
            System.out.printf("Seed #%d: %d%n", i, seeds[i]);
        }

        minMaxTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];
        descendingTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];
        almostSortedTable = new int[sortingAlgorithms.length][sizes.length][iterationCount][];

        timeResults = new double[sizes.length][iterationCount];
    }

    public void createMinMaxTable() {
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

    public void createDescendingTable() {
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


    public void createAlmostSortedTable() {
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

                    System.out.println("Change count " + changeCount);
                    for (int i = 0; i < changeCount; i++) {
                        int number = random.nextInt();
                        int index = random.nextInt(almostSortedTable[alg][row][col].length);
                        almostSortedTable[alg][row][col][index] = number;
                    }

                }
            }
        }

    }

    public void printTable() {
//        for (int row = 0; row < sizes.length; row++) {
//
//            for (int col = 0; col < iterationCount; col++) {
//
//                for (int a: minMaxTable[row][col]) {
//                    System.out.printf("%2d ", a);
//                }
//                System.out.print("\t\t");
//            }
//            System.out.println();
//        }
    }



}
