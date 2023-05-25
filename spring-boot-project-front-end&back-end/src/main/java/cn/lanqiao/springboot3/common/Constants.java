package cn.lanqiao.springboot3.common;


/**
 * @author
 */

public class Constants {

    public static final int RESULT_CODE_SUCCESS = 200;  // 成功处理请求

    public static final int RESULT_CODE_BAD_REQUEST = 412;  // 请求错误
    public static final int RESULT_CODE_NOT_LOGIN = 402;  // 未登录
    public static final int RESULT_CODE_PARAM_ERROR = 406;  // 传参错误

    public static final int RESULT_CODE_SERVER_ERROR = 500;  // 服务器错误

    /*
    HTTP 状态码由三个十进制数字组成，第一个十进制数字定义了状态码的类型。
    响应分为五类：信息响应(100–199)，成功响应(200–299)，重定向(300–399)，客户端错误(400–499)和服务器错误 (500–599)：
    */

    public final static String FILE_UPLOAD_PATH = "D:\\upload\\";//上传文件的保存地址，根据部署设置自行修改
    //public final static String FILE_UPLOAD_PATH = "/home/project/upload/";//上传文件的保存地址，根据部署设置自行修改

}
