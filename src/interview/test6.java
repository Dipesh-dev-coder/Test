package interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class test6 {

	public static void main(String[] args) {
		// Remove the duplicates from a list of integers, keeping the last (rightmost) occurrence of each element.
		List<Integer> list = Arrays.asList(1,3,5,7,3,9,2,1,7);
		Set<Integer> set = new HashSet<Integer>();
		List<Integer> duplicates = list.stream().filter(n -> !set.add(n)).collect(Collectors.toList());
		System.out.println("duplicates value <"+duplicates+">");

	}

}
