package com.disaster.example.replacestring;

/*
请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class Client {
    public static void main(String[] args) {
        System.out.println(replaceSpace("We are happy."));
    }
    public static String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            if (aChar == ' '){
                sb.append("%20");
            }else{
                sb.append(aChar);
            }
        }
        return sb.toString();
    }
}
