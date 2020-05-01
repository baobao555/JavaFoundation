package baobao.jvm.memory;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author baobao
 * @create 2020-04-20 20:09
 * @description
 */
public class MetaspaceMemoryOut {
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MetaspaceMemoryOut.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor)(obj, method, args1, proxy) ->
                proxy.invokeSuper(obj, args1)
            );
            enhancer.create();
        }
    }
}
