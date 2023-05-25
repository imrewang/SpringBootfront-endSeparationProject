package cn.lanqiao.springboot3.controller;

import cn.lanqiao.springboot3.common.Constants;
import cn.lanqiao.springboot3.common.Result;
import cn.lanqiao.springboot3.common.ResultGenerator;
import cn.lanqiao.springboot3.config.annotation.TokenToUser;
import cn.lanqiao.springboot3.entity.AdminUser;
import cn.lanqiao.springboot3.service.AdminUserService;
import cn.lanqiao.springboot3.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController//@RestController 如果只是接口，那么就用 RestController 来注解.
// 用于类上：表示类中的所有响应请求的方法都是以该地址作为父路径
@RequestMapping("/users")//用于将任意HTTP 请求映射到控制器方法上。
public class AdminUserController {

    @Autowired//@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
    private AdminUserService adminUserService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    //指定请求的method类型， GET、POST、PUT、DELETE等；
    //RequestMapping 是一个用来处理请求地址映射的注解，可用于类或方法上。
    public Result list(@RequestParam Map<String, Object> params) {
        //@RequestParam简单一点说作用就是把请求中的指定名称的参数传递给控制器中的形参赋值
        //if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            //用于检查传递给方法的参数是否包含名为"page"和"limit"的键，并且这些值不能为空
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //查询列表数据
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(adminUserService.getAdminUserPage(pageUtil));
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody AdminUser user) {
        // @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
        Result result = ResultGenerator.genFailResult("登录失败");
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            result.setMessage("请填写登录信息！");
        }
        AdminUser loginUser = adminUserService.updateTokenAndLogin(user.getUserName(), user.getPassword());
        if (loginUser != null) {
            result = ResultGenerator.genSuccessResult(loginUser);
        }
        return result;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody AdminUser user, @TokenToUser AdminUser loginUser) {
        //当使用该注解时，它会告诉Java应用程序，只有在用户提供有效的令牌（token）时才能访问该方法或类。
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }

        AdminUser tempUser = adminUserService.selectByUserName(user.getUserName());

        if (tempUser != null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "用户已存在勿重复添加！");
        }
        if ("admin".endsWith(user.getUserName().trim())) {
            //trim返回一个字符串，其值为此字符串，删除了所有前导和尾随空格，其中space被定义为其代码点小于或等于 'U+0020' （空格字符）的任何字符。
            //测试此字符串是否以指定的后缀结尾。
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "不能添加admin用户！");
        }

        if (adminUserService.save(user) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public Result update(@RequestBody AdminUser user, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请输入密码！");
        }

        AdminUser tempUser = adminUserService.selectById(user.getId());

        if (tempUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此用户！");
        }
        if ("admin".endsWith(tempUser.getUserName().trim())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "不能修改admin用户！");
        }

        tempUser.setPassword(user.getPassword());
        if (adminUserService.updatePassword(user) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result delete(@RequestBody Integer[] ids, @TokenToUser AdminUser loginUser) {
        //@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
        //對有帶有@TokenToUser 註解的表示需要先登錄，才能正常進行
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        if (ids.length < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }

        if (adminUserService.deleteBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}