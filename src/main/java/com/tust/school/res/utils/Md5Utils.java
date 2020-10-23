package com.tust.school.res.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;

@Slf4j
public class Md5Utils {

    public static final String md5Sign(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            String md5code = new BigInteger(1, md.digest()).toString(16);

            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }

            return md5code;
        } catch (Exception ex) {
            log.error("have no md5 MessageDigest.");
        }

        return "";
    }
}
