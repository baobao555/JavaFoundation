package baobao.changeableparam;

public class DemoChangeableParam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {11,22,33,44,55};
		print(arr);
		print(1,2,3);
		print();
		print2(1,2,3);
	}
	
	public static void print(int ... arr) {  //可变参数底层是数组实现
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	public static void print2(int x,int ... arr) {  //可变参数底层是数组实现
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
