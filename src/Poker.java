import java.util.*;

/**
 * 扑克类
 */
public class Poker {
    //花色
    private final String[] cardsFlags = {"♠", "♥", "♣", "◆"};
    //牌号
    private final String[] cardsNum = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};


    public Poker() {
    }

    //存入纸牌索引
    private List<Integer> list = new ArrayList<>();
    //存放纸牌
    private Map<Integer, String> map = new HashMap<>();
    //记录输赢顺序
    //private Set<Integer> set = new HashSet<>();

    /**
     * 拼装一副扑克牌
     */
    public void assemble() {
        //记录扑克牌的编号(张数)
        int index = 0;
        for (String nums : cardsNum) {
            for (String flags : cardsFlags) {
                //花色、牌号组成的字符串（每张牌）
                map.put(index, flags.concat(nums));
                //添加编号到List集合
                list.add(index);
                index++;
            }
        }
        //添加大王小王
        map.put(index, "SmallKing");
        list.add(index);
        index++;
        map.put(index, "BigKing");
        list.add(index);
    }

    /**
     * 洗牌
     */
    public void washCards() {
        Collections.shuffle(list);
    }

    /**
     * 发牌
     */
    public void distributionCards(TreeSet<Integer> firstPlayer, TreeSet<Integer> secondPlayer, TreeSet<Integer> thirdPlayer, TreeSet<Integer> fourthPlayer) {
        if (firstPlayer == null || secondPlayer == null || thirdPlayer == null || fourthPlayer == null) {
            System.out.println("用户不存在！");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (i % 4 == 0) {
                firstPlayer.add(list.get(i));
            } else if (i % 4 == 1) {
                secondPlayer.add(list.get(i));
            } else if (i % 4 == 2) {
                thirdPlayer.add(list.get(i));
            } else if (i % 4 == 3) {
                fourthPlayer.add(list.get(i));
            }
        }

        //this.queryRedPeach(firstPlayer,secondPlayer,thirdPlayer,fourthPlayer);
    }

    /**
     * 查询红桃4的玩家
     */
    public String queryRedPeach(Player player) {
        for (Iterator iter = player.getTreeSet().iterator(); iter.hasNext(); ) {
            if (map.get(iter.next()).equals("♥4")) {
                return player.getName();
            }
        }
        return null;
    }

    /**
     * 看牌
     *
     * @param player
     */
    public void queryCards(Player player) {
        System.out.println();
        System.out.println("===========================================");
        System.out.print(" ");
        for (Iterator iter = player.getTreeSet().iterator(); iter.hasNext(); ) {
            System.out.print(map.get(iter.next()).toString().concat("、"));
        }
        System.out.println();
        System.out.print(" ");
        for (Iterator iter = player.getTreeSet().iterator(); iter.hasNext(); ) {
            System.out.print(iter.next().toString().concat(" ").concat("、"));
        }
        System.out.println();
        System.out.println("===========================================");
    }


    /**
     * 出牌
     *
     * @param str
     * @return
     */
    public int outCards(String str) {
        Validator validator = new Validator();
        return validator.compareType(str);
    }

    /*public void test(Player firstPlayer, Player secondPlayer, Player thirdPlayer, Player fourthPlayer) {

        for (int i = 0; i < list.size(); i++) {
            System.out.print(map.get(i).toString().concat("——").concat(Integer.valueOf(i).toString()).concat("、"));
        }

        System.out.println();
        System.out.println("========================================================");
        System.out.println("========================================================");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(map.get(list.get(i)).toString().concat("——").concat(Integer.valueOf(list.get(i)).toString()).concat("、"));
        }


        System.out.println();
        System.out.println("========================================================");
        System.out.println("========================================================");
        for (Iterator iter = firstPlayer.getTreeSet().iterator(); iter.hasNext(); ) {
            System.out.print(map.get(iter.next()).toString().concat("、"));
        }
        System.out.println();
        System.out.println();
        for (Iterator iter = secondPlayer.getTreeSet().iterator(); iter.hasNext(); ) {
            System.out.print(map.get(iter.next()).toString().concat("、"));
        }
        System.out.println();
        System.out.println();
        for (Iterator iter = thirdPlayer.getTreeSet().iterator(); iter.hasNext(); ) {
            System.out.print(map.get(iter.next()).toString().concat("、"));
        }
        System.out.println();
        System.out.println();
        for (Iterator iter = fourthPlayer.getTreeSet().iterator(); iter.hasNext(); ) {
            System.out.print(map.get(iter.next()).toString().concat("、"));
        }
    }*/
}
