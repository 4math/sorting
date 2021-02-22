//tut 2 vida sortirovki:
//1) sort
//2) modifiedSort
package dip107;
public class CountingSort {
	public static void sort (int[] a, int direction) {
		int i, j, k, n = a.length;
		int b[] = new int[n];
		int equal[] = new int[n];
		int less[] = new int[n];		
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (a[i] == a[j]) {
					equal[i]++;
				}
				else if (a[i] > a[j]) {
					less[i]++;
				}
			}
		}
		for (i = 0; i < n; i++) {
			k = less[i];
			for (j = 0; j < equal[i]; j++) {
				b[k+j] = a[i];
			}
		}
		if (direction == 1) {
			for (i = 0; i < n; i++) {
				a[i] = b[i];
			}
		}
		else {
			for (i = 0; i < n; i++) {
				a[n-i-1] = b[i];
			}
		}
	}
	public static void modifiedSort(int[] a, int direction) {
		int m = Integer.MAX_VALUE, n = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (m > a[i]) {
				m = a[i];
			}
			if (n < a[i]) {
				n = a[i];
			}
		}
		int[] extra = new int[n-m+1];
		for (int i = 0; i < a.length; i++) {
			extra[a[i] - m]++;
		}
		if (direction == 1) {
			int q = 0;
			for (int i = 0; i < extra.length; i++) {
				for (int j = 0; j < extra[i]; j++) {
					a[q] = m + i;
					q++;
				}
			}
		}
		else {
			int q = a.length - 1;
			for (int i = 0; i < extra.length; i++) {
				for (int j = 0; j < extra[i]; j++) {
					a[q] = m + i;
					q--;
				}
			}
		}
	}
}
