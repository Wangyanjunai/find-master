package com.potato369.find.message;

public class Test01 {

    public static String reverse(String str) {
        String regex = " ";
        String[] array = str.split(regex);
        StringBuffer sb1 = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            StringBuffer sb2 = new StringBuffer(array[i]);
            sb1.append(sb2.reverse().append(regex));
        }
        return sb1.toString();
    }

    public static String m(Double d) {
        return "Double";
    }

    public static String m(Integer d) {
        return "Integer";
    }

    public static void main(String[] args) {
        String a = reverse("Hello World!123");
        System.out.println(m(1));
    }
}
