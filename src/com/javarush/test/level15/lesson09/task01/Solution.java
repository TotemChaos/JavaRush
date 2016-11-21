package com.javarush.test.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static  {
        labels.put(2.3, "1");
        labels.put(2.9, "1f");
        labels.put(2.6, "fg1");
        labels.put(2.8, "1fgfd");
        labels.put(2.1, "d1");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
