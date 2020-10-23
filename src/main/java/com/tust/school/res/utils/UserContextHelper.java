package com.tust.school.res.utils;

import com.tust.school.res.consts.UserConstants;

import java.util.Map;

public class UserContextHelper {

    private static ThreadLocal<Map<String, Object>> ownerThreadLocal = new ThreadLocal<>();

    public static void putUser(Map<String, Object> map) {
        ownerThreadLocal.set(map);
    }

    public static Integer getUserId() {
        Map<String, Object> userMap = ownerThreadLocal.get();

        return Integer.parseInt(userMap.get(UserConstants.USER_ID).toString());
    }

    public static String getName() {
        Map<String, Object> userMap = ownerThreadLocal.get();

        return userMap.get(UserConstants.NAME).toString();
    }

    public static Integer getAccountType() {
        Map<String, Object> userMap = ownerThreadLocal.get();

        return Integer.parseInt(userMap.get(UserConstants.ACCOUNT_TYPE).toString());
    }

    public static String getNo() {
        Map<String, Object> userMap = ownerThreadLocal.get();

        return userMap.get(UserConstants.NO).toString();
    }

}
