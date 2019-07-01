package baobao.collection.enhancedfor;

import java.util.ArrayList;

public class DemoEnhancedFor {

	public static void main(String[] args) {
		int[] arr = {11,22,33,44,55};
		for(int i : arr) {
			System.out.println(i);//{11,22,33,44,55}
		}
		
		for(int i : arr) {
			i = 11;//i是局部变量，相当于将数组的值取出来赋给i，并不会改变原来数组里元素的值
			System.out.println(i);//{11,11,11,11,11}
		}
		
		for(int i : arr) {
			System.out.println(i);//{11,22,33,44,55}
		}
		
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		for (String string : list) {
			System.out.println(string);
		}

	}

}
