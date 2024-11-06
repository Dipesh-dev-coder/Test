package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class occuranceOfWords {

	public static void main(String[] args) {
		
		List <String> wordsList1 = Arrays.asList("hi","bye","df","hi","bha");
	    Map<String, Integer> counts = wordsList1
	    		.parallelStream().
	            collect(Collectors.toConcurrentMap(
	                w -> w, w -> 1, Integer::sum));
	        System.out.println(counts);
		
		
//		wordsList = wordsList.stream().filter(s -> s.startsWith("b")).collect(Collectors.toList());
//		for(String name:wordsList)
//		{
//			System.out.println(name);
//		}
		List <Integer> wordsList = Arrays.asList(1,5,7,8,3);
//		Collections.sort(wordsList, Collections.reverseOrder());
		wordsList = wordsList.stream().sorted().collect(Collectors.toList());
		System.out.println("occuranceOfWords.main()"+wordsList);
//		Collections.reverse(wordsList);
//		int total=0;
//		for(int i=0; i<wordsList.size();i++)
//		{
//			total =wordsList.get(i);
//			
//		}
		
	}
}
