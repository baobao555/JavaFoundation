package baobao.java8.optional;

/**
 * @author baobao
 * @create 2020-03-11 19:52
 * @description 球员
 */
public class Player {
    private String name;

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String name) {
        this.name = name;
    }
}
