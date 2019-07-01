package baobao.generic;

interface Inter<T>{
	public abstract void show(T t);
}


class InterfaceGeneric implements Inter<String> {

	@Override
	public void show(String t) {
		// TODO Auto-generated method stub
		
	}
	
	
}


class InterfaceGeneric1<T> implements Inter<T> {


	@Override
	public void show(T t) {
		// TODO Auto-generated method stub
		
	}
	
	
}


