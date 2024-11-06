package interview;

import java.util.Collections;

public class test1 {
	
	static void reverse(int b[], int n) {
		
		int[] c = new int[n];
		int j = n;
		for(int i=0; i<n; i++)
		{
			c[j-1]=b[i];
			j=j-1;
		}
		System.out.println("reversed array is: ");
		for(int k =0; k<n;k++)
		{
			System.out.println(c[k]);
		}
	}
	public static void main(String[] args) {
		
		//array 5 element
		int[] a = {1,2,3,4,5};
		reverse(a, a.length);	
	}
}

