package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.sort;

/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inStream = new FileInputStream(reader.readLine());
        List<Integer> arrayByte = new ArrayList<Integer>();

        while(inStream.available() > 0)    {
            int data = inStream.read();
            if(!arrayByte.contains(data))    {
                arrayByte.add(data);
            }
        }

        sort(arrayByte);

        for (int x : arrayByte) {
            System.out.print(x + " ");
        }

        reader.close();
        inStream.close();
    }
}
