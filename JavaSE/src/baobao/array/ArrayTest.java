package baobao.array;

public class ArrayTest {

	public static void main(String[] args) {
		//一维数组

		//1、动态初始化
		int[] a = new int[5];//基本类型默认初始化为0
		String[] s =new String[5];//引用类型默认初始化为null
		for(int i : a) {
			System.out.println(i);//5个0
		}
		
		for(String ss : s) {
			System.out.println(ss);//5个null
		}
		
		for(int i=0;i<a.length;i++) {
			a[i] = i;
			System.out.println(a[i]);//{0,1,2,3,4}
		}
		
		//2、静态初始化
		int[] b = new int[]{1,2,3};
		//int[] b = new int[3]{1,2,3}; //err
		int[] c = {1,2,3};
		
		//二维数组
		//1、动态初始化
		int[][] ii = new int[3][5];
		int[][] jj = new int[3][];
		jj[0] = new int[5];
		jj[1] = new int[4];
		jj[2] = new int[3];
		//int[][] kk = new int[][5];//err
		
		//2、静态初始化
		int[][] xx = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
		int[][] yy = {{1,2,3},{4,5,6},{7,8,9}};

	}
	
}
