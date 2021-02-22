package dip107;


public class Main {

    public static void main(String[] args) {
        SortingAlgorithm[] sortingAlgorithms = {new QuickSort()};
        TestingFramework tf = new TestingFramework(sortingAlgorithms, 100);

        // It is needed to run test several times, because JVM runs slow at first run
        // due to JIT
        // https://stackoverflow.com/questions/20655963/java-benchmarking-why-is-the-second-loop-faster
        for (int i = 0; i < 3; i++) {
            tf.test();
        }
        tf.printTimeResults();

    }
}
