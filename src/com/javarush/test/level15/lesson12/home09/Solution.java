package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader readerd = new BufferedReader(new InputStreamReader(System.in));
        URL url = new URL(readerd.readLine());
        List<String> urlArray = new ArrayList<String>();

        String[] obj = url.getQuery().split("&");
        for (int i = 0; i < obj.length; i++)    {
            if(obj[i].contains("="))    {
                urlArray.add((obj[i].substring(0,(obj[i].indexOf("=")))).replaceAll("\\?", ""));
            }else if(!obj[i].isEmpty()) urlArray.add(obj[i].replaceAll("\\?", ""));
        }

        for(int i = 0; i < urlArray.size(); i++) {
            if(i == urlArray.size() - 1)  {
                System.out.print(urlArray.get(i));
            }else System.out.print(urlArray.get(i) + " ");
        }

        System.out.println();

        for (int i = 0; i < obj.length; i++)    {
            if(obj[i].startsWith("obj=") & obj[i].length() > 4)    {
                if(Character.isDigit((obj[i].substring(obj[i].indexOf("=") + 1)).charAt(0)))    {
                    alert(Double.parseDouble(obj[i].substring(obj[i].indexOf("=") + 1, obj[i].length())));
                }else alert(obj[i].substring(obj[i].indexOf("=") + 1, obj[i].length()));
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
