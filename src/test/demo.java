package test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class demo {

	public static void main(String[] args) 
	{ 
		Map<String, String> output = new HashMap<String, String>();
		List<Employee> map = new ArrayList<Employee>();
		
		Map<Employee, String> output1 = new HashMap<Employee, String>();
		
		Employee e1 = new Employee();
		e1.setEmpName("Dipesh");
		e1.setEmpAddress("Thanes");
		e1.setEmpId("101");
		map.add(e1);
		output1.put(e1, e1.getEmpAddress());
		System.out.println(output1.get(e1));
		Employee e2 = new Employee();
		e2.setEmpName("Dipesh");
		e2.setEmpAddress("Thane");
		e2.setEmpId("102");
		map.add(e2);
		output1.put(e2, e2.getEmpAddress());
		System.out.println(output1.get(e2));
		
		for(Employee emp :map){
			output.put(emp.getEmpId(), emp.getEmpName());
		}
		System.out.println(output);
		
		int[] a = {1,2,4,5};
		int b = a.length + 1;
		int c = b * (b+1)/2;
		for(int d:a)
		{
			c = c-d;
		}
		System.out.println(c);
		
		List<Integer> list= Arrays.asList(3,1,74,9,33);
		list = list.stream().sorted().collect(Collectors.toList());
		System.out.println(list);
		
		String  str = "124569";
		System.out.println(str.chars().mapToObj(s -> new Character((char) s)).collect(Collectors.toList()));
	} 
}
