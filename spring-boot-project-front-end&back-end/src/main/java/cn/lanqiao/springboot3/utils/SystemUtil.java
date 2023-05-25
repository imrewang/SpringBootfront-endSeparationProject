package cn.lanqiao.springboot3.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SystemUtil {

    private SystemUtil(){}


    /**
     * 登录或注册成功后,生成保持用户登录状态会话token值
     * @param src:为用户最新一次登录时的now()+user.id+random(4)
     * @return
     */
    public static String genToken(String src){
        if (null == src || "".equals(src)){
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());//使用指定的字节更新摘要。
            //不可变的任意精度整数。//将BigInteger的符号幅度表示转换为BigInteger。大端
            return new BigInteger(1, md.digest()).toString(16);
            //digest通过执行填充等最终操作来完成哈希计算。
            //符号表示为整数符号值：-1 表示负数，0 表示零，或 1 表示正数。
        } catch (Exception e) {
            return null;
        }
    }

}
