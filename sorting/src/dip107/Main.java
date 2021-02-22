package dip107;


public class Main {

    public static void main(String[] args) {
        boolean fFullTest = false;
        boolean fTestShaker = false;
        boolean fTestHoars = false;
        boolean fTestCounting = false;

        for (String arg : args) {
            if (arg.compareTo("-fTestShaker") == 0) {
                fTestShaker = true;
            }

            if (arg.compareTo("-fTestHoars") == 0) {
                fTestHoars = true;
            }

            if (arg.compareTo("-fTestCounting") == 0) {
                fTestCounting = true;
            }

            if (arg.compareTo("-fFullTest") == 0) {
                fFullTest = true;
            }
        }

        boolean testing = fFullTest || fTestShaker || fTestHoars || fTestCounting;
        if(testing) {
            if(fTestShaker || fFullTest) {

            }

            if(fTestHoars || fFullTest) {

            }

            if(fTestCounting || fFullTest) {

            }
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

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    ShakerSort.sort(array);
                    break;
                case 3:
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
