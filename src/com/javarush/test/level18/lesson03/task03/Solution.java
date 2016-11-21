package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inStream = new FileInputStream(reader.readLine());
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        int valueMain = 0;

        while(inStream.available() > 0)    {
            int data = inStream.read();
            if(map.containsKey(data))    {
                map.put(data, map.get(data).intValue() + 1);
            } else map.put(data, 1);
        }
        reader.close();
        inStream.close();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(valueMain < entry.getValue()) { valueMain = entry.getValue(); }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(valueMain == entry.getValue()) {
                System.out.print(entry.getKey() + " ");
            }
        }

    }
}
