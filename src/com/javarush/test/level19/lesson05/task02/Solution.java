package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader readerFile =  new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        String tmp;
        int count = 0;
        Pattern p = Pattern.compile("^[Ww][Oo][Rr][Ll][Dd]$");
        Matcher m;

        while((tmp = readerFile.readLine()) != null)   {
            String[] line = tmp.split("[\\W || \\s]+");
            for(String s : line)
                if((m = p.matcher(s)).find())
                    count++;
        }
        readerFile.close();

        System.out.println(count);
    }
}
