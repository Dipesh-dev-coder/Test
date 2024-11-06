package interview;

import java.util.Arrays;

public class test8 {

	public static void main(String[] args) {
		// •	Given an integer array nums, move all 0's to the end of it while maintaining
		//the relative order of the non-zero elements.

		int array[] = {125,78,34,98,1,0,5,88};
		int n = array.length;
		func(array, n);
		
	}
	public static void func(int array[], int n)
	{
		Arrays.sort(array);
		System.out.println("Element of array after moving all the zeros to the end of array: ");
		
		for(int i = n-1; i>=0; i--)
		{
			System.out.println(array[i] + " ");
		}
	}

}
