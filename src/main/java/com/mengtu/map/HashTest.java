package com.mengtu.map;

public class HashTest {
    public static void main(String[] args) {
        calcString();
    }

    private static void calcString() {
        String str = "jack"; //3254239
        int len = str.length();
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
//            hashCode = hashCode * 31 + c;
            hashCode = (hashCode << 5) - hashCode + c;
        }
        System.out.println(hashCode);
    }

    private static void calcHashCode() {
        Integer a = 110;
        Float b = 10.6f;
        Long c = 156L;
        Double d = 10.9;
        String e = "rose";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());
        System.out.println(e.hashCode());
    }
}
