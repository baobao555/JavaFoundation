package designmode.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author baobao
 * @create 2020-03-01 20:37
 * @description 使用Cglib实现动态代理
 *
 * Cglib动态代理不要求被代理类实现接口，它是一种基于子类的代理，相当于动态生成的代理类是被代理类的
 * 子类，重写被代理类的方法来达到增强方法的目的。所以被代理类不能是final类型，否则无法生成代理的子类。
 * 另外被代理类中的final、static方法也不能被代理类增强。被代理类一定要有无参构造函数
 */
public class CglibProxy {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new Singer("Jay"));
        Singer singer = (Singer)proxyFactory.getProxyInstance();
        singer.sing();
    }

}

//使用Cglib代理要实现MethodInterceptor接口
class ProxyFactory implements MethodInterceptor{
    //被代理的对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //获取动态代理生成的代理对象
    public Object getProxyInstance(){
        //1、创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2、设置代理类的父类，即被代理类
        enhancer.setSuperclass(target.getClass());
        //3、设置调用代理类方法的回调，一般为this，因为MethodInterceptor是Callback的子接口
        enhancer.setCallback(this);
        //4、创建子类对象，即代理对象
        return enhancer.create();
    }

    //拦截被代理类的相应方法
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理");
        //调用被代理类的方法
        return method.invoke(target, args);
    }
}
