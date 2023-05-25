package cn.lanqiao.springboot3.controller;

import cn.lanqiao.springboot3.common.Constants;
import cn.lanqiao.springboot3.common.Result;
import cn.lanqiao.springboot3.common.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/images")
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultGenerator.genFailResult("请选择文件");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();//构造一个没有字符且初始容量为 16 个字符的字符串生成器。
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        //sdf.format将 Date 格式化为日期时间字符串。//nextInt返回从该随机数生成器的序列中提取的伪随机、均匀分布的 int 值，介于 0（含）和指定值（不含）之间。
        String newFileName = tempName.toString();
        try {
            // 保存文件
            byte[] bytes = file.getBytes();
            //https://blog.csdn.net/qq_36314960/article/details/104775557
            // getBytes方法用来将文件转换成一种字节数组的方式进行传输，会抛出IOException异常。
            Path path = Paths.get(Constants.FILE_UPLOAD_PATH + newFileName);
            //将路径字符串或连接形成路径字符串的字符串序列转换为路径。
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = ResultGenerator.genSuccessResult();
        result.setData("files/" + newFileName);
        return result;
    }
}