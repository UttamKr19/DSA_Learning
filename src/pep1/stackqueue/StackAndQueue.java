package pep1.stackqueue;

import java.util.Arrays;
import java.util.Stack;

public class StackAndQueue {

	static void display(int arr[]) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	static char getReverseBracket(char ch) {
		char rev = ' ';
		if (ch == ')') {
			rev = '(';
		} else if (ch == ']') {
			rev = '[';
		} else if (ch == '}') {
			rev = '{';
		} else if (ch == '(') {
			rev = ')';
		} else if (ch == '[') {
			rev = ']';
		} else if (ch == '{') {
			rev = '}';
		}
		return rev;
	}

	static boolean balancedBrackets(String str) {
		Stack<Character> st = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
//			System.out.println(st+"\n"+ch);
//			System.out.println("----------------------");
			if (ch == ')' || ch == ']' || ch == '}') {
				if (st.empty()) {
					return false;
				}

				if (st.peek() != getReverseBracket(ch)) {
					return false;
				} else {
					st.pop();
				}

			} else if (ch == '(' || ch == '[' || ch == '{') {
				st.push(ch);
			}
		}
		if (st.empty()) {
			return true;
		} else {
			return false;
		}
	}

	static boolean duplicateBracket(String ex) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < ex.length(); i++) {
			char ch = ex.charAt(i);
			if (ch == ')') {
				if (stack.peek() == '(')
					return true;
				else {
					while (stack.peek() != '(') {
						stack.pop();
					}
					stack.pop();
				}
			} else {
				stack.push(ch);
			}
		}

		return false;
	}

	static int[] nextGreaterElementOnRight(int[] arr) {
		// from right to left
		// pop(-) ans push(+)

		Stack<Integer> st = new Stack<>();
		int[] ans = new int[arr.length];

		for (int i = arr.length - 1; i >= 0; i--) {
			// pop the smaller ones
			while (!st.empty() && st.peek() <= arr[i]) {
				st.pop();
			}

			// put answer
			if (st.empty())
				ans[i] = -1;
			else
				ans[i] = st.peek();

			// push current element
			st.push(arr[i]);
		}
		return ans;
	}

	static int[] nextGreaterElementOnRight_Alternate(int[] arr) {
		// solve

		Stack<Integer> st = new Stack<>();
		int[] ans = new int[arr.length];
		Arrays.fill(arr, -1);
		st.push(0);
		for (int i = 1; i < arr.length; i++) {
			while (!st.empty() && arr[i] > arr[st.peek()]) {
				int pos = st.peek();
				ans[pos] = arr[i];
				st.pop();
			}

			st.push(i);
		}
		return ans;
	}

	static int[] nextGreaterElementOnLeft(int[] arr) {
		// from left to right
		// pop(-) ans push(+)
		int[] nge = new int[arr.length];

		Stack<Integer> st = new Stack<>();
		nge[0] = -1;
		st.push(arr[0]);
		for (int i = 1; i < arr.length; i++) {
//			System.out.println(st);
			while (st.size() > 0 && st.peek() <= arr[i]) {
				st.pop();
			}
			if (st.empty()) {
				nge[i] = -1;
			} else {
				nge[i] = st.peek();
			}
			st.push(arr[i]);
		}

		return nge;
	}

	static int[] nextSmallerElementOnLeft(int[] arr) {
		// from right to left
		// pop(-) ans push(+)

		int[] ans = new int[arr.length];
		Arrays.fill(ans, -1);
		Stack<Integer> st = new Stack<>();

		return ans;
	}

	static int[] nextSmallerElementOnRight(int[] arr) {

		// from left to right
		// pop(-) ans pusg(+)

		int[] nse = new int[arr.length];
		Stack<Integer> st = new Stack<>();
		st.push(arr[arr.length - 1]);
		nse[arr.length - 1] = -1;

		for (int i = arr.length - 2; i >= 0; i--) {
			while (st.size() > 0 && st.peek() > arr[i]) {
				st.pop();
			}

			if (st.empty()) {
				nse[i] = -1;
			} else {
				nse[i] = st.peek();
			}

			st.push(arr[i]);

		}

		return nse;
	}

	static int[] stockSpan(int[] arr) {
		// similar to nseor but with index
		// counter approach failing test cases
		int[] ans = new int[arr.length];
		Stack<Integer> st = new Stack<>();
		st.push(0);
		ans[0] = 1;
		for (int i = 1; i < arr.length; i++) {
			while (st.size() > 0 && arr[st.peek()] <= arr[i]) {
				st.pop();
			}
			if (st.empty()) {
				ans[i] = i + 1;
			} else {
				ans[i] = i - st.peek();
			}
			st.push(i);

		}
		return ans;
	}

	static int[] nextSmallerOnRightIndex(int[] arr) {
		// helper for histogram problem

		int nger[] = new int[arr.length];
		Stack<Integer> st = new Stack<>();
//		st.push(arr.length-1);
//		nger[arr.length-1]=-1;
		for (int i = arr.length - 1; i >= 0; i--) {

			while (st.size() > 0 && arr[st.peek()] >= arr[i]) {
				st.pop();
			}
			if (st.empty()) {
				nger[i] = arr.length;
			} else {
				nger[i] = st.peek();
			}
			st.push(i);

		}
		return nger;

	}

	static int[] nextSmallerOnLeftIndex(int[] arr) {
		// helper for histogram problem

		int ngel[] = new int[arr.length];
		Stack<Integer> st = new Stack<>();

		for (int i = 0; i < arr.length; i++) {

			while (st.size() > 0 && arr[st.peek()] >= arr[i]) {
				st.pop();
			}
			if (st.empty()) {
				ngel[i] = -1;
			} else {
				ngel[i] = st.peek();
			}
			st.push(i);
		}
		return ngel;

	}

	static int histogramLargestArea(int[] a) {
		int[] l = nextSmallerOnLeftIndex(a);
		int[] r = nextSmallerOnRightIndex(a);

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			max = Math.max(a[i] * (r[i] - l[i] - 1), max);
		}
		return max;
	}

	static void slidingWindowMaximum(int[] arr, int k) {
		// needs revision
		int[] ngi = nextGreaterElementOnRightIndex(arr);
		for (int i = 0; i <= arr.length - k; i++) {
			int j = i;

			while (ngi[j] < (k + i)) {
				j = ngi[j];
			}
			System.out.print(arr[j] + " ");
		}
	}

	static int[] nextGreaterElementOnRightIndex(int[] arr) {
		// from right to left
		// pop(-) ans push(+)

		Stack<Integer> st = new Stack<>();
		int[] ans = new int[arr.length];

		for (int i = arr.length - 1; i >= 0; i--) {
			// pop the smaller ones
			while (!st.empty() && arr[st.peek()] <= arr[i]) {
				st.pop();
			}

			// put answer
			if (st.empty())
				ans[i] = arr.length;
			else
				ans[i] = st.peek();

			// push current element
			st.push(i);
		}
		return ans;
	}

	static int precedence(char ch) {
		if (ch == '+' || ch == '-') {
			return 1;
		}

		return 2;
	}

	static int infixEvaluation(String str) {
		Stack<Integer> opnd = new Stack<Integer>();
		Stack<Character> optr = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {
				opnd.push(ch - '0');
			} else if (ch == '(') {
				optr.push(ch);
			} else if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
				while (optr.size() > 0 && optr.peek() != '(' && precedence(ch) <= precedence(optr.peek())) {
					int v2 = opnd.pop();
					int v1 = opnd.pop();
					char operator = optr.pop();
					int val = operation(v1, v2, operator);
					opnd.push(val);
				}
				optr.push(ch);
			} else if (ch == ')') {
				while (optr.peek() != '(') {
					int v2 = opnd.pop();
					int v1 = opnd.pop();
					char operator = optr.pop();
					int val = operation(v1, v2, operator);
					opnd.push(val);
				}
				optr.pop();
			}
		}
		while (optr.size() > 0) {
			int v2 = opnd.pop();
			int v1 = opnd.pop();
			char operator = optr.pop();
			int val = operation(v1, v2, operator);
			opnd.push(val);
		}
		return opnd.peek();

	}

	private static int operation(int v1, int v2, char ch) {
		if (ch == '+') {
			return v1 + v2;
		} else if (ch == '-') {
			return v1 - v2;
		} else if (ch == '*') {
			return v1 * v2;
		} else if (ch == '/') {
			return v1 / v2;
		}
		return 0;
	}

	static void processPrePostStacks(Stack<String> pre, Stack<String> post, Stack<Character> optr) {
		String v2 = pre.pop();
		String v1 = pre.pop();

		String n2 = post.pop();
		String n1 = post.pop();

		char operator = optr.pop();

		post.push(n1 + n2 + operator);
		pre.push(operator + v1 + v2);
	}

	static String infixConversions(String str) {
		Stack<String> pre = new Stack<>();
		Stack<String> post = new Stack<>();
		Stack<Character> optr = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (ch == '(') {
				optr.push(ch);

			} else if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
				while (optr.size() > 0 && optr.peek() != '(' && precedence(ch) <= precedence(optr.peek())) {
					processPrePostStacks(pre, post, optr);
				}
				optr.push(ch);

			} else if (ch == ')') {
				while (optr.peek() != '(') {
					processPrePostStacks(pre, post, optr);
				}
				optr.pop();

			} else {
				// alphabets or digits
				pre.push(ch + "");
				post.push(ch + "");
			}
		}
		while (optr.size() > 0) {
			processPrePostStacks(pre, post, optr);
		}
		return pre.peek() + "," + post.peek();

	}

	static void processPreInValStacks(Stack<String> pre, Stack<String> in, Stack<Integer> val, char operator) {
		String preV2 = pre.pop();
		String preV1 = pre.pop();
		pre.push(operator + preV1 + preV2);

		String inV2 = in.pop();
		String inV1 = in.pop();
		in.push("(" + inV1 + operator + inV2 + ")");

		int valV2 = val.pop();
		int valV1 = val.pop();
		int v = operation(valV1, valV2, operator);
		val.push(v);

	}

	static void postfixEvaluationAndConversion(String str) {
		Stack<String> pre = new Stack<>();
		Stack<String> in = new Stack<>();
		Stack<Integer> val = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (Character.isDigit(ch)) {
				val.push(ch - '0');
				in.push("" + ch);
				pre.push("" + ch);

			} else if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
				processPreInValStacks(pre, in, val, ch);
			}
		}

		System.out.println(val.peek());
		System.out.println(in.peek());
		System.out.println(pre.peek());
	}

	static boolean doesAknowB(int a, int b, int[][] arr) {
		if (arr[a][b] == 1) {
			return true;
		}
		return false;
	}

	public static void findCelebrity(int[][] arr) {
		// if a celebrity is there print it's index (not position), if there is not then
		// print "none"

		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < arr.length; i++)
			st.push(i);

		while (st.size() > 1) {

			int a = st.pop();
			int b = st.pop();
			if (!doesAknowB(a, b, arr)) {
				st.push(a);
			} else if (!doesAknowB(b, a, arr)) {
				st.push(b);
			}
		}
		if (st.size() == 1) {
			int p = st.peek();

			for (int i = 0; i < arr.length; i++) {
				if (i != p) {
					if (!doesAknowB(i, p, arr)) {
						System.out.println("none");
						return;
					}
				}
			}

			for (int i = 0; i < arr.length; i++) {
				if (doesAknowB(p, i, arr)) {
					System.out.println("none");
					return;
				}
			}

			System.out.println(p);
		}

	}

	public static void main(String[] args) {
		String st[] = { "[(a + b) + {(c + d) * (e / f)}]", "[(a + b) + {(c + d) * (e / f)]}",
				"[(a + b) + {(c + d) * (e / f)}", "([(a + b) + {(c + d) * (e / f)}]", "[]()" };

//		System.out.println(infixConversions("2+6*4/8-3"));
//
//		System.out.println(infixConversions("a*(b-c+d)/e"));

		postfixEvaluationAndConversion("264*8/+3-");
	}
}
