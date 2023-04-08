package com.github.franklinthree.main.util;

public class Base62Encoder {
    private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE62 = 62;

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int remainder = (int) (num % BASE62);
            sb.append(BASE62_CHARS.charAt(remainder));
            num /= BASE62;
        }
        return sb.reverse().toString();
    }

    public static long decode(String str) {
        long num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE62 + BASE62_CHARS.indexOf(str.charAt(i));
        }
        return num;
    }
}
