package av;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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

	static void uniqueSubsets(String st, String ans, HashSet<String> hs) {
		if (st.length() == 0) {
			hs.add(ans);
			return;
		}

		uniqueSubsets(st.substring(1), ans + st.substring(0, 1), hs);
		uniqueSubsets(st.substring(1), ans, hs);

	}

	static String swap(String str, int i, int j) {
		StringBuilder sb = new StringBuilder(str);
		char a = sb.charAt(i);
		char b = sb.charAt(j);
		sb.setCharAt(i, b);
		sb.setCharAt(j, a);
		return sb.toString();
	}

	static void printPermutation(String st, int l, int r) {
		if (l == r) {
			System.out.println(st);
			return;
		}

		for (int i = l; i <= r; i++) {
			st = swap(st, l, i);
			printPermutation(st, l + 1, r);
			st = swap(st, l, i);
		}
	}

	static void printPermutation2(String st, String ans) {
		if (st.length() == 0) {
			System.out.println(ans);
			return;
		}

		for (int i = 0; i < st.length(); i++) {
			char ch = st.charAt(i); // ith char
			String left = st.substring(0, i); // 0 to i-1 chars
			String right = st.substring(i + 1); // i+1 to n chars
			printPermutation2(left + right, ans + ch); //
		}
	}

	static void permutationWithSpaces(String st, String ans) {
		if (st.length() == 0) {
			System.out.println(ans);
			return;
		}

		char ch = st.charAt(0);
		permutationWithSpaces(st.substring(1), ans + ch);
		// this condition bcz a_b_c chahiye, not _a_b_c
		if (ans.length() > 0)
			permutationWithSpaces(st.substring(1), ans + "_" + ch);
	}

	static void permutationWithCaseChange(String st, String ans) {
		if (st.length() == 0) {
			System.out.println(ans);
			return;
		}

		char ch = st.charAt(0);
		permutationWithCaseChange(st.substring(1), ans + ch);
		permutationWithCaseChange(st.substring(1), ans + Character.toUpperCase(ch));
	}

	static void letterCasePermutation(String st, String ans) {
		if (st.length() == 0) {
			System.out.println(ans);
			return;
		}

		char ch = st.charAt(0);

		if (Character.isDigit(ch)) {
			letterCasePermutation(st.substring(1), ans + ch);
		} else {
			letterCasePermutation(st.substring(1), ans + Character.toLowerCase(ch));
			letterCasePermutation(st.substring(1), ans + Character.toUpperCase(ch));
		}
	}

	static void balancedParanthesis(int o, int c, String ans) {
		if (o == 0 && c == 0) {
			System.out.println(ans);
			hs.add(ans);
			return;
		} else if (o == c) {
			balancedParanthesis(o - 1, c, ans + "(");
		} else if (o == 0 && c > 0) {
			balancedParanthesis(o, c - 1, ans + ")");
		} else {
			balancedParanthesis(o - 1, c, ans + "(");
			balancedParanthesis(o, c - 1, ans + ")");
		}
	}

	static HashSet<String> hs = new HashSet<>();

	static void balancedParanthesisNotOptimal(int n, String ans) {
		if (n == 0) {
//			System.out.println(ans);
			hs.add(ans);
			return;
		}
		balancedParanthesisNotOptimal(n - 1, "(" + ans + ")");
		balancedParanthesisNotOptimal(n - 1, ans + "()");
		balancedParanthesisNotOptimal(n - 1, "()" + ans);

	}

	// print n bit binary no. having more 1's than equal to 0's for any prefix
	static void printNBitBinary(int n, int o, int z, String ans) {
		if (o + z == n) {
			System.out.println(ans);
			return;
		}
		
		if (o == z) {
			printNBitBinary(n, o + 1, z, ans + "1");
		} else {
			printNBitBinary(n, o + 1, z, ans + "1");
			printNBitBinary(n, o, z + 1, ans + "0");
		}
	}
	
	

	public static void main(String[] args) {


	}
}
