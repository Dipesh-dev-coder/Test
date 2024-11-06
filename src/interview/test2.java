package interview;

import java.util.stream.IntStream;

public class test2 {

	public static void main(String[] args) {
		String str = "Dipesh";
		boolean output = test(str.toLowerCase());
		System.out.println(output);
		
		int num = 121;
        String temp = Integer.toString(num);
        boolean val = IntStream.range(0,temp.length()/2).noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length()-i-1));
        System.out.println("result "+val);
	}
	
	private static boolean test(String str)
	{
		String empty ="";
		boolean returnValue = false;
		for(int i = str.length() -1; i >= 0; i--)
		{
			empty = empty + str.charAt(i);
		}
		if(empty.equals(str))
		{
			returnValue = true;
		}
		
		return returnValue;
		
	}

}
