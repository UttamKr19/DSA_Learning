package pep1.arraylist;

import java.util.ArrayList;

public class ArraylistQuestions {
	static boolean isPrime(int n) {
		if (n == 2)
			return true;
		int sq = (int) Math.sqrt(n);
		for (int i = 2; i <= sq; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	static ArrayList<Integer> arraylistPrimeRemove(ArrayList<Integer> arr) {
		for (int i = arr.size() - 1; i >= 0; i--) {
			if (isPrime(arr.get(i))) {
				arr.remove(i);
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(12);
		arr.add(32);
		arr.add(3);
		arr.add(7);
		arr.add(13);
		arr.add(21);
		System.out.println(arr);
		arraylistPrimeRemove(arr);
		System.out.println(arr);
	}
}
