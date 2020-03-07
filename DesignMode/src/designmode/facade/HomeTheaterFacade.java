package designmode.facade;

/**
 * @author baobao
 * @create 2020-03-01 20:03
 * @description 家庭影院
 */
public class HomeTheaterFacade {
    private Light light;
    private DVDPlayer dvdPlayer;

    public HomeTheaterFacade(Light light, DVDPlayer dvdPlayer) {
        this.light = light;
        this.dvdPlayer = dvdPlayer;
    }

    //将开灯、开DVD等一系列操作封装，客户端只需调用家庭影院类即可，无需关心具体细节
    public void start(){
        light.on();
        dvdPlayer.on();
        dvdPlayer.play();
    }

    public void stop(){
        dvdPlayer.off();
        light.off();
    }
}
