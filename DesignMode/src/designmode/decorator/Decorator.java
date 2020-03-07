package designmode.decorator;

/**
 * @author baobao
 * @create 2020-02-27 21:38
 * @description
 */
public class Decorator extends Drink {
    private Drink coffee;

    public Decorator(String description, int cost,Drink coffee) {
        super(description, cost);
        this.coffee = coffee;
    }


    @Override
    public int cost() {
        return this.coffee.cost() + getCost();
    }
}
