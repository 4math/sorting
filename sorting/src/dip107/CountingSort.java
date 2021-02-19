
public class CountingSort {
	
	public static void sort (int[] a) {
		//long t0 = System.nanoTime();
		int i, j, k, n = a.length;
		int b[] = new int[n];
		int equal[] = new int[n];
		int less[] = new int[n];
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (a[i] == a[j]) {
					equal[i] = equal[i]+1;
				}
				else if (a[i] > a[j]) {
					less[i] = less[i]+1;
				}
			}
		}
		
		for (i = 0; i < n; i++) {
			k = less[i];
			for (j = 0; j < equal[i]; j++) {
				b[k+j] = a[i];
			}
		}
		
		for (i = 0; i < n; i++) {
			a[i] = b[i];
		}
		
		//long t = System.nanoTime();
		//return t - t0;
	}
}
