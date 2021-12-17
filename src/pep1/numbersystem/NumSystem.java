package pep1.numbersystem;

public class NumSystem {
	
	static int decimalToAnyBase(int num,int base) {
		
		int ans=0;
		int pow=1;
		
		while(num!=0) {
			int rem=num%base;
			ans=ans+rem*pow;
			
			pow=pow*10;
			num=num/base;
		}
		
//		System.out.println(ans);
		return ans;
	}
	
	static int anyBaseToDecimal(int num,int base) {
		int ans=0;
		int pow=1;
		
		while(num!=0) {
			int rem=num%10;
			ans=ans+rem*pow;
			
			pow=pow*base;
			num=num/10;
		}
		
//		System.out.println(ans);
		return ans;
	}
	
	static int anyBaseToAnyBase(int num,int baseFrom,int baseTo) {
		
		int toDecimal=anyBaseToDecimal(num, baseFrom);
		int ans=decimalToAnyBase(toDecimal, baseTo);
		
//		System.out.println(ans);
		return ans;
	}
	
	
	static int anyBaseAddition(int a,int b,int base) {
		
		int carry=0;
		int dig=0;
		int ans=0;
		int pow=1;
		while(a!=0 || b!=0) {
			int dig1=a%10;
			int dig2=b%10;
			
			dig=dig1+dig2+carry;
			
			carry=dig/base;
			dig=dig%base;
			
			ans=ans+dig*pow;
			pow=pow*10;
			
			a=a/10;
			b=b/10;
			
		}
		
		if(carry!=0) {
			ans=ans+carry*pow;
		}
		
//		System.out.println(ans);
		return ans;
	}
	
	static int anyBaseSubtraction(int a,int b, int base) {
		// b>a
		
//		if(a>b) {
//			a=a+b;
//			b=a-b;
//			a=a-b;
//		}
		
		int carry=0;
		int dig=0;
		int ans=0;
		int pow=1;
		
		while(b>0) {
			int d1=a%10;
			int d2=b%10;
			
			d2=d2+carry;
			
			if(d2>=d1) {
				carry=0;
				dig=d2-d1;
			}
			else {
				carry=-1;
				dig=d2+base-d1;
			}
			
			ans=ans+dig*pow;
			pow=pow*10;
			
//			System.out.println(ans);

			a=a/10;
			b=b/10;
			
		}
		return ans;
	}
	
	static int anyBaseMultiplication(int n1,int n2,int base) {
		int ans=0;
		
		int p=1;
		
		while(n2!=0) {
			int d1=n2%10;

			int mul=0;
			int pow=1;
			int carry=0;
			int dig=0;
			int a=n1;
			
			while(a!=0) {
				int d2=a%10;
				dig=d2*d1+carry;
				
				carry=dig/base;
				dig=dig%base;
				
				mul=mul+dig*pow;
				pow=pow*10;
				
				a=a/10;	
			}
			
			if(carry!=0) {
				mul=mul+carry*pow;
			}
			ans=anyBaseAddition(ans, mul*p, base);

			p=p*10;
			n2=n2/10;
		}
		
		return ans;
	}
	public static void main(String[] args) {
//		decimalToAnyBase(57, 2);
//		anyBaseToDecimal(111001, 2);
//		anyBaseToAnyBase(172, 8, 2);
		
//		anyBaseAddition(234, 343, 5);
//		anyBaseAddition(346, 777, 8);
//		anyBaseSubtraction(256, 1212, 8);
		
//		int a=anyBaseToAnyBase(634, 8, 10);
//		System.out.println(a);
		
		int m=anyBaseMultiplication(234, 76, 8);
		System.out.println(m);
		
	}
}
