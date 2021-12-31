package prep1.dp;

import java.util.Arrays;
import java.util.Scanner;

public class DPandGreedy {
	static void display(Integer dp[][]) {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void display2(int dp[][]) {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	// -----------------------------------------------------
	static int climbingStairsRecursion(int n, int N) {
		if (n == N)
			return 1;

		if (n > N)
			return 0;

		return climbingStairsRecursion(n + 1, N) + climbingStairsRecursion(n + 2, N)
				+ climbingStairsRecursion(n + 3, N);
	}

	static int climbingStairsMemoization(int n, int N, int dp[]) {
		if (n == N) {
			dp[n] = 1;
			return 1;
		}

		if (n > N)
			return 0;

		if (dp[n] > 0)
			return dp[n];

		int ans = climbingStairsMemoization(n + 1, N, dp) + climbingStairsMemoization(n + 2, N, dp)
				+ climbingStairsMemoization(n + 3, N, dp);

		dp[n] = ans;
		return ans;
	}

	static int climbingStairsRecursionMemoizationMain(int N) {
		int dp[] = new int[N + 1];
		return climbingStairsMemoization(0, N, dp);
	}

	static int climbingStairsTabulation(int N) {
		int dp[] = new int[N + 1];

		for (int i = 0; i < dp.length; i++) {
			if (i == 0) {
				dp[i] = 1;
			} else if (i == 1) {
				dp[i] = dp[i - 1];
			} else if (i == 2) {
				dp[i] = dp[i - 1] + dp[i - 2];
			} else {
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}
		}

		return dp[dp.length - 1];

	}

	// -----------------------------------------------------------
	static int minCostPathRecursion(int arr[][], int i, int j, int n, int m, int c) {
		if (i == n && j == m) {
			return c + arr[i][j];
		}
		if (i > n || j > m) {
			return Integer.MAX_VALUE;
		}

		int a = minCostPathRecursion(arr, i + 1, j, n, m, c);
		int b = minCostPathRecursion(arr, i, j + 1, n, m, c);
		int minCost = a < b ? a : b;

		return minCost + arr[i][j];
	}

	static int minCostPathMemoization(int arr[][], int i, int j, int n, int m, int c, Integer dp[][]) {
		if (i == n && j == m) {
			dp[i][j] = c + arr[i][j];
			return c + arr[i][j];
		}
		if (i > n || i < 0 || j > m || j < 0) {
			return Integer.MAX_VALUE;
		}

		if (dp[i][j] != null) {
			int a = dp[i][j];
			int b = c + dp[i][j];
			return a < b ? a : b;
		}

		int a = minCostPathMemoization(arr, i + 1, j, n, m, c, dp);
		int b = minCostPathMemoization(arr, i, j + 1, n, m, c, dp);
		int minCost = a < b ? a : b;

		dp[i][j] = minCost + arr[i][j];

		return minCost + arr[i][j];
	}

	static int minCostPathTabulation(int arr[][], Integer dp[][]) {

		int n = dp.length;
		int m = dp[0].length;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {

				if (i == n - 1 && j == m - 1) {
					dp[i][j] = arr[i][j];
				} else if (i == n - 1) {
					dp[i][j] = arr[i][j] + dp[i][j + 1];
				} else if (j == m - 1) {
					dp[i][j] = arr[i][j] + dp[i + 1][j];
				} else {
					int a = dp[i + 1][j];
					int b = dp[i][j + 1];
					dp[i][j] = arr[i][j] + (a < b ? a : b);
				}
			}
		}
		return dp[0][0];
	}

	// -----------------------------------------------------------
	static int maxGoldmineRecursion(int arr[][], int row, int col) {
		if (row >= arr.length || row < 0 || col < 0) {
			return 0;
		}

		if (col == arr.length - 1) {
			return arr[row][col];
		}

		int a = maxGoldmineRecursion(arr, row - 1, col + 1);
		int b = maxGoldmineRecursion(arr, row, col + 1);
		int c = maxGoldmineRecursion(arr, row + 1, col + 1);

		int abcMax = Math.max(c, Math.max(a, b));

		return abcMax + arr[row][col];

	}

	static int maxGoldmineHelper(int arr[][]) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int rowMax = maxGoldmineRecursion(arr, i, 0);
			max = Math.max(rowMax, max);
		}
		return max;
	}

	static int maxGoldmineTabulation(int arr[][], int dp[][]) {

		for (int j = dp[0].length - 1; j >= 0; j--) {
			for (int i = dp.length - 1; i >= 0; i--) {

				if (j == dp[0].length - 1) {
					dp[i][j] = arr[i][j];
				} else if (i == 0) {
					dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
				} else if (i == dp.length - 1) {
					dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i - 1][j + 1]);
				} else {
					dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], Math.max(dp[i - 1][j + 1], dp[i + 1][j + 1]));

				}
			}
		}

		int maxGold = dp[0][0];
		for (int i = 0; i < dp.length; i++) {
			if (dp[i][0] > maxGold) {
				maxGold = dp[i][0];
			}
		}
		return maxGold;

	}

	// -----------------------------------------------------------
	static int climbStairsVariableRecursion(int[] arr, int n, int N) {
		if (n == N) {
			return 1;
		}
		if (n > N) {
			return 0;
		}

		int sum = 0;
		for (int i = 1; i <= arr[n]; i++) {
			sum += climbStairsVariableRecursion(arr, n + i, N);
		}

		return sum;
	}

	static int climbStairsVariableMemoization(int[] arr, int n, int N, int[] dp) {

		if (n == N) {
			return 1;
		}
		if (n > N) {
			return 0;
		}

		if (dp[n] > 0)
			return dp[n];

		int sum = 0;
		for (int i = 1; i <= arr[n]; i++) {
			sum += climbStairsVariableRecursion(arr, n + i, N);
		}

		dp[n] = sum;
		return sum;
	}

	static int climbStairsVariableTabulation(int[] arr, int n, int N) {
		int dp[] = new int[arr.length + 1];

		for (int i = dp.length - 1; i >= 0; i--) {
			if (i == dp.length - 1) {
				dp[i] = 1;
			} else {
				for (int j = 1; j <= arr[i] && i + j < dp.length; j++) {
					dp[i] += dp[i + j];
				}
			}
		}
		return dp[0];
	}

	// ------------------------------------------------------------
	// not working
	static Integer climbStairsMinMovesRecursion(int arr[], int n, int N) {
		if (n == N) {
			return 0;
		}
		if (n > N) {
			return null;
		}
		int possibleMoves = arr[n];
		Integer m = null;
		for (int i = 1; i <= possibleMoves; i++) {
			if (m == null) {
				m = climbStairsMinMovesRecursion(arr, n + i, N);
			} else {
				m = Math.min(m, climbStairsMinMovesRecursion(arr, n + i, N));
			}
		}
		if (m == null)
			return m;
		return m + 1;
	}

	static int climbStairsMinMovesTabulation(int arr[]) {
		Integer dp[] = new Integer[arr.length + 1];
		dp[dp.length - 1] = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			int moves = arr[i];
			int min = 1000;
			for (int j = 1; j <= moves; j++) {
				if (i + j < dp.length && dp[i + j] != null) {
					min = Math.min(min, dp[i + j]);
				}
			}
			dp[i] = min + 1;
		}
		return dp[0];
	}

	// ------------------------------------------------------------
	// pepcoding target sum
	static boolean isSubsetSumRecursion(int arr[], int tar, int n) {

		if (tar == 0) {
			return true;
		}

		if (n == 0)
			return false;

		if (arr[n - 1] > tar) {
			return isSubsetSumRecursion(arr, tar, n - 1);
		} else {
			return isSubsetSumRecursion(arr, tar - arr[n - 1], n - 1) || isSubsetSumRecursion(arr, tar, n - 1);
		}
	}

	static Boolean isSubsetSumMemoization(int arr[], int tar, int n, Boolean[][] dp) {

		if (tar == 0) {
			return dp[n][tar] = true;
		}
		if (n == 0)
			return false;

		if (dp[n][tar] != null) {
			return dp[n][tar];
		}

		if (arr[n - 1] > tar) {
			return dp[n][tar] = isSubsetSumMemoization(arr, tar, n - 1, dp);
		} else {
			return dp[n][tar] = isSubsetSumMemoization(arr, tar, n - 1, dp)
					|| isSubsetSumMemoization(arr, tar - arr[n - 1], n - 1, dp);
		}
	}

	static boolean isSubsetSumMemoizationMain(int[] arr, int tar) {
		Boolean dp[][] = new Boolean[arr.length + 1][tar + 1];
		boolean ans = isSubsetSumMemoization(arr, tar, arr.length, dp);
		return ans;
	}

	static boolean isSubsetSumTabulation(int[] arr, int sum, int n, boolean[][] dp) {

		for (int i = 0; i < n + 1; i++) {
			dp[i][0] = true;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < sum + 1; j++) {
				if (arr[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
				}
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}

	static boolean isSubsetSum(int[] arr, int sum) {
		boolean[][] dp = new boolean[arr.length + 1][sum + 1];
		return isSubsetSumTabulation(arr, sum, arr.length, dp);
	}

	// -------------------------------------------------------------------

	static int coinChangeCombinationRecursion(int arr[], int sum, int n) {
		if (sum == 0) {
			return 1;
		}
		if (sum < 0 || n == 0) {
			return 0;
		}

		if (arr[n - 1] <= sum) {
			return coinChangeCombinationRecursion(arr, sum - arr[n - 1], n)
					+ coinChangeCombinationRecursion(arr, sum, n - 1);
		}
		return coinChangeCombinationRecursion(arr, sum, n - 1);
	}

	static int coinChangeCombinationMemo2DMain(int arr[], int sum, int n) {
		int dp[][] = new int[n + 1][sum + 1];
		return coinChangeCombinationMemoization(arr, sum, n, dp);

	}

	static int coinChangeCombinationMemoization(int arr[], int sum, int n, int[][] dp) {
		if (sum == 0) {
			return 1;
		}
		if (sum < 0 || n == 0) {
			return 0;
		}
		if (dp[n][sum] > 0) {
			return dp[n][sum];
		}

		if (arr[n - 1] <= sum) {
			return dp[n][sum] = coinChangeCombinationMemoization(arr, sum - arr[n - 1], n, dp)
					+ coinChangeCombinationMemoization(arr, sum, n - 1, dp);
		}
		return dp[n][sum] = coinChangeCombinationMemoization(arr, sum, n - 1, dp);
	}

	static int coinChangeCombinationTabulation2DMain(int arr[], int sum, int n) {
		int dp[][] = new int[n + 1][sum + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		return coinChangeCombinationTabulation2D(arr, sum, n, dp);
	}

	static int coinChangeCombinationTabulation2D(int arr[], int sum, int n, int dp[][]) {

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < sum + 1; j++) {
				if (arr[i - 1] <= j && j - arr[i - 1] >= 0) {
					dp[i][j] = dp[i][j - arr[i - 1]] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

//		display2(dp);

		return dp[n][sum];

	}

	// --------------------------------------------------------------------------

	static int coinChangePermutationRecursion(int arr[], int sum, int n) {
		if (sum == 0) {
			return 1;
		}
		if (sum < 0 || n == 0) {
			return 0;
		}
		if (n < 0)
			return 0;

		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[n - 1] <= sum) {
				ans = +coinChangePermutationRecursion(arr, sum - arr[n - 1], n)
						+ coinChangePermutationRecursion(arr, sum, n - 1);
			} else {
				ans += coinChangePermutationRecursion(arr, sum, n - 1);
			}
		}

		return ans;
	}

	// --------------------------------------------------------------------------
	static int countEncoding(String str) {

		int dp[] = new int[str.length()];

		dp[0] = 1;
		for (int i = 1; i < str.length(); i++) {
			char l = str.charAt(i - 1);
			char r = str.charAt(i);

			if (l == '0' && r == '0') {
				dp[i] = 0;
			} else if (l == '0' && r != '0') {
				dp[i] = dp[i - 1];
			} else if (l != '0' && r == '0') {
				if (Integer.parseInt("" + l + r) <= 26) {
					dp[i] = i >= 2 ? dp[i - 2] : 1;
				} else {
					dp[i] = 0;
				}
			} else {
				if ((Integer.parseInt("" + l + r) <= 26)) {
					dp[i] = dp[i - 1] + ((i >= 2) ? dp[i - 2] : 1);
				} else {
					dp[i] = dp[i - 1];
				}
			}
		}
		return dp[str.length() - 1];

	}

	// --------------------------------------------------------------------------------------
	static int countAbcSubseq(String str) {
		int n = str.length();
		int dp[][] = new int[3][n + 1];

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a') {
				dp[0][i + 1] = 2 * dp[0][i] + 1;

				dp[1][i + 1] = dp[1][i];
				dp[2][i + 1] = dp[2][i];
			} else if (str.charAt(i) == 'b') {
				dp[1][i + 1] = 2 * dp[1][i] + dp[0][i];

				dp[0][i + 1] = dp[0][i];
				dp[2][i + 1] = dp[2][i];
			} else if (str.charAt(i) == 'c') {
				dp[2][i + 1] = 2 * dp[2][i] + dp[1][i];

				dp[1][i + 1] = dp[1][i];
				dp[0][i + 1] = dp[0][i];
			}
		}
//		display2(dp);

		return dp[2][n];

	}

	// ----------------------------------------------------------------------------
	// greedy
	static int maxNonAdjacent(int arr[]) {
		return maxNonAdj(arr, arr.length, false);
	}

	static int maxNonAdj(int[] arr, int n, boolean lastTaken) {

		if (n == 0) {
			return 0;
		}

		if (lastTaken == false) {
			return Math.max(arr[n - 1] + maxNonAdj(arr, n - 1, true), maxNonAdj(arr, n - 1, false));
		} else {
			return maxNonAdj(arr, n - 1, false);
		}
	}

	static int maxNonAdjTabulation(int[] arr) {
//		int dp[]=new int[arr.length+1];
//		
//		dp[0]=0;
//		dp[1]=Math.max(0, arr[0]); // in case first element is negative, don't include
//		
//		for(int i=2;i<dp.length;i++) {
//			dp[i]=Math.max(arr[i-1]+dp[i-2], dp[i-1]);
//		}
//		return dp[dp.length-1];

		// greedy

		int inc = arr[0];
		int exc = 0;
		for (int i = 1; i < arr.length; i++) {
			int nInc = arr[i] + exc;
			int nExc = Math.max(exc, inc);

			inc = nInc;
			exc = nExc;
		}
		return Math.max(inc, exc);

	}

	static void paintHouse(int arr[][]) {
		long rI = arr[0][0];
		long bI = arr[0][1];
		long gI = arr[0][2];
		for (int i = 1; i < arr.length; i++) {
			long r = arr[i][0] + Math.min(bI, gI);

			long b = arr[i][1] + Math.min(rI, gI);

			long g = arr[i][2] + Math.min(rI, bI);

			rI = r;
			bI = b;
			gI = g;
		}
		System.out.println(Math.min(rI, Math.min(bI, gI)));
	}

	static int getPrevMinExcludingIndex(int arr[], int j) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (i == j) {
				continue;
			} else {
				min = Math.min(min, arr[i]);
			}
		}
		return min;
	}

	static void paintHouseManyColors(int[][] arr, int n, int k) {
		long dp[][] = new long[n][k];

		long least = Integer.MAX_VALUE;
		long sleast = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++) {
			dp[0][i] = arr[0][i];

			if (arr[0][i] <= least) {
				sleast = least;
				least = arr[0][i];
			} else if (arr[0][i] <= sleast) {
				sleast = arr[0][i];
			}
		}

		for (int i = 1; i < n; i++) {

			long l = Integer.MAX_VALUE;
			long sl = Integer.MAX_VALUE;

			for (int j = 0; j < k; j++) {
				dp[i][j] = arr[i][j] + (least == dp[i - 1][j] ? sleast : least);

				if (dp[i][j] <= l) {
					sl = l;
					l = dp[i][j];
				} else if (dp[i][j] <= sl) {
					sl = dp[i][j];
				}
			}
			least = l;
			sleast = sl;
		}
		System.out.println(least);
	}

	// paint fence

	// -----------------------------------------------

	static int tiling2x1Recursion(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		return tiling2x1Recursion(n - 1) + tiling2x1Recursion(n - 2);
	}

	static int tiling2x1(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		int a = 1;
		int b = 2;
		int c = 0;
		for (int i = 3; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}
	// -------------------------------------------------------

	static int tilingMx1Rec(int n, int m) {
		if (n == 1)
			return 1;
		if (n == m)
			return 2;
		if (n > 1 && n < m)
			return 1;
		if (n < 1)
			return 0;

		return tilingMx1Rec(n - 1, m) + tilingMx1Rec(n - m, m);
	}

	static int tilingMx1Memoization(int n, int m) {
		int dp[] = new int[n + 1];
		Arrays.fill(dp, -1);
		return tilingMx1Memo(n, m, dp);
	}

	static int tilingMx1Memo(int n, int m, int dp[]) {
		if (n == 1)
			return 1;
		if (n == m)
			return 2;
		if (n > 1 && n < m)
			return 1;
		if (n < 1)
			return 0;
		if (dp[n] != -1) {
			return dp[n];
		}

		return dp[n] = tilingMx1Memo(n - 1, m, dp) + tilingMx1Memo(n - m, m, dp);
	}

	static int friendsPairing(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		return friendsPairing(n - 1) + friendsPairing(n - 2) * (n - 1);

	}

	static void buySellStock1Transaction(int[] arr) {
		int min = arr[0];
		int profit = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			} else {
				profit = Math.max(profit, arr[i] - min);
			}
		}
		System.out.println(profit);
	}

	static int buySellStockInfiniteTransaction(int[] arr) {
		int profit = 0;
		for (int i = 1; i < arr.length; i++) {
			profit = Math.max(profit, profit + arr[i] - arr[i - 1]);

		}
		return profit;
	}

	public static void main(String[] args) {
		/*
		 * Scanner sc = new Scanner(System.in); int n = sc.nextInt(); int m =
		 * sc.nextInt(); int arr[][] = new int[n][m]; for (int i = 0; i < n; i++) { for
		 * (int j = 0; j < m; j++) { arr[i][j] = sc.nextInt(); } }
		 */

		int arr[] = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
		int brr[] = { 1, 1, 1, 4, 9, 8, 1, 1, 10, 1 };

		int coins[] = { 2, 3, 5 };
		int sum = 7;

//		System.out.println(coinChangeCombinationRecursion(coins, sum, coins.length));
//		System.out.println(coinChangeCombinationTabulation2DMain(coins, sum, coins.length));
//		System.out.println(coinChangePermutationRecursion(coins, sum, coins.length));

//		System.out.println(countEncoding("123"));
//		System.out.println("aa".substring(0, 2));
//		
//		System.out.println(countAbcSubseq("abcabc"));
		int ele[] = { 5, 10, 10, 100, 5, 6 };
		System.out.println(maxNonAdjacent(ele));
		System.out.println(maxNonAdjTabulation(ele));
		System.out.println(tiling2x1Recursion(8));
		System.out.println(tilingMx1Rec(39, 16));

	}

}
