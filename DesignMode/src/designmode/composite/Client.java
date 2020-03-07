package designmode.composite;

/**
 * @author baobao
 * @create 2020-02-27 22:09
 * @description 组合模式
 *
 * * 组合模式用于处理的对象具有树形结构，需要遍历组织结构时。
 *  * 组合模式要点：
 *  * 1、声明抽象的组件
 *  * 2、具体的节点都继承组件，具体节点包含非叶子节点和叶子节点，非叶子节点可以聚合其他节点，并且要实现组件的方法，
 *  * 能添加和删除聚合进来的其他节点对象。叶子节点不再聚合其他节点，无需实现顶层抽象组件的方法。
 *  *
 *  * 举例：
 *  * 学校、学院和专业的关系不是互相继承的关系，而是学校包含学院，学院包含专业。
 *  * 这种情况下，学校是树的第一层，学院是第二层，专业是最底层。学校和学院是非叶子节点，专业是叶子节点
 */
public class Client {
    public static void main(String[] args) {
        University university = new University("浙江工业大学");
        College engineer = new College("信息工程学院");
        engineer.add(new Major("通信工程"));
        engineer.add(new Major("电子科学与技术"));
        engineer.add(new Major("自动化"));

        College chemical = new College("化学工程与材料学院");
        chemical.add(new Major("化学工程与工艺"));
        chemical.add(new Major("海洋工程"));
        chemical.add(new Major("材料工程"));

        university.add(engineer);
        university.add(chemical);
        university.print();
    }
}
