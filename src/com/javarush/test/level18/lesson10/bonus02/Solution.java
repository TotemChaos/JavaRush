package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        BufferedReader readerFile = new BufferedReader(new FileReader(file));
        String tmp;
        int id = 0;

        while((tmp = readerFile.readLine()) != null)
             if(Integer.parseInt(tmp.substring(0, 8).trim()) > id)
                 id = Integer.parseInt(tmp.substring(0, 8).trim());
        readerFile.close();

        BufferedWriter writerFile = new BufferedWriter(new FileWriter(file, true));
        writerFile.write(String.format("%1$-8s%2$-30s%3$-8s%4$-4s", ++id, args[1], args[2], args[3]));
        writerFile.close();
    }
}