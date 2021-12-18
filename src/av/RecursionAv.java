package av;

import java.util.Stack;

public class RecursionAv {
	static void insertInReversedStack(int val, Stack<Integer> st) {
		if (st.empty()) {
			st.push(val);
			return;
		}
		int t = st.pop();
		insertInReversedStack(val, st);
		st.push(t);

	}

	static void reverseStack(Stack<Integer> st) {
		if (st.empty() || st.size() == 1) {
			return;
		}
		int top = st.pop();
		reverseStack(st);
		insertInReversedStack(top, st);
	}

	// 1 2 6 9, 3
	static void insertSortedInStack(int val, Stack<Integer> st) {
		if (st.empty() || val >= st.peek()) {
			st.push(val);
			return;
		}

		int top = st.pop();
		insertSortedInStack(val, st);
		st.push(top);
	}

	static void sortStack(Stack<Integer> st) {
		if (st.size() <= 1) {
			return;
		}

		int top = st.pop();
		sortStack(st);
		insertSortedInStack(top, st);

	}

	static String kthSymbolInGrammar(int n) {
		if (n == 1) {
			return "0";
		}

		String ab = kthSymbolInGrammar(n - 1);
		String ans = "";
		for (int i = 0; i < ab.length(); i++) {
			if (ab.charAt(i) == '0') {
				ans += "01";
			} else {
				ans += "10";
			}
		}

		return ans;
	}

	static int kthSymbolInGrammarOriginal(int n, int k) {
		String grammar = kthSymbolInGrammar(n);
		System.out.println(grammar);
		return grammar.charAt(k - 1) - '0';
	}

	static int kthSymbolInGrammarOptimal(int n, int k) {
		if (k > Math.pow(2, (n - 1)))
			return -1;

		if (n == 1 || k == 1) {
			return 0;
		}

		int mid = (int) Math.pow(2, n - 1) / 2;
		if (k <= mid) {
			return kthSymbolInGrammarOptimal(n - 1, k);
		} else {
			return kthSymbolInGrammarOptimal(n - 1, k - mid) ^ 1;
		}
	}

	public static void main(String[] args) {
//		Stack<Integer> st = new Stack<>();
//		st.push(552);
//		st.push(35);
//		st.push(12);
//		st.push(45);
//		System.out.println(st);
//
//		sortStack(st);
//		System.out.println(st);
//		
//		reverseStack(st);
//		System.out.println(st);

		int n = 5;
		int k = 20;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (kthSymbolInGrammarOptimal(i, j) == -1)
					continue;
//				System.out.println(i+" "+j);
				System.out.print(kthSymbolInGrammarOptimal(i, j) + " ");
			}
			System.out.println();
		}

	}
}
