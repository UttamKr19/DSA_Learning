package pep1.arrays;

public class ArraysQuestions {

	static void display(int a[]) {
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	public static void display(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int[] sum(int arr[], int brr[]) {
		int n = arr.length;
		if (brr.length > n)
			n = brr.length;

		int ans[] = new int[n + 1];

		int i = arr.length - 1;
		int j = brr.length - 1;
		int k = ans.length - 1;
		int carry = 0;

		while (k >= 0) {

			int d1 = i >= 0 ? arr[i] : 0;
			int d2 = j >= 0 ? brr[j] : 0;

			int d = d1 + d2 + carry;
			ans[k] = d % 10;
			carry = d / 10;

			i--;
			j--;
			k--;
		}

		return ans;

	}

	static int[] diff(int arr[], int brr[]) {
		int n = arr.length;
		if (brr.length > n)
			n = brr.length;

		int ans[] = new int[n];

		int i = arr.length - 1;
		int j = brr.length - 1;
		int k = ans.length - 1;
		int carry = 0;
		int d = 0;

		while (k >= 0) {

			int d1 = i >= 0 ? arr[i] : 0;
			int d2 = j >= 0 ? brr[j] : 0;

			d = d1 - d2 + carry;

			if (d < 0) {
				d = d + 10;
				carry = -1;
			} else {
				carry = 0;
			}

			ans[k] = d;

			i--;
			j--;
			k--;
		}

		return ans;
	}

	static void reverse(int[] arr, int l, int h) {
		for (int i = l, j = h; i < j; i++, j--) {
			int t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;
		}
	}

	static void rotate(int[] a, int k) {

		k = k % a.length;
		if (k < 0) {
			k = a.length + k;
		}
		int n = a.length;

		reverse(a, 0, n - k - 1);
		reverse(a, n - k, a.length - 1);
		reverse(a, 0, a.length - 1);

	}

	static void inverse(int[] arr) {
		int brr[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			brr[arr[i]] = i;
		}

		for (int i : brr) {
			System.out.print(i + " ");
		}
	}

	static void subarray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				for (int k = i; k <= j; k++) {
					System.out.print(arr[k] + " ");
				}
				System.out.println();
			}
		}
	}

	static void subset(int[] arr) {
		int limit = (int) Math.pow(2, arr.length);

		for (int i = 0; i < limit; i++) {

			int temp = i;
			String set = "";

			for (int j = arr.length - 1; j >= 0; j--) {
				int r = temp % 2;
				temp = temp / 2;
				if (r == 1) {
					set = arr[j] + set;
				}
			}
			System.out.println(set);
		}
	}

	static void matrixMultiplication(int a[][], int b[][]) {

		int r1 = a[0].length;
		int c2 = b.length;

		if (r1 != c2) {
			System.out.println("invalid");
			return;
		}

		int prd[][] = new int[r1][c2];

		for (int i = 0; i < prd.length; i++) {
			for (int j = 0; j < prd[i].length; j++) {
				for (int k = 0; k < r1; k++) {
					prd[i][j] += a[i][k] + b[k][j];
				}
			}
		}
	}

	static void waveTraversal(int[][] arr) {

		int i = 0;
		int j = 0;

		while (j < arr[0].length) {
			while (i < arr.length && i >= 0) {
				System.out.println(arr[i][j]);

				if (j % 2 == 0) {
					i++;
				} else {
					i--;
				}

				if (j % 2 == 0 && i == arr.length) {
					i--;
					break;
				} else if (j % 2 != 0 && i < 0) {
					i++;
					break;
				}
			}
			j++;
		}

		// solution by sir

//		for(j=0;j<arr[0].length;j++) {
//			if(j%2==0) {
//				for(i=0;i<arr.length;i++) {
//					System.out.println(arr[i][j]);
//				}
//			}
//			else {
//				for(i=arr.length-1;i>=0;i--) {
//					System.out.println(arr[i][j]);
//				}
//			}
//		}
	}

	static void printVertical(int arr[][], int col, int lo, int hi, boolean reverse) {
		if (reverse) {
			for (int i = hi; i >= lo; i--) {
				System.out.println(arr[i][col]);
			}
		} else {
			for (int i = lo; i <= hi; i++) {
				System.out.println(arr[i][col]);
			}
		}

	}

	static void printHorizontal(int arr[][], int row, int lo, int hi, boolean reverse) {
		if (reverse) {
			for (int i = hi; i >= lo; i--) {
				System.out.println(arr[row][i]);
			}
		} else {
			for (int i = lo; i <= hi; i++) {
				System.out.println(arr[row][i]);
			}
		}
	}

	static void spiralTraversal(int[][] arr) {
		int l = 0;
		int r = arr[0].length - 1;
		int u = 0;
		int d = arr.length - 1;

		while (l <= r && u <= d) {
			printVertical(arr, l, u, d, false);
			l++;
			if (l > r)
				break;
			printHorizontal(arr, d, l, r, false);
			d--;
			if (u > d)
				break;
			printVertical(arr, r, u, d, true);
			r--;
			if (l > r)
				break;
			printHorizontal(arr, u, l, r, true);
			u++;
			if (u > d)
				break;
		}

	}

	static void matrixSortedSearch(int[][] arr, int k) {

		int r = arr[0].length - 1;

		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (k == arr[mid][0]) {
				System.out.println(mid);
				System.out.println(0);
				return;
			}
			if (k == arr[mid][r]) {
				System.out.println(mid);
				System.out.println(r);
				return;
			}

			if (k > arr[mid][0] && k < arr[mid][r]) {
				for (int i = 0; i < r; i++) {
					if (arr[mid][i] == k) {
						System.out.println(mid);
						System.out.println(i);
						return;
					}
				}
			}
			if (k > arr[mid][0]) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}

		}
		System.out.println("Not Found");
	}

	static void rotateBy90(int[][] arr) {
		// transpose then reverse

		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int temp = arr[j][i];
				arr[j][i] = arr[i][j];
				arr[i][j] = temp;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			reverse(arr[i], 0, arr[0].length - 1);
		}
	}

	static void endPointOfMatrix(int[][] arr) {
		int i = 0;
		int j = 0;
		int dir = 0;
		while (i < arr.length && j < arr[0].length) {
			dir = (dir + arr[i][j]) % 4;

			if (dir == 0)
				j++;
			else if (dir == 1)
				i++;
			else if (dir == 2) {
				j--;
			} else if (dir == 3) {
				i--;
			}

			if (i < 0) {
				i++;
				break;
			} else if (j < 0) {
				j++;
				break;
			} else if (i == arr.length) {
				i--;
				break;
			} else if (i == arr[0].length) {
				j--;
				break;
			}

		}
		System.out.println(i);
		System.out.println(j);
	}

	static void diagonalTraversal(int[][] arr) {
//		for(int i=0;i<arr[0].length;i++) {
//			
//			int j=0;
//			int k=i;
//			while(j<arr.length && k<arr[0].length) {
//				System.out.println(arr[j][k]);
//				k++;
//				j++;
//			}
//			
//		}
//		
		for (int g = 0; g < arr.length; g++) {
			for (int i = 0, j = g; j < arr.length; j++, i++) {
				System.out.println(arr[i][j]);

			}
		}
	}

	static void saddlePoint(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minI = 0;
			for (int j = 1; j < arr[i].length; j++) {
				if (arr[i][j] < arr[i][minI]) {
					minI = j;
				}
			}

			int maxI = 0;
			for (int j = 1; j < arr.length; j++) {
				if (arr[j][minI] > arr[maxI][minI]) {
					maxI = j;
				}
			}

			if (maxI == i) {
				System.out.println(arr[i][minI]);
				return;
			}

		}
		System.out.println("Invalid input");
	}

	static void swap(int[][] arr, int i, int j, int k, int l) {
		int t = arr[i][j];
		arr[i][j] = arr[k][l];
		arr[k][l] = t;
	}

	static void shellRotate(int[][] arr, int s, int k) {

		int n = arr.length;
		int m = arr[0].length;
		int size = 2 * (n - 2 * (s - 1)) + 2 * (m - 2 * (s - 1)) - 4;
		int a[] = new int[size];

		int i = 0;

		int lo = s - 1;
		int hi = m - s;
		int row = s - 1;
		int col = s - 1;

		for (int j = lo; j <= hi; j++) {
			a[i++] = arr[row][j];
		}

		col = m - s;
		lo = lo + 1;
		hi = n - s;
		for (int j = lo; j <= hi; j++) {
			a[i++] = arr[j][col];
		}

		row = n - s;
		lo = s - 1;
		hi = m - s - 1;
		for (int j = hi; j >= lo; j--) {
			a[i++] = arr[row][j];
		}

		col = s - 1;
		lo = s;
		hi = n - s - 1;
		for (int j = hi; j >= lo; j--) {
			a[i++] = arr[j][col];
		}

		rotate(a, k * -1);

		i = 0;

		lo = s - 1;
		hi = m - s;
		row = s - 1;
		col = s - 1;

		for (int j = lo; j <= hi; j++) {
			arr[row][j] = a[i++];
		}

		col = m - s;
		lo = lo + 1;
		hi = n - s;
		for (int j = lo; j <= hi; j++) {
			arr[j][col] = a[i++];
		}

		row = n - s;
		lo = s - 1;
		hi = m - s - 1;
		for (int j = hi; j >= lo; j--) {
			arr[row][j] = a[i++];
		}

		col = s - 1;
		lo = s;
		hi = n - s - 1;
		for (int j = hi; j >= lo; j--) {
			arr[j][col] = a[i++];
		}

	}


	public static void main(String[] args) {
		int[] a = { 1, 2, 3 };
		int[] b = { 1, 0 };

		int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 }, { 13, 14, 15 } };
		int[][] crr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] brr = { { 11, 12, 13, 14 }, { 25, 26, 27, 28 }, { 39, 30, 31, 32 }, { 43, 44, 45, 46 } };

		int[][] drr = { { 11, 12, 13, 14, 15, 16, 17 }, { 21, 25, 26, 36, 46, 45, 27 }, { 31, 24, 33, 34, 35, 44, 37 },
				{ 41, 23, 22, 32, 42, 43, 47 }, { 51, 52, 53, 54, 55, 56, 57 } };

		display(drr);
		System.out.println();

		int s = 2;
		int k = 3;

		System.out.println();
		shellRotate(drr, s, k);
		System.out.println("\n");
		display(drr);
	}
}
