package interview;

public class test7 {

	public static void main(String[] args) {
		// •	Find out if a given string is a palindrome (a word, phrase, or
		 //sequence that reads the same backwards as forwards, e.g. madam or nurses run.)
		
		String str = "madam";
		String reverseStr ="";
		
		int strLenghth = str.length();
		for(int i = strLenghth -1; i>=0; --i)
		{
			reverseStr = reverseStr +str.charAt(i);
		}
		if(str.toLowerCase().equals(reverseStr.toLowerCase()))
		{
			System.out.println(str+" is a palindrome string.");
		}else {
			System.out.println(str+" is not a palindrome string.");
		}
	}

}
