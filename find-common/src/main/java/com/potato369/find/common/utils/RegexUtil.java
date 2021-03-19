package com.potato369.find.common.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <pre>
 * @PackageName com.potato369.common.utils
 * @ClassName RegexUtil
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/19 10:04
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public class RegexUtil {

    static String phoneRegex = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";

    static String ipRegex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    
    public static boolean isMathPhone(String phone) {
        if (StrUtil.isEmpty(phone) && phone.length() != 11) {
            return false;
        }
        return ReUtil.isMatch(phoneRegex, phone);
    }
    
    public static boolean isMathIp(String ip) {
        if (StrUtil.isEmpty(ip)) {
            return false;
        }
        return ReUtil.isMatch(ipRegex, ip);
    }
}
