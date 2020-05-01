package baobao.java8.optional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author baobao
 * @create 2020-03-11 19:51
 * @description 演示获得bean中的集合并判空
 */
public class OptionalDemo2 {
    public static void main(String[] args) {
        //创建2个Team对象
        Team team1 = new Team("Warriors");
        Team team2 = new Team("Warriors");
        //team1的players集合设置为null
        team1.setPlayers(null);
        //team2的players集合设置为有意义的值
        team2.setPlayers(Arrays.asList(new Player("curry"),new Player("thompson")));
        //利用Optional包装team1和team2
        Optional<Team> optional1 = Optional.ofNullable(team1);
        Optional<Team> optional2 = Optional.ofNullable(team2);

        //利用包装了team的Optional，先利用map方法将team的Optional映射成包装了players
        // 的Optional，再调用包装了players的Optional的orElse方法传入一个空的集合，这样
        //如果players为空，就会返回一个空的集合而不是null了
        Optional<List<Player>> players = optional1.map(warriorTeam -> warriorTeam.getPlayers());
        List<Player> players1 = optional1.map(warriorTeam -> warriorTeam.getPlayers()).orElse(Collections.emptyList());
        List<Player> players2 = optional2.map(warriorTeam -> warriorTeam.getPlayers()).orElse(Collections.emptyList());

        System.out.println(players1);
        System.out.println(players2);
    }
}
