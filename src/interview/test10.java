package interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class test10 {

	public static void main(String[] args) {
		//"Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil"
		
		List<String> list1= Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
		Map<String, Integer> map = list1.parallelStream().collect(Collectors.toConcurrentMap(w -> w, w -> 1, Integer::sum));
		System.out.println(map);

		//1,0,0,0,0,1,0
		
		int list []  = {1,0,0,0,0,1,0};
		for(int i =0; i<list.length; i++)
		{
			if(list[i] < list[i-1])
			{
				list[i]= list[i] + list[i-1];
				list[i-1]= list[i] - list[i -1];
				list[i] = list[i] - list[i-1];
				i=0;
			}
			
		}
		System.out.println("Sorted array is :");
		for(int i=0; i<list.length;i++)
		{
			System.out.println(list[i]+ " ");
		}
//		List<Employee> allEmployees = ...
//		Map<String, Employee> topEmployees =
//			    allEmployees.stream()
//			                .collect(groupingBy(
//			                    e -> e.department,
//			                    collectingAndThen(maxBy(comparingInt(e -> e.salary)), Optional::get) 
//			                ));
		
	}

}
