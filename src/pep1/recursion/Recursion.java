package pep1.recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class Recursion {

	static void printDecreasing(int n) {
		if (n == 0) {
			return;
		}
		System.out.println(n);
		printDecreasing(n - 1);
	}

	static void printIncreasing(int n) {
		if (n == 0) {
			return;
		}
		printIncreasing(n - 1);
		System.out.println(n);
	}

	static void printDecreasingIncreasing(int n) {
		if (n == 0) {
			return;
		}

		System.out.println(n);
		printDecreasingIncreasing(n - 1);
		System.out.println(n);
	}

	static int factorial(int n) {
		if (n == 1 || n == 0)
			return 1;
		return n * factorial(n - 1);
	}

	static int powerLinear(int x, int n) {

		if (n == 0)
			return 1;

		return x * powerLinear(x, n - 1);
	}

	static int powerLog(int x, int n) {
		if (n == 0)
			return 1;

		int xpnb2 = powerLog(x, n / 2);
		int xn = xpnb2 * xpnb2;

		if (n % 2 == 1)
			xn = xn * x;

		return xn;
	}

	static void printZigZag(int n) {
		if (n == 0)
			return;

		System.out.println("pre " + n);
		printZigZag(n - 1);
		System.out.println("in " + n);
		printZigZag(n - 1);
		System.out.println("post " + n);
	}

	static void toh(int n, String a, String b, String c) {
		if (n == 0) {
			return;
		}

		toh(n - 1, a, c, b);
		System.out.println(n + " | " + a + " --> " + b);
		toh(n - 1, c, b, a);

	}

	static ArrayList<String> getSubSequence(String str) {

		if (str.length() == 0) {
			ArrayList<String> base = new ArrayList<>();
			base.add("");
			return base;
		}

		String first = str.substring(0, 1);
		String restStr = str.substring(1);
		ArrayList<String> restAnswer = getSubSequence(restStr);
		int n = restAnswer.size();
		for (int i = 0; i < n; i++) {
			restAnswer.add(first + restAnswer.get(i));
		}

		return restAnswer;
	}

	static ArrayList<String> getKeypadCombination(String[] str, String inp) {

		if (inp.length() == 0) {
			ArrayList<String> base = new ArrayList<>();
			base.add("");
			return base;
		}

		String first = inp.substring(0, 1);
		String restInp = inp.substring(1);
		ArrayList<String> restAnswer = getKeypadCombination(str, restInp);

		int ind = Integer.parseInt(first);
		String keys = str[ind];

		ArrayList<String> fullAnswer = new ArrayList<>();

		for (int i = 0; i < restAnswer.size(); i++) {
			for (int j = 0; j < keys.length(); j++) {
				fullAnswer.add(keys.charAt(j) + restAnswer.get(i));
			}

		}
		return fullAnswer;

	}

	static ArrayList<String> getStairPaths(int n) {

		if (n == 0) {
			ArrayList<String> baseZero = new ArrayList<>();
			baseZero.add("");
			return baseZero;
		}
		if (n < 0) {
			ArrayList<String> base = new ArrayList<>();
			return base;
		}

		ArrayList<String> one = getStairPaths(n - 1);

		ArrayList<String> two = getStairPaths(n - 2);

		ArrayList<String> three = getStairPaths(n - 3);

		ArrayList<String> result = new ArrayList<>();

		for (int i = 0; i < one.size(); i++) {
			result.add(1 + one.get(i));
		}
		for (int i = 0; i < two.size(); i++) {
			result.add(2 + two.get(i));
		}
		for (int i = 0; i < three.size(); i++) {
			result.add(3 + three.get(i));
		}

		return result;

	}

	static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {

		if (dr == sr && dc == sc) {
			ArrayList<String> base = new ArrayList<>();
			base.add("");
			return base;
		}
		if (sc > dc || sr > dr) {
			ArrayList<String> base = new ArrayList<>();
			return base;
		}

		ArrayList<String> hpaths = getMazePaths(sr, sc + 1, dr, dc);
		ArrayList<String> vpaths = getMazePaths(sr + 1, sc, dr, dc);
		ArrayList<String> c = new ArrayList<>();

		for (int i = 0; i < hpaths.size(); i++) {
			c.add("h" + hpaths.get(i));
		}

		for (int i = 0; i < vpaths.size(); i++) {
			c.add("v" + vpaths.get(i));
		}

		return c;
	}

	static ArrayList<String> getMazePathsWithJumps(int sr, int sc, int dr, int dc) {

		if (sr == dr && sc == dc) {
			ArrayList<String> base = new ArrayList<>();
			base.add("");
			return base;
		}

		ArrayList<String> result = new ArrayList<>();

		int hMoves = dc - sc;
		for (int i = 1; i <= hMoves; i++) {
			ArrayList<String> hPaths = getMazePathsWithJumps(sr, sc + i, dr, dc);
			for (int j = 0; j < hPaths.size(); j++) {
				result.add("h" + i + hPaths.get(j));
			}
		}

		int vMoves = dr - sr;
		for (int i = 1; i <= vMoves; i++) {
			ArrayList<String> vPaths = getMazePathsWithJumps(sr + i, sc, dr, dc);
			for (int j = 0; j < vPaths.size(); j++) {
				result.add("v" + i + vPaths.get(j));
			}
		}

		for (int i = 1; i <= hMoves && i <= vMoves; i++) {
			ArrayList<String> dPaths = getMazePathsWithJumps(sr + i, sc + i, dr, dc);
			for (int j = 0; j < dPaths.size(); j++) {
				result.add("d" + i + dPaths.get(j));
			}
		}

		return result;

	}

	static void printSubsequence(String str, int i, String ans) {

		if (i == str.length()) {
			System.out.println(ans);
			return;
		}

		printSubsequence(str, i + 1, ans + str.charAt(i));
		printSubsequence(str, i + 1, ans);

	}

	static void printSubsequence2(String str, String ans) {
		if (str.length() == 0) {
			System.out.println(ans);
			return;
		}

		printSubsequence2(str.substring(1), ans + str.substring(0, 1));
		printSubsequence2(str.substring(1), ans);

	}

	static void printKeypadCombination(String str, String ans) {
		String[] code = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
		printKeypadCombo(str, ans, code);
	}

	static void printKeypadCombo(String str, String ans, String[] code) {

		if (str.length() == 0) {
			System.out.println(ans);
			return;
		}

		int val = Integer.parseInt(str.substring(0, 1));
		String possibleCodes = code[val];

		for (int i = 0; i < possibleCodes.length(); i++) {
			printKeypadCombo(str.substring(1), ans + possibleCodes.charAt(i), code);
		}

	}

	static void printStairPaths(int n, String path) {

		if (n == 0) {
			System.out.println(path);
			return;
		}
		if (n < 0) {
			return;
		}

		printStairPaths(n - 1, path + "1");
		printStairPaths(n - 2, path + "2");
		printStairPaths(n - 3, path + "3");

	}

	static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
		if (sr == dr && sc == dc) {
			System.out.println(psf);
			return;
		}
		if (sr > dr || sc > dc)
			return;

		printMazePaths(sr, sc + 1, dr, dc, psf + "h");
		printMazePaths(sr + 1, sc, dr, dc, psf + "v");

	}

	static void printMazePathsWithJumps(int sr, int sc, int dr, int dc, String psf) {

		if (sr == dr && sc == dc) {
			System.out.println(psf);
			return;
		}

		int mh = dc - sc;
		for (int i = 1; i <= mh; i++) {
			printMazePaths(sr, sc + i, dr, dc, psf + "h" + i);
		}

		int mv = dr - sr;
		for (int i = 1; i <= mv; i++) {
			printMazePaths(sr + i, sc, dr, dc, psf + "v" + i);
		}

		for (int i = 1; i <= mh && i <= mv; i++) {
			printMazePaths(sr + i, sc + i, dr, dc, psf + "d" + i);
		}
	}

	static void floodfill(int[][] maze, int sr, int sc, String asf) {

		if (sr < 0 || sc < 0 || sc >= maze[0].length || sr >= maze.length || maze[sr][sc] == 1 || maze[sr][sc] == -1) {
			return;
		}
		if (sc == maze[0].length - 1 && sr == maze.length - 1) {
			System.out.println(asf);
			return;
		}

		maze[sr][sc] = -1;
		floodfill(maze, sr - 1, sc, asf + "t");
		floodfill(maze, sr, sc - 1, asf + "l");
		floodfill(maze, sr + 1, sc, asf + "d");
		floodfill(maze, sr, sc + 1, asf + "r");
		maze[sr][sc] = 0;
	}

	static boolean isSafe(int[][] arr, int r, int c) {

		for (int i = r - 1, j = c; i >= 0; i--) {
			if (arr[i][j] == 1)
				return false;
		}
		for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
			if (arr[i][j] == 1)
				return false;
		}

		for (int i = r - 1, j = c + 1; i >= 0 && j < arr.length; i--, j++) {
			if (arr[i][j] == 1)
				return false;
		}
		return true;
	}

	static void printNQueens(int[][] chess, String qsf, int row) {

		if (row == chess.length) {
			System.out.println(qsf + ".");
			return;
		}

		for (int col = 0; col < chess.length; col++) {
			if (isSafe(chess, row, col) == true) {
				chess[row][col] = 1;
				printNQueens(chess, qsf + row + "-" + col + ", ", row + 1);
				chess[row][col] = 0;
			}
		}
	}

	static void displayBoard(int[][] chess) {
		for (int i = 0; i < chess.length; i++) {
			for (int j = 0; j < chess[0].length; j++) {
				System.out.print(chess[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

	static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {

		if (r < 0 || c < 0 || r >= chess.length || c >= chess[0].length || chess[r][c] > 0) {
			return;
		}

		if (upcomingMove == chess.length * chess[0].length) {
			chess[r][c] = upcomingMove;
			displayBoard(chess);
			chess[r][c] = 0;

		}

		chess[r][c] = upcomingMove;
		printKnightsTour(chess, r - 2, c + 1, upcomingMove + 1);
		printKnightsTour(chess, r - 1, c + 2, upcomingMove + 1);
		printKnightsTour(chess, r + 1, c + 2, upcomingMove + 1);
		printKnightsTour(chess, r + 2, c + 1, upcomingMove + 1);
		printKnightsTour(chess, r + 2, c - 1, upcomingMove + 1);
		printKnightsTour(chess, r + 1, c - 2, upcomingMove + 1);
		printKnightsTour(chess, r - 1, c - 2, upcomingMove + 1);
		printKnightsTour(chess, r - 2, c - 1, upcomingMove + 1);
		chess[r][c] = 0;

	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = 4;
		int arr[][] = new int[n][n];
		printNQueens(arr, "", 0);

	}

}
