package com.disaster.example.leftrotationstring;

import java.util.Stack;

/*
字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab";
 */
public class Client {
    public static void main(String[] args) {
        System.out.println(rotationString("abcdefg", 10));
        System.out.println(rotationString1("asdff", 10));
    }
    public static String rotationString(String srcStr,int n){
        StringBuilder sb = new StringBuilder();
        if (n==0){
            return srcStr;
        }
        if (n>=srcStr.length()){
            char[] chars = srcStr.toCharArray();
            for (int i = srcStr.length()-1; i >=0; i--) {
                sb.append(chars[i]);
            }
            return sb.toString();
        }
        String s = srcStr.substring(n);
        String s1 = srcStr.substring(0, n);
        sb.append(s);
        sb.append(s1);
        return sb.toString();
    }
    public static String rotationString1(String srcStr,int n) {
        return srcStr.substring(n, srcStr.length()) + srcStr.substring(0, n);
    }
}
