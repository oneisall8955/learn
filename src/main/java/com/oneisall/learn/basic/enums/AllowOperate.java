package com.oneisall.learn.basic.enums;

import com.oneisall.learn.common.CommonResult;

/**
 * 整合状态,角色,操作权限
 *
 * @author : oneisall
 * @version : v1 2019/2/14 18:04
 * @param <KEY1> 第一个KEY,如角色
 * @param <KEY2> 第二个KEY,如操作
 */
public interface AllowOperate<KEY1,KEY2> {

    /**
     * 是否允许操作
     * @param key1 第一个KEY
     * @param key2 第二个KEY
     * @return 是否允许操作
     */
    CommonResult allowOperate(KEY1 key1, KEY2 key2);
}
