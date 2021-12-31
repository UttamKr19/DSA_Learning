package av;

import java.util.Arrays;
import java.util.Iterator;

public class DPplaylist {

	// ------------------------------------------------------
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

	static void display3(boolean dp[][]) {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	// -----------------------------------------------------------------------
	// ======================= 0-1 knapsack ========================
	// -----------------------------------------------------------------------
	static int knapsack01Recursion(int wt[], int val[], int W, int n) {
		if (n == 0 || W == 0)
			return 0;

		if (wt[n - 1] > W) {
			return knapsack01Recursion(wt, val, W, n - 1);
		}

		return Math.max(knapsack01Recursion(wt, val, W, n - 1),
				val[n - 1] + knapsack01Recursion(wt, val, W - wt[n - 1], n - 1));

	}

	static int knapsack01Memoization(int wt[], int val[], int W, int n, int dp[][]) {
		if (n == 0 || W == 0)
			return 0;

		if (dp[n][W] != -1) {
			return dp[n][W];
		}
		if (wt[n - 1] > W) {
			return knapsack01Memoization(wt, val, W, n - 1, dp);
		}

		dp[n][W] = Math.max(knapsack01Memoization(wt, val, W, n - 1, dp),
				val[n - 1] + knapsack01Memoization(wt, val, W - wt[n - 1], n - 1, dp));
		return dp[n][W];
	}

	static int knapsack01Tabulation(int wt[], int val[], int W) {

		int dp[][] = new int[wt.length + 1][W + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0 || j == 0)
					dp[i][j] = 0;
			}
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (wt[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i - 1][j - wt[i - 1]]);
				}
			}
		}
		return dp[dp.length - 1][dp[0].length - 1];

	}

	// ------------------------------------------------------------
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

		// taking Boolean instead of boolean is important
		// true for possible, false for not possible, and null for unexplored
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

	static boolean subsetSumTabulation(int[] arr, int sum, int n, boolean[][] dp) {

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

	static boolean subsetSum(int[] arr, int sum) {
		boolean[][] dp = new boolean[arr.length + 1][sum + 1];
		return subsetSumTabulation(arr, sum, arr.length, dp);
	}

	public int subsetSum1d(int[] nums, int s) {
		int[] dp = new int[s + 1];
		dp[0] = 1;
		for (int n : nums)
			for (int i = s; i >= n; i--)
				dp[i] += dp[i - n];
		return dp[s];
	}

	// -----------------------------------------------------------------------

	static boolean equalSumPartition(int arr[]) {
		int sum = 0;
		for (int a : arr)
			sum += a;

		if (sum % 2 == 1) {
			return false;
		}

		return subsetSum(arr, sum / 2);
	}

	// -----------------------------------------------------------------------

	static int countOfSubsetSum(int[] arr, int sum) {

		int n = arr.length;
		int dp[][] = new int[n + 1][sum + 1];

		for (int i = 0; i < n + 1; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < sum + 1; j++) {
				if (arr[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
				}
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}

	// -----------------------------------------------------------------------

	static int minimumSubsetDifference(int[] arr) {
		int sum = 0;
		for (int i : arr)
			sum += i;

		for (int i = (int) Math.ceil(sum / 2); i >= 0; i--) {
			if (subsetSum(arr, i)) {
				return sum - i - i; // sum-2*i
			}
		}
		return sum;
	}

	// -----------------------------------------------------------------------

	// S1 U S2 = S
	static int countSubsetPartitionsWithGivenDifference(int arr[], int diff) {
		int sum = 0;
		for (int i : arr)
			sum += i;

		int target = (diff + sum) / 2;
		if (target % 2 == 1)
			return 0;

		return countOfSubsetSum(arr, target); // divide by 2 for pairs

	}

	// -----------------------------------------------------------------------
	// ======================= unbounded knapsack ========================
	// -----------------------------------------------------------------------

	static int knapsackUnboundedRecursion(int wt[], int val[], int W, int n) {
		if (W == 0 || n == 0)
			return 0;

		if (wt[n - 1] <= W) {
			return Math.max(val[n - 1] + knapsackUnboundedRecursion(wt, val, W - wt[n - 1], n),
					knapsackUnboundedRecursion(wt, val, W, n - 1));
		}
		return knapsackUnboundedRecursion(wt, val, W, n - 1);
	}

	// dp[][] initialized with -1 and if(i==0||j==0) dp[i][j]=0
	static int knapsackUnboundedMemoization(int wt[], int val[], int W, int n, int dp[][]) {
		if (n == 0 || W == 0)
			return 0;

		if (dp[n][W] != -1) {
			return dp[n][W];
		}

		if (wt[n - 1] <= W) {
			dp[n][W] = Math.max(knapsackUnboundedMemoization(wt, val, W, n - 1, dp),
					val[n - 1] + knapsackUnboundedMemoization(wt, val, W - wt[n - 1], n, dp));
		} else {
			dp[n][W] = knapsackUnboundedMemoization(wt, val, W, n - 1, dp);

		}

		return dp[n][W];
	}

	static int knapsackUnboundedTabulation(int wt[], int val[], int W) {
		int n = val.length;
		int dp[] = new int[W + 1];

		// Fill dp[] using above recursive formula
		for (int i = 0; i <= W; i++) {
			for (int j = 0; j < n; j++) {
				if (wt[j] <= i) {
					dp[i] = Math.max(dp[i], dp[i - wt[j]] + val[j]);
				}
			}
		}
		for (int i = 0; i <= W; i++) {
			System.out.print(dp[i] + " ");

		}
		System.out.println();

		return dp[W];
	}

	static int knapsackUnboundedTabulation2D(int wt[], int val[], int W) {
		int n = val.length;
		int dp[][] = new int[n + 1][W + 1];

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (wt[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i][j - wt[i - 1]]);
				}
			}
		}
		return dp[dp.length - 1][dp[0].length - 1];
	}

	static int rodCuttingMaximizeProfit(int len[], int[] profit, int N) {

		return knapsack01Tabulation(len, profit, N);
	}

	// ------------------------------------------------------------------------

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

		return dp[n][sum];

	}

	// --------------------------------------------------------------------------

	static int coinChangePermutationTabulation2DMain(int arr[], int sum) {
		int dp[][] = new int[arr.length][sum + 1];
		return coinChangePermutationTabulation2D(arr, sum, arr.length, dp);
	}

	static int coinChangePermutationTabulation2D(int arr[], int sum, int n, int dp[][]) {

		for (int j = 1; j < sum + 1; j++) {
			for (int i = 1; i < n + 1; i++) {
				if (arr[i - 1] <= j && j - arr[i - 1] >= 0) {
					dp[i][j] = dp[i][j - arr[i - 1]] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[n][sum];

	}

	public static void main(String[] args) {
//		int val[] = new int[] { 60, 100, 120 };
//		int wt[] = new int[] { 10, 20, 30 };
//		int W = 50;

		int W = 8;
		int val[] = { 10, 40, 50, 70 };
		int wt[] = { 1, 3, 4, 5 };

		int n = val.length;
//
		int dp[][] = new int[n + 1][W + 1];
		for (int[] is : dp) {
			Arrays.fill(is, -1);
		}
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < W + 1; j++) {
				if (i == 0 || j == 0)
					dp[i][j] = 0;
			}
		}

	}
}
