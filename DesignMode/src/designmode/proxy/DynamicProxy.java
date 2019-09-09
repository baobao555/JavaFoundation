package designmode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author baobao
 * @date 2019/7/18 0018 10:07
 * @description 动态代理
 */
public class DynamicProxy {
    public static void main(String[] args) {
        //保存动态代理所生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //创建被代理类
        Singer singer = new Singer("Jay");
        //创建InvocationHandler实例，传入被代理类的实例
        Handler handler = new Handler(singer);
        //动态生成代理类
        Object proxyInstance = Proxy.newProxyInstance(singer.getClass().getClassLoader(), singer.getClass().getInterfaces(),handler);
        //调用代理类的相关方法，实际会调用到InvocationHandler中的invoke方法
        ((SingSong) proxyInstance).sing();
    }
}

//InvocationHandler的实现类
class Handler implements InvocationHandler{

    //需持有被代理类的引用
    private Singer singer;

    public Handler(Singer singer) {
        this.singer = singer;
    }

    //调用代理类的方法实际会调invoke
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke");
        //判断调用的方法名
        if (method.getName().equals("sing")){
            //做代理工作，然后调用被代理类的方法实现功能
            System.out.println("安排日程");
            System.out.println("收费");
            method.invoke(singer, args);
        }
        return null;
    }
}


