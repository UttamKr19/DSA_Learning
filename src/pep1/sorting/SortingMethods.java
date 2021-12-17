package pep1.sorting;

import java.util.Arrays;
import java.util.Random;

/*
 *	Sorting
 *		Bubble
 *		Insertion
 *		Selection
 *		Quick
 *		Merge
 *		01
 *		012
 *
 *	Quick Select | Nth Smallest Number
 * 
 * 
 */

public class SortingMethods {

	static void display(int arr[]) {
		System.out.println("\n---------------");
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	static void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static int[] getRandomArray(int n) {
		int arr[] = new int[n];
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = r.nextInt(100);
		}
		return arr;
	}

	static int[] getUnsortedArray() {
		return new int[] { 56, 44, 45, 22, 5, 243, 6, 53, 53, 64, 92, 21, 4, 24, 54, 2, 3, 523, 3 };
//		return new int[] { 92, 21, 4, 24, 54, 2, 3, 523, 3 };
	}

	
	
	//------------------------------------------------------------------
	// bubble sort
	// swap adjacent elements
	static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					swap(arr,i,j);
				}
			}
		}
	}

	// selection sort
	// find min and put at front
	static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;

			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}

			swap(arr, i, minIndex);
		}
	}

	// insertion sort
	// like sorting a deck of cards
	static void insertionSort(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			int elem = arr[i];
			int j = i - 1;
			for (; j >= 0 && arr[j] > elem; j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = elem;
		}
	}

	// 5 4 8 2 3 6 1 4 2
	// 12378956
	// 
	static void insertionSort2(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	// partition
	static int partition(int[] arr, int l, int r) {
		int p = l;
		int pivot = arr[l];

		while (true) {
			while (l < arr.length && arr[l] <= pivot) {
				l++;
			}

			while (r > 0 && arr[r] > pivot) {
				r--;
			}

			if (l >= r) {
				swap(arr, p, r);
				break;
			} else {
				swap(arr, l, r);
			}
		}

//		swap(arr, l, r);
		return r;
	}

	// Partition using Lomuto partition scheme
	public static int partition(int[] nums, int left, int right, int pIndex) {
		// pick `pIndex` as a pivot from the array
		int pivot = nums[pIndex];

		// Move pivot to end
		swap(nums, pIndex, right);

		// elements less than the pivot will be pushed to the left of `pIndex`;
		// elements more than the pivot will be pushed to the right of `pIndex`;
		// equal elements can go either way
		pIndex = left;

		// each time we find an element less than or equal to the pivot, `pIndex`
		// is incremented, and that element would be placed before the pivot.
		// swapping to throw smaller elements in left and bigger elements to right of
		// pivot
		for (int i = left; i < right; i++) {
			if (nums[i] <= pivot) {
				swap(nums, i, pIndex);
				pIndex++;
			}
		}

		// move pivot to its final place
		swap(nums, pIndex, right);

		// return `pIndex` (index of the pivot element)
		return pIndex;
	}
	
	public static int partition3(int[] nums, int left, int right, int pIndex) {

		int pivot = nums[pIndex];
		swap(nums, pIndex, right);

		int i=left;
		int j=left;
		while(i<right) {
			if(nums[i]>=pivot) {
				i++;
			}
			else {
				swap(nums,i,j);
				i++;
				j++;
			}
		}
		
		swap(nums, j, right);
		System.out.println();
		System.out.println(i+" "+j);
		return j;
	}

	// quick sort
	static void quickSort(int[] arr, int l, int r) {

		if (l < r) {
			int p = partition(arr, l, r);
			quickSort(arr, l, p - 1);
			quickSort(arr, p + 1, r);
		}

	}

	static void quickSort2(int[] arr, int l, int r) {

		if (l < r) {
			int p = partition(arr, l, r, l);
			quickSort(arr, l, p - 1);
			quickSort(arr, p + 1, r);
		}

	}

	// quick select
	static void findNthSmallest(int[] arr, int n, int l, int r) {
		while (true) {
			int p = partition(arr, l, r);
			if (p == n) {
				System.out.println("\n" + p + " " + arr[p]);
				return;
			}
			if (p > n) {
				r = p - 1;
			}
			if (p < n) {
				l = p + 1;
			}
		}

	}

	// quick select using recursion
	static int findKthSmallest(int[] arr, int n, int l, int r) {
		int p = partition(arr, l, r);
		if (p == n)
			return arr[p];

		if (p > n)
			return findKthSmallest(arr, n, l, p - 1);
		else
			return findKthSmallest(arr, n, p + 1, r);
	}

	static void merge(int arr[], int l, int m, int r) {

		int n1 = m - l + 1;
		int n2 = r - m;

		int left[] = new int[n1];
		int right[] = new int[n2];

		for (int i = 0; i < n1; ++i)
			left[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			right[j] = arr[m + 1 + j];

		int i = 0;
		int j = 0;
		int k = l;

		while (i < n1 && j < n2) {
			if (left[i] < right[j]) {
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}

		while (i < left.length) {
			arr[k++] = left[i++];
		}
		while (j < right.length) {
			arr[k++] = right[j++];
		}
	}

	static void mergeSort(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}

	}

	static void sort01(int[] arr) {
		int i = 0;
		int j = 0;
		
		while(i<arr.length) {
			if(arr[i]==1)
				i++;
			else if(arr[i]==0){
				swap(arr,i,j);
				i++;
				j++;
			}
		}
	}
	
	static void sort012(int[] arr) {
		int i = 0;
		int j = 0;
		int k=arr.length-1;
		
		
		while(i<=k) {
			if(arr[i]==0) {
				swap(arr,i,j);
				i++;
				j++;
			}
			else if(arr[i]==1) {
				i++;
			}
			else if(arr[i]==2) {
				swap(arr,i,k);
				k--;
			}
		}

		

	}

	static void targetSumPair(int[] arr, int target){
	    //write your code here
	    int i=0;
	    int j=arr.length-1;
	    Arrays.sort(arr);

	    while(i<=j){
	        if(arr[i]+arr[j]==target){
	            System.out.println(arr[i]+" "+arr[j]);
	            i++;j--;
	        }
	        else if(arr[i]+arr[j]>target){
	            j--;
	        }
	        else{
	            i++;
	        }
	    }
	}

	public static void main(String[] args) {

		int[] arr = getRandomArray(10);
		display(arr);
		int p=partition3(arr, 0, arr.length-1, 0);
		display(arr);
		
		int[] crr= {1,0,1,2,1,2,1,0,2,0,1,1,0,0,1,0,2,0,0,2,1,0,2,0,2,2};
		display(crr);
		sort012(crr);
		display(crr);
		
		int[] drr= {2,8,3,4,6,7,0,2,34};
		display(drr);
		System.out.println();
		targetSumPair(drr, 10);
		

	}
}
