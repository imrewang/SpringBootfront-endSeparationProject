package cn.lanqiao.springboot3.service;

import cn.lanqiao.springboot3.entity.AdminUser;
import cn.lanqiao.springboot3.utils.PageResult;
import cn.lanqiao.springboot3.utils.PageUtil;

/**
 * @author 13
 * @qq交流群 784785001
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */

public interface AdminUserService {

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getAdminUserPage(PageUtil pageUtil);

    /**
     * 登陆功能
     *
     * @return
     */
    AdminUser updateTokenAndLogin(String userName, String password);

    /**
     * 根据id获取用户记录
     *
     * @return
     */
    AdminUser selectById(Long id);

    /**
     * 根据用户名获取用户记录
     *
     * @return
     */
    AdminUser selectByUserName(String userName);

    /**
     * 新增用户记录
     *
     * @return
     */
    int save(AdminUser user);

    /**
     * 修改密码
     *
     * @return
     */
    int updatePassword(AdminUser user);

    /**
     * 批量删除功能
     *
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);

    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    AdminUser getAdminUserByToken(String userToken);
}
