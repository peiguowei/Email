package com.demo.email.controller;

import java.util.Arrays;

/**
 *
 */
public class Test1 {
    public static void main(String[] args) {
        String s="123,345";
        String[] split = s.split(",");
        for (String s1:split){
            System.out.println(s1);
        }
        System.out.println(Arrays.toString(split));
    }
}
