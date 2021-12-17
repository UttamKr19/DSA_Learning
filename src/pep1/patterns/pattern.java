package pep1.patterns;

public class pattern {

	static void p1() {
		int n = 5;
		int i = 0;

		for (i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}

	}

	static void p2() {
		int n = 5;
		int i = 0;

		for (i = 0; i <= n; i++) {
			for (int j = i; j < n; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}

	}

	static void p3() {
		int n = 5;
		int i = 0;

		for (i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				System.out.print(" ");
			}
			for (int k = 0; k <= i; k++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}

	static void p4() {
		int n = 5;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}

			for (int k = 0; k < n - i; k++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}

	static void p5() {
		int n = 5;

		int sp = n / 2;
		int st = 1;

		for (int i = 0; i < n; i++) {

//			System.out.println(sp+" "+st);
			for (int j = 0; j < sp; j++) {
				System.out.print(" ");
			}

			for (int j = 0; j < st; j++) {
				System.out.print("*");
			}

			if (i < n / 2) {
				sp--;
				st = st + 2;
			} else {
				sp++;
				st = st - 2;
			}

			System.out.println();
		}

	}

	static void p6() {
		int n = 5;

		int st = n / 2;
		int sp = 1;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < st + 1; j++) {
				System.out.print("*");
			}

			for (int j = 0; j < sp; j++) {
				System.out.print(" ");
			}

			for (int j = 0; j < st + 1; j++) {
				System.out.print("*");
			}

			if (i < n / 2) {
				st--;
				sp = sp + 2;
			} else {
				st++;
				sp = sp - 2;
			}

			System.out.println();
		}

	}

	static void p7() {

		int n = 5;
		int sp = 0;
		int st = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < sp; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < st; j++) {
				System.out.print("*");
			}
			System.out.println();
			sp++;

		}
	}

	static void p8() {

		int n = 5;
		int sp = n - 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < sp; j++) {
				System.out.print(" ");
			}
			System.out.print("*");
			System.out.println();
			sp--;
		}
	}

	static void p9() {
		int n = 5;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				if (i == j || i + j == n - 1) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}

			System.out.println();
		}
	}

	static void p10() {
		int n = 7;
		int a = n / 2;
		int b = -1;

		for (int i = 0; i < n; i++) {
			if (i == 0 || i == n - 1) {
				for (int j = 0; j < n / 2; j++) {
					System.out.print(" ");
				}
				System.out.print("*");
			} else {
				for (int j = 0; j < a; j++) {
					System.out.print(" ");
				}
				System.out.print("*");
				for (int j = 0; j < b; j++) {
					System.out.print(" ");
				}
				System.out.print("*");

			}

			if (i < n / 2) {
				a--;
				b = b + 2;
			} else {
				a++;
				b = b - 2;
			}

			System.out.println();
		}
	}

	static void p11() {
		int n = 5;
		int a = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(a++ + " ");
			}
			System.out.println();
		}

	}

	static void p12() {
		int n = 5;
		int a = 0;
		int b = 1;
		int c;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(a + " \t");
				c = a + b;

				a = b;
				b = c;

			}
			System.out.println();
		}
	}

	static void p13() {
		int n = 6;

		for (int i = 0; i < n; i++) {
			int iCj = 1;
			for (int j = 0; j <= i; j++) {

				System.out.print(iCj + " \t");
				int iCjp1 = iCj * (i - j) / (j + 1);
				iCj = iCjp1;
			}
			System.out.println();
		}
	}

	static void p14() {
		int n = 5;
		for (int i = 1; i <= n; i++) {
			System.out.println(n + " x " + i + " = " + (n * i));
		}
	}

	static void p15() {
		int n = 5;
		int sp = n / 2;
		int st = 1;
		int val = 1;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < sp; j++) {
				System.out.print("\t");
			}

			int x = val;
			for (int j = 0; j < st; j++) {
				if (j < st / 2)
					System.out.print(x++ + "\t");
				else {
					System.out.print(x-- + "\t");
				}
			}

			if (i < n / 2) {
				val++;
				sp--;
				st = st + 2;
			} else {
				val--;
				sp++;
				st = st - 2;
			}

			System.out.println();
		}
	}

	static void p16() {
		int n = 6;
		int sp = 2 * n - 3;
		int st = 1;
		int val = 1;

		for (int i = 0; i < n; i++) {

			for (int j = 1; j <= val; j++) {
				System.out.print(j + "\t");
			}
			for (int j = 0; j < sp; j++) {
				System.out.print("\t");
			}
			if (i == n - 1)
				val--;
			for (int j = val; j > 0; j--) {
				System.out.print(j + "\t");
			}
			val++;
			sp = sp - 2;

			System.out.println();
		}
	}

	static void p17() {
		int n = 5;
		int sp = n / 2;
		int st = 1;
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < sp; j++) {
				if (i == (n / 2))
					System.out.print("*\t");
				else
					System.out.print("\t");
			}

			for (int j = 0; j < st; j++) {
				System.out.print("*\t");
			}

			if (i < n / 2) {
				st++;
			} else {
				st--;
			}

			System.out.println();
		}
	}

	static void p18() {
		int n = 9;
		int sp = 0;
		int st = n;
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < sp; j++) {
				System.out.print("\t");
			}

			for (int j = 0; j < st; j++) {
				if (i > 0 && i < n / 2 && j > 0 && j < st - 1)
					System.out.print("\t");
				else
					System.out.print("*\t");
			}

			if (i < n / 2) {
				st = st - 2;
				sp++;
			} else {
				st = st + 2;
				sp--;

			}

			System.out.println();
		}
	}
	
	
	static void p19() {
		//swastik
	}
	
	
	static void p20() {
		int n=5;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if( i>=n/2 && (i==j || i+j==n-1))
					System.out.print("* ");
				else if(j==0 || j==n-1 )
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		p20();
	}
}
