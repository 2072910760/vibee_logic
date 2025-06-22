package com.neuedu.xinxin.util;

import cn.hutool.core.util.ArrayUtil;

/**
 * 检查状态工具类
 * 如:订单状态、用户使用状态等
 */
public class CheckStateUtils {
    /***
     * 判断状态是否存在于某个范围, 不存在抛出异常
     * @param state
     * @param message
     * @param states
     */
    public  static void isExisted(int state, String message, int... states) {
        if (ArrayUtil.isEmpty(states)) {
            throw new IllegalArgumentException("未设置状态数组");
        }
        boolean existed = false;
        for (int s : states) {
            if (s == state) {
                existed = true;
                break;
            }
        }
        if (!existed) {
            throw new IllegalArgumentException(message);
        }
    }

    /***
     * 判断状态是否不存在于某个范围, 存在则抛出异常
     * @param state
     * @param message
     * @param states
     */
    public final static void isNotExisted(int state, String message, int... states) {
        if (ArrayUtil.isEmpty(states)) {
            throw new IllegalArgumentException("未设置状态数组");
        }
        boolean existed = false;
        for (int s : states) {
            if (s == state) {
                existed = true;
                break;
            }
        }
        if (existed) {
            throw new IllegalArgumentException(message);
        }

    }
}
