package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

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

        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String tmp;
        String fileContent = "";
        String newLine = System.getProperty("line.separator");

        if("-u".equals(args[0]))    {
            while((tmp = fileReader.readLine()) != null)    {
                if(tmp.substring(0, 8).trim().equals(args[1]))  {
                    fileContent = fileContent + String.format("%1$-8s%2$-30.30s%3$-8.8s%4$-4.4s", args[1], args[2], args[3], args[4]) + newLine;
                }else fileContent = fileContent + tmp + newLine;
            }
            fileReader.close();

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
            fileWriter.write(fileContent);
            fileWriter.close();
        }else if("-d".equals(args[0]))  {
            while((tmp = fileReader.readLine()) != null)
                if(!tmp.substring(0, 8).trim().equals(args[1]))
                    fileContent = fileContent + tmp + newLine;
            fileReader.close();

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
            fileWriter.write(fileContent);
            fileWriter.close();
        }
    }
}