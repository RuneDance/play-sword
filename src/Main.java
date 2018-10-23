import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // 创建ArrayList集合保存输入的用户名
        ArrayList<String> list = new ArrayList<>();

        // 向控制台输入玩家用户名
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********欢迎来玩穿剑小游戏*********");
        for (int i = 1; i <= 4; i++) {
            System.out.print("您是第" + i + "个玩家, 请输入您的用户名：");
            String name = scanner.next();
            if (list.contains(name)) {
                System.out.println("用户名已被占用！");
                System.out.println();
                System.out.println();
                System.out.println();
                i--;
                continue;
            }
            list.add(name);
        }


        Poker poker = new Poker();

        String user1 = list.get(0);
        String user2 = list.get(1);
        String user3 = list.get(2);
        String user4 = list.get(3);

        Player firstPlayer = new Player(user1);
        Player secondPlayer = new Player(user2);
        Player thirdPlayer = new Player(user3);
        Player fourthPlayer = new Player(user4);
        //初始化牌
        poker.assemble();
        //洗牌
        poker.washCards();
        //发牌
        poker.distributionCards(firstPlayer.getTreeSet(), secondPlayer.getTreeSet(), thirdPlayer.getTreeSet(), fourthPlayer.getTreeSet());

        //出牌
        for (int i = 1; i <= 4; i++) {
            //看牌
            if (i == 1) {
                poker.queryCards(firstPlayer);
            } else if (i == 2) {
                poker.queryCards(secondPlayer);
            } else if (i == 3) {
                poker.queryCards(thirdPlayer);
            } else if (i == 4) {
                poker.queryCards(fourthPlayer);
            }
            System.out.print("请" + list.get(i) + "出牌：");
            String card = scanner.next();
            poker.outCards(card);
        }

        //poker.test(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer);

    }
}
