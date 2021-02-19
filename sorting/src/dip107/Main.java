package dip107;


public class Main {

    public static void main(String[] args) {
        Test[] tests = {new QuickSort()};
        TestingFramework tf = new TestingFramework(tests, 100);

        tf.test();
        tf.printTimeResults();

    }
}
