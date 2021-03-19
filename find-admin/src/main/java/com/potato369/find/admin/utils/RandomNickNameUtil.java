package com.potato369.find.admin.utils;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * @PackageName com.potato369.find.user.utils
 * @ClassName RandomNickNameUtil
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/19 10:29
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public class RandomNickNameUtil {

    private List<String> list = new ArrayList<>();

    /**
     * <pre>
     * 获取随机行数
     * @param total 文件总行数
     * @return 整形参数
     * </pre>
     */
    public int getRandomNumber(int total) {
        return (int) (Math.random() * total);
    }

    /**
     * <pre>
     * 获取随机行数的字符串
     * @return
     * </pre>
     */
    public String getStringOfFile() {
        if (null != list) {
            int line = getRandomNumber(list.size());
            return list.get(line);
        }
        return null;
    }

    /**
     * <pre>
     * 从昵称库随机获取昵称
     * @return 昵称
     * </pre>
     */
    public synchronized String randomName() throws Exception {
        @Cleanup InputStream is = this.getClass().getResourceAsStream("/name.txt");
        ;
        @Cleanup BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String s;
        while ((s = br.readLine()) != null) {
            list.add(s);
        }
        return getStringOfFile();
    }
}
