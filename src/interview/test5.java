package interview;

import java.util.Arrays;
import java.util.List;

public class test5 {

	static int largestValue()
	{
		int arr[] = {10, 256, 678, 8, 26};

	  int i;
	  
	  int max = arr[0];
	  
	  
	  for(i = 1; i<arr.length;i++)
	  {
//	    if(arr[i]>max)
//		{
//			max=arr[i];
//		}
		  
		  max = Math.max(arr[i], max);
	  }
	  return max;
	}
	
	public static void main(String[] args) {
		
		
		System.out.println("largest Value is: "+largestValue());
		  List<Integer> list = Arrays.asList(10, 256, 678, 8, 26);
		  Integer output= list.stream().max(Integer::compare).get();
		  System.out.println("output :::::"+output);
	}

}
