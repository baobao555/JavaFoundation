package designmode.facade;

/**
 * @author baobao
 * @create 2020-03-01 20:03
 * @description DVD播放器
 */
public class DVDPlayer {
    public void on(){
        System.out.println("打开DVD播放器");
    }

    public void off(){
        System.out.println("关闭DVD播放器");
    }

    public void play(){
        System.out.println("DVD播放影片");
    }
}
