package interview;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class test4 {

	public static void main(String[] args) {
		String input="I felt happy because I saw the others were happy and because I knew I should feel happy, but I wasn’t really happy.";

		Map<String, Long> outPut= Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(outPut);
	}

}
