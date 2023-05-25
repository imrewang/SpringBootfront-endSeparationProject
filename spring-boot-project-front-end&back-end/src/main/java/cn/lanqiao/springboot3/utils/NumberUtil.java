package cn.lanqiao.springboot3.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

    private NumberUtil() {
    }


    /**
     * 判断是否为11位电话号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        //Pattern正则表达式的编译表示。//将给定的正则表达式编译为模式。
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-8])|(18[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phone);//创建一个匹配此模式的给定输入的匹配器。
        return matcher.matches();//尝试将整个区域与模式匹配。
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length
     * @return
     */
    public static int genRandomNum(int length) {
        int num = 1;
        double random = Math.random();//大于或等于 0.0 且小于 1.0 的伪随机双精度数。
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 生成订单流水号
     *
     * @return
     */
    public static String genOrderNo() {
        //valueOf返回长参数的字符串表示形式。//StringBuffer构造一个字符串缓冲区，初始化为指定字符串的内容。
        StringBuffer buffer = new StringBuffer(String.valueOf(System.currentTimeMillis()));
        int num = genRandomNum(4);
        buffer.append(num);
        return buffer.toString();
    }

    public static String formatMoney2Str(Double money) {
        //DecimalFormat使用给定的模式和默认 FORMAT 语言环境的符号创建 DecimalFormat。
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        return df.format(money);
        //格式化对象以生成字符串。
    }

    public static String formatMoney2Str(float money) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        return df.format(money);
    }

}
