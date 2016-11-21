package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream readerFile = new FileInputStream(reader.readLine());
        FileOutputStream writerFile = new FileOutputStream(reader.readLine());

        while(readerFile.available() > 0) {
            byte[] tmp = new byte[readerFile.available()];
            readerFile.read(tmp);

            for (int i = 0; i < tmp.length; i++)
                if (i % 2 != 0)
                    writerFile.write(tmp[i]);
        }

        reader.close();
        readerFile.close();
        writerFile.close();
    }
}
