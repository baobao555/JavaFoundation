package designmode.facade;

/**
 * @author baobao
 * @create 2020-03-01 20:08
 * @description 外观模式
 *
 * 外观模式将为了达到一个结果的一系列操作统一封装到一个外观类中，客户端只需调用外观类的对应方法
 * 达到效果，无需关心具体复杂的实现步骤和细节
 *
 * 比如：家里要看电影先开灯，再开DVD播放器，最后播放影片。可以将这一系列动作封装到家庭影院外观类中的start方法，
 * start方法调用相应的灯和DVD播放器完成影院开启，客户端只需要找家庭影院外观类，无需直接操作灯和DVD播放器
 *
 * 外观模式的基本要素：
 * 1、外观类：对外提供统一的调用接口，完成一些列操作，达到一个目的
 * 2、调用者Client
 * 3、子系统的集合：被外观类所聚合，处理外观类指派的任务，是功能的具体实现者
 */
public class Client {
    public static void main(String[] args) {
        //创建家庭影院，将灯、DVD播放器聚合进去
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(new Light(), new DVDPlayer());
        //开启家庭影院模式播放影片
        homeTheaterFacade.start();
        //关闭家庭影院模式
        homeTheaterFacade.stop();
    }
}
