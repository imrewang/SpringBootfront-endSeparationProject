package cn.lanqiao.springboot3.utils;

import java.security.MessageDigest;

public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();//线程安全，可变的字符序列。
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            //此MessageDigest类为应用程序提供消息摘要算法的功能，例如SHA-1或SHA-256。
            //getInstance返回实现指定摘要算法的MessageDigest对象。
            if (charsetname == null || "".equals(charsetname)) {
                //digest使用指定的字节数组对摘要执行最终更新，然后完成摘要计算。
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
                //getBytes使用平台的默认字符集将此String编码为字节序列，将结果存储到新的字节数组中。

            } else {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
                //getBytes使用命名的字符集将此 String编码为字节序列，将结果存储到新的字节数组中。
            }
        } catch (Exception exception) {
            System.out.println("MD5Encode Exception");
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
