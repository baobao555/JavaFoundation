package baobao.stream;

import java.util.ArrayList;

public class DemoStream {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("包包,98");
		list.add("李工,95");
		list.add("刘工,87");
		
		list.stream().map(s -> s.split(",")[1]).map(s -> Integer.parseInt(s))
		.filter(i -> i > 90).forEach(System.out :: println);

		/*list.stream().map(s -> s.split(",")[1]).map(Integer::parseInt)
				.filter(i -> i > 90).forEach(System.out :: println);*/
	}
}
