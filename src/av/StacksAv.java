package av;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class StacksAv {
	static int[] ngl(int arr[]) {
		int[] ans = new int[arr.length];
		if (arr.length == 0) {
			return ans;
		}
		Stack<Integer> st = new Stack<>();

		for (int i = 0; i < arr.length; i++) {
			while (st.size() > 0 && arr[st.peek()] <= arr[i]) {
				st.pop();
			}
			if (st.size() == 0) {
				ans[i] = -1;
			} else {
				ans[i] = st.peek();
			}
			st.push(i);
		}
		return ans;

	}

	// Function to calculate the span of stock's price for all n days.
	public static int[] calculateSpan(int price[], int n) {
		// Your code here

		int[] ngarr = ngl(price);
		int[] ans = new int[price.length];

		for (int i = 0; i < price.length; i++) {
			ans[i] = i - ngarr[i];
		}
		return ans;
	}

	public static void main(String[] args) {

		

	}
}
