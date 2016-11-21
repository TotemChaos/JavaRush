package com.javarush.test.level19.lesson10.bonus02;

public class Solution {
    public static void main(String[] args)throws Exception {
        FileConsoleWriter console = new FileConsoleWriter(args[0]);

        console.write("Проверка String:");
        console.write(1234);
        console.write("Проверка char buff:".toCharArray());
        console.write("Проверка char buff:".toCharArray(), 3, 5);
        console.write("Проверка String обрезка:", 1, 6);

        console.close();
    }
}
