package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SecondHighestValue {

	public static int SecondHighest(Integer[]a, int total)
	{
		List<Integer> list = Arrays.asList(a);
		Collections.sort(list);
		int secondHighest = list.get(total-2);
		return secondHighest;
	}
	public static void main(String[] args) {
		
		Integer a[] = {3,7,8,9,2,82};
		Integer b[] = {33,87,38,99,22,48};
		
		System.out.println("Second highest number "+SecondHighest(a, 6));
		System.out.println("Second highest number "+SecondHighest(b, 6));
		
	}

}
