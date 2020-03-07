package designmode.decorator;

/**
 * @author baobao
 * @create 2020-02-27 21:29
 * @description
 */
public abstract class Drink {
    private String description;
    private int cost;

    public abstract int cost();

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public Drink(String description, int cost) {
        this.description = description;
        this.cost = cost;
    }
}
