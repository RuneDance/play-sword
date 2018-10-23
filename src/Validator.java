import java.util.Arrays;

/**
 * 校验类
 * 出牌规则为：X,X,X,X,X,X,X,X
 */
public class Validator {
    //上一个玩家出的牌
    String oldNum = "";

    /**
     * 比较出牌类型是否一致
     * -1:不要，0:出的牌有问题，1:要住了
     *
     * @param str
     * @return
     */
    public int compareType(String str) {
        if (str == null && str.length() < 0 && str.trim().length() < 0) {
            System.out.println("请先出牌！");
            return 0;
        }
        if (oldNum != "") {
            if (str.equals("no")) {
                return -1;
            } else if (!(str.matches("[0-9]+"))) {
                return 0;
            }
            String[] newNums = str.split(",");
            String[] oldNums = oldNum.split(",");
            //排序
            Arrays.sort(newNums);
            //有问题
            if (newNums.length != oldNums.length) {
                return 0;
            }
            for (int i = 0; i < newNums.length; i++) {
                for (int j = 0; j < oldNums.length; j++) {
                    if (Integer.parseInt(newNums[i]) > Integer.parseInt(oldNums[j])) {
                        return 1;
                    }
                    return 0;
                }
            }

            oldNum = str;
            return 1;
        }

        oldNum = str;
        return 1;

    }

    //剑
    public int sword(String str) {
        if (!str.equals("4,4,14")) {
            return 1;
        }
        return 0;
    }

    //单张
    public int compareSingle(String oldNum, String str) {
        if (this.compareType(str) == 1) {
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return 1;
            }
        }
        return 0;
    }

    //单顺子
    public int compareSingleOrder(String oldNum, String str) {
        String[] oldNums = oldNum.split(",");
        String[] newNums = str.split(",");

        if (this.compareType(str) == 1) {
            //最少三张连
            if (!(oldNums.length >= 3)) {
                return 0;
            }

            //最少三张连
            if (!(newNums.length >= 3)) {
                return 0;
            }

            //排除 2-3-SmallKing-BigKing
            for (int i = 0; i < oldNums.length; i++) {
                String num = oldNums[i];
                if (num.equals("15") || num.equals("16") || num.equals("52") || num.equals("53")) {
                    return 0;
                }
            }

            //排除 2-3-SmallKing-BigKing
            for (int i = 0; i < newNums.length; i++) {
                String num = newNums[i];
                if (num.equals("15") || num.equals("16") || num.equals("52") || num.equals("53")) {
                    return 0;
                }
            }
            //比较大小
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return 1;
            }
        }
        return 0;
    }

    //双顺子
    public int compareDoubleOrder(String oldNum, String str) {
        String[] oldNums = oldNum.split(",");
        String[] newNums = str.split(",");

        if (this.compareType(str) == 1) {

            //最少四张连
            if (!(oldNums.length % 4 == 0)) {
                return 0;
            }

            //最少四张连
            if (!(newNums.length % 4 == 0)) {
                return 0;
            }

            //排除AA22
            if (oldNum.equals("14,14,15,15") || str.equals("14,14,15,15")) {
                return 0;
            }
            //排除3344
            if (oldNum.equals("4,4,16,16") || str.equals("4,4,16,16")) {
                return 0;
            }
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return 1;
            }
        }
        return 0;
    }

    //三顺子
    public int compareTripleOrder(String oldNum, String str) {
        String[] oldNums = oldNum.split(",");
        String[] newNums = str.split(",");

        if (this.compareType(str) == 1) {

            //最少三张连
            if (!(oldNums.length % 3 == 0)) {
                return 0;
            }

            //最少三张连
            if (!(newNums.length % 3 == 0)) {
                return 0;
            }

            //排除AAA222
            if (oldNum.equals("14,14,14,15,15,15") || str.equals("14,14,14,15,15,15")) {
                return 0;
            }
            //排除333444
            if (oldNum.equals("4,4,4,16,16,16") || str.equals("4,4,4,16,16,16")) {
                return 0;
            }
            //对特殊的QQQ判断
            if (str.equals("12,12,12") && !(oldNum.equals("4,4,14"))) {
                return 1;
            }
            //对特殊的666判断
            if (str.equals("6,6,6") && !(oldNum.equals("4,4,14")) && !(oldNum.equals("12,12,12"))) {
                return 1;
            }
            //排除（驴）要住三个连顺子
            if (oldNums.length != 3 && newNums.length == 3) {
                return 1;
            }

            //开始比较
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return 1;
            }
        }
        return 0;
    }

    //四顺子
    public int compareQuadrupleOrder(String oldNum, String str) {
        String[] oldNums = oldNum.split(",");
        String[] newNums = str.split(",");

        if (this.compareType(str) == 1) {

            //最少四张连
            if (!(oldNums.length % 4 == 0)) {
                return 0;
            }

            //最少四张连
            if (!(newNums.length % 4 == 0)) {
                return 0;
            }

            //排除AAAA2222
            if (oldNum.equals("14,14,14,14,15,15,15,15") || str.equals("14,14,14,14,15,15,15,15")) {
                return 0;
            }
            //排除33334444
            if (oldNum.equals("4,4,4,4,16,16,16,16") || str.equals("4,4,4,4,16,16,16,16")) {
                return 0;
            }
            //对特殊的44A QQQ 666判断
            if (!(oldNum.equals("4,4,14")) && oldNum.equals("12,12,12") && !(oldNum.equals("6,6,6"))) {
                return 1;
            }
            //排除（炸弹）要住四个连顺子
            if (oldNums.length != 4 && newNums.length == 4) {
                return 1;
            }

            //开始比较
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return 1;
            }
        }
        return 0;
    }
}
