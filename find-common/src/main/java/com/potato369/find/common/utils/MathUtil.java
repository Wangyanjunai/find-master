package com.potato369.find.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class MathUtil {

    private static final Double MONEY_RANG = 0.01;

    /**
     * <pre>
     * 比较两个金额是否相等
     * @param double1 金额1
     * @param double2 金额2
     * @return Boolean
     * </pre>
     */
    public static synchronized Boolean equals(Double double1, Double double2) {
        double result = Math.abs(double1 - double2);
        return result < MONEY_RANG;
    }

    /**
     * <pre>
     * 比较两个金额大小，如果double1 >= double2，返回true，否则返回false。
     * @param double1 金额1
     * @param double2 金额2
     * @return Boolean
     * </pre>
     */
    public static synchronized Boolean compareTo(Double double1, Double double2) {
        double result = Math.abs(double1 - double2);
        return result >= 0;
    }

    /**
     * <pre>
     * getRandom方法的作用：产生随机红包余额
     * @param rang 范围
     * @return
     * </pre>
     */
    public static synchronized Double getRandom(int rang) {
        return BigDecimal.valueOf((Math.random() * rang + 1) / 10).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * <pre>
     * 根据min和max随机生成一个范围在[min,max]的随机数，包括min和max
     * @param min 最小值
     * @param max 最大值
     * </pre>
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        // int i = random.nextInt(max - min + 1) + min;
        // System.out.println("i1=" + i);
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * <pre>
     * 根据min和max随机生成count个不重复的随机数组
     * @param min 最小值
     * @param max 最大值
     * @param count 数量
     * </pre>
     */
    public static int[] getRandoms(int min, int max, int count) {
        int[] randoms = new int[count];
        List<Integer> listRandom = new ArrayList<>();
        if (count > (max - min + 1)) {
            return null;
        }
        // 将所有的可能出现的数字放进候选list
        for (int i = min; i <= max; i++) {
            listRandom.add(i);
        }
        // 从候选list中取出放入数组，已经被选中的就从这个list中移除
        for (int i = 0; i < count; i++) {
            int index = getRandom(0, listRandom.size() - 1);
            randoms[i] = listRandom.get(index);
            listRandom.remove(index);
        }
        //遍历输出
//        for (int i : randoms) {
//            System.out.println("i2=" + i);
//        }
        return randoms;
    }

    /**
     * <pre>
     * 获取countIndex个1-bound之间的随机数，要求不能重复
     * @param countIndex 数量
     * @param bound 最大值
     * </pre>
     */
    public static void randoms(int countIndex, int bound) {
        //创建一个产生随机数的对象
        Random r = new Random();
        //创建一个存储随机数的集合
        Vector<Integer> v = new Vector<>();
        //定义一个统计变量
        int count = 0;
        while (count < countIndex) {
            int number = r.nextInt(bound) + 1;
            //判断number是否在集合中存在
            if (!v.contains(number)) {
                //不在集合中，就添加
                v.add(number);
                count++;
            }
        }
        //遍历输出
//        for (int i : v) {
//            System.out.println("i3=" + i);
//        }
    }

//    public static void main(String[] args) {
//        randoms(10, 20);
//        getRandoms(0, 132, 10);
//        getRandom(0, 132);
//    }
}
