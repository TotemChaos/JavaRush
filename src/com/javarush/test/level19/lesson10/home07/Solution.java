package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args)throws Exception {
        BufferedReader readFile = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writeFile = new BufferedWriter(new FileWriter(args[1]));
        StringBuilder sb = new StringBuilder();

        while(readFile.ready()) {
            String[] tmp = readFile.readLine().split("\\s+");

            for(String s : tmp)
                if(s.length() > 6)
                    sb.append(s + ",");
        }

        writeFile.write(sb.deleteCharAt(sb.length() - 1).toString());

        readFile.close();
        writeFile.close();
    }
}
