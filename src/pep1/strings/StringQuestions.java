package pep1.strings;

public class StringQuestions {
	static boolean isPalindrome(String str) {
		for(int i=0;i<str.length()/2;i++) {
			if(str.charAt(i)!=str.charAt(str.length()-i-1))
				return false;
		}
		return true;
	}
	static void substringsPalindrome(String str) {
		for(int i=0;i<str.length();i++) {
			for(int j=i;j<str.length();j++) {
				String sub=str.substring(i,j+1);
				if(isPalindrome(sub))
					System.out.println(sub);
			}
		}
	}
	
	static void stringCompression1(String str) {
		StringBuilder com=new StringBuilder();
		com.append(str.charAt(0));
		for(int i=1;i<str.length();i++) {
			if(str.charAt(i)!=str.charAt(i-1)) {
				com.append(str.charAt(i));
			}
		}
		System.out.println(com);
		
	}

	static void stringCompression2(String str) {
		StringBuilder com=new StringBuilder();
		com.append(str.charAt(0));
		int count=1;
		for(int i=1;i<str.length();i++) {
			if(str.charAt(i)==str.charAt(i-1)) {
				count++;
			}
			else {
				if(count>1)
					com.append(count);
				
				com.append(str.charAt(i));
				count=1;
			}
		}
		if(count>1)
			com.append(count);
		
		System.out.println(com);
		
	}
	
	static void toggleCase(String str) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<str.length();i++) {
			char ch=str.charAt(i);
			if(ch>='a' && ch<='z'){
				char uc=(char)('A'+ch-'a');
				sb.append(uc);
			}else if(ch>='A' && ch<='Z'){
				char lc=(char)('a'+ch-'A');
				sb.append(lc);
			}
		}
		System.out.println(sb);
	}
	
	static void asciiDiff(String str) {
		StringBuilder sb=new StringBuilder();
		sb.append(str.charAt(0));
		for(int i=1;i<str.length();i++) {
			sb.append(str.charAt(i)-str.charAt(i-1));
			sb.append(str.charAt(i));
		}
		System.out.println(sb);
	}
	
	static void swap(StringBuilder str,int i,int j) {
		char t=str.charAt(i);
		str.setCharAt(i, str.charAt(j));
		str.setCharAt(j, t);
	}
	
	
	//using recursion 'n backtracking
	// not working for repeating chars
	static void perm(StringBuilder str,int l,int r) {
		
		if(l==r) {
			System.out.println(str);
			return;
		}
		
		for(int i=l;i<=r;i++) {
			swap(str,l,i);
			perm(str,l+1,r);
			swap(str,l,i);
		}
		
	}
	
	public static void main(String[] args) {
		String str="xaaabbbbbccdeefffgghijkkkkll";
//		System.out.println(str);
//		stringCompression1(str);
//		stringCompression2(str);
//		str="aBcDDDdeajflJDS";
//		System.out.println(str);
//		toggleCase(str);
		
//		str="abdwacgaghjac";
//		System.out.println(str);
//		asciiDiff(str);
		
		StringBuilder sb=new StringBuilder("aba");
		
		perm(sb,0,sb.length()-1);
		
	}
}
