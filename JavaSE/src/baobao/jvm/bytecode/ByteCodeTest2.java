package baobao.jvm.bytecode;

/**
 * @author baobao
 * @create 2020-04-14 20:15
 * @description
 */
public class ByteCodeTest2 {
    public synchronized void syn1(){
        System.out.println("syn1");
    }

    public void syn2(){
        synchronized (this){
            System.out.println("syn2");
        }
    }
}
