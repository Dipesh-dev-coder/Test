package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class test {

	public static void main(String[] args) {
		
		List<Integer> list = Arrays.asList(1,4,7,8,9,3,7,5,4);
		Set<Integer> set = new HashSet<Integer>();
		List<Integer> dupIntegers = list.stream().filter(n -> !set.add(n)).collect(Collectors.toList());
		System.out.println(dupIntegers);
		
		List<String> name = Arrays.asList("Dipesh","Abhi","Rahul","Aakash","Rohit");
		List<Integer> list1 = name.stream().map(a -> a.length()).collect(Collectors.toList());
		System.out.println(list1);
		
		List<List<Integer>> number = new ArrayList<List<Integer>>();
		number.add(Arrays.asList(1,2));
		number.add(Arrays.asList(3,4));
		number.add(Arrays.asList(6,5));
		number.add(Arrays.asList(7,8));
		number.add(Arrays.asList(1,9));
		
		List<Integer> list2 = number.stream().flatMap(b -> b.stream()).collect(Collectors.toList());
		System.out.println(list2);
		
		//int[] arr = new int[]{2, 3, 4, 6, 8, 9, 12,27};
		int oddOne = list.stream().filter(num -> num % 2 !=0).mapToInt(Integer :: intValue).sum();
		System.out.println(oddOne);
		
		String input = "gainjavaknowledge";
        Map<String, Long> output = Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Output : "+output);
	}
}
