package baobao.jvm.bytecode;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 * @author baobao
 * @create 2020-04-14 21:00
 * @description
 */
public class ByteCodeTest4 {
    public void test() throws IOException,FileNotFoundException,NullPointerException{
        try {
            InputStream in = new FileInputStream("aaa");
            ServerSocket serverSocket = new ServerSocket(8888);
            serverSocket.accept();
        } catch (FileNotFoundException ex){

        } catch (IOException ex){

        } catch (Exception ex){

        } finally {
            System.out.println("finally");
        }
    }
}
