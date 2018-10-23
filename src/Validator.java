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
     *
     * @param str
     * @return
     */
    public boolean compareType(String str) {
        if (str == null && str.length() < 0 && str.trim().length() < 0) {
            System.out.println("请先出牌！");
        }
        if (oldNum != "") {
            String[] newNums = str.split(",");
            String[] oldNums = oldNum.split(",");
            Arrays.sort(newNums);
            //有问题
            if (newNums.length != oldNums.length) {
                return false;
            }
            for (int i = 0; i < newNums.length; i++) {
                for (int j = 0; j < oldNums.length; j++) {
                    if (Integer.parseInt(newNums[i]) > Integer.parseInt(oldNums[j])) {
                        return true;
                    }
                    return false;
                }
            }

            oldNum = str;
            return true;
        }

        oldNum = str;
        return true;

    }

    //剑
    public boolean sword(String str) {
        if (!str.equals("4,4,14")) {
            return true;
        }
        return false;
    }

    //单张
    public boolean compareSingle(String oldNum, String str) {
        if (this.compareType(str)) {
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return true;
            }
        }
        return false;
    }

    //单顺子
    public boolean compareSingleOrder(String oldNum, String str) {
        String[] oldNums = oldNum.split(",");
        String[] newNums = str.split(",");

        if (this.compareType(str)) {
            //最少三张连
            if (!(oldNums.length >= 3)) {
                return false;
            }

            //最少三张连
            if (!(newNums.length >= 3)) {
                return false;
            }

            //排除 2-3-SmallKing-BigKing
            for (int i = 0; i < oldNums.length; i++) {
                String num = oldNums[i];
                if (num.equals("15") || num.equals("16") || num.equals("52") || num.equals("53")) {
                    return false;
                }
            }

            //排除 2-3-SmallKing-BigKing
            for (int i = 0; i < newNums.length; i++) {
                String num = newNums[i];
                if (num.equals("15") || num.equals("16") || num.equals("52") || num.equals("53")) {
                    return false;
                }
            }
            //比较大小
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return true;
            }
        }
        return false;
    }

    //双顺子
    public boolean compareDoubleOrder(String oldNum, String str) {
        String[] oldNums = oldNum.split(",");
        String[] newNums = str.split(",");

        if (this.compareType(str)) {

            //最少四张连
            if (!(oldNums.length % 4 == 0)) {
                return false;
            }

            //最少四张连
            if (!(newNums.length % 4 == 0)) {
                return false;
            }

            //排除AA22
            if (oldNum.equals("14,14,15,15") || str.equals("14,14,15,15")) {
                return false;
            }
            //排除3344
            if (oldNum.equals("4,4,16,16") || str.equals("4,4,16,16")) {
                return false;
            }
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return true;
            }
        }
        return false;
    }

    //三顺子
    public boolean compareTripleOrder(String oldNum, String str) {
        String[] oldNums = oldNum.split(",");
        String[] newNums = str.split(",");

        if (this.compareType(str)) {

            //最少三张连
            if (!(oldNums.length % 3 == 0)) {
                return false;
            }

            //最少三张连
            if (!(newNums.length % 3 == 0)) {
                return false;
            }

            //排除AAA222
            if (oldNum.equals("14,14,14,15,15,15") || str.equals("14,14,14,15,15,15")) {
                return false;
            }
            //排除333444
            if (oldNum.equals("4,4,4,16,16,16") || str.equals("4,4,4,16,16,16")) {
                return false;
            }
            //对特殊的QQQ判断
            if (str.equals("12,12,12") && !(oldNum.equals("4,4,14"))) {
                return true;
            }
            //对特殊的666判断
            if (str.equals("6,6,6") && !(oldNum.equals("4,4,14")) && !(oldNum.equals("12,12,12"))) {
                return true;
            }
            //排除（驴）要住三个连顺子
            if (oldNums.length != 3 && newNums.length == 3) {
                return true;
            }

            //开始比较
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return true;
            }
        }
        return false;
    }

    //四顺子
    public boolean compareQuadrupleOrder(String oldNum, String str) {
        String[] oldNums = oldNum.split(",");
        String[] newNums = str.split(",");

        if (this.compareType(str)) {

            //最少四张连
            if (!(oldNums.length % 4 == 0)) {
                return false;
            }

            //最少四张连
            if (!(newNums.length % 4 == 0)) {
                return false;
            }

            //排除AAAA2222
            if (oldNum.equals("14,14,14,14,15,15,15,15") || str.equals("14,14,14,14,15,15,15,15")) {
                return false;
            }
            //排除33334444
            if (oldNum.equals("4,4,4,4,16,16,16,16") || str.equals("4,4,4,4,16,16,16,16")) {
                return false;
            }
            //对特殊的44A QQQ 666判断
            if (!(oldNum.equals("4,4,14")) && oldNum.equals("12,12,12") && !(oldNum.equals("6,6,6"))) {
                return true;
            }
            //排除（炸弹）要住四个连顺子
            if (oldNums.length != 4 && newNums.length == 4) {
                return true;
            }

            //开始比较
            if (Integer.parseInt(str) > Integer.parseInt(oldNum)) {
                return true;
            }
        }
        return false;
    }
}
