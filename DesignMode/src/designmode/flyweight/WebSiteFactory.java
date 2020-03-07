package designmode.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baobao
 * @create 2020-03-01 20:21
 * @description 网站工厂
 */
public class WebSiteFactory {
    //网站池
    private Map<String,WebSite> webSiteMap = new HashMap<>();

    //根据类型获取网站
    public WebSite getWebSite(String type){
        //如果池中已经有相同类型的网站，直接返回池中的那个。否则new一个放到池中
        if (!webSiteMap.containsKey(type)){
            webSiteMap.put(type, new ConcreteWebSite(type));
        }
        return webSiteMap.get(type);
    }
}
