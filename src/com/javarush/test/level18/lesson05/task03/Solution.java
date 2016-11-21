package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFile = reader.readLine();
        FileInputStream inputStream = new FileInputStream(inputFile);
        String outputFile1 = reader.readLine();
        FileOutputStream outputStream1 = new FileOutputStream(outputFile1);
        String outputFile2 = reader.readLine();
        FileOutputStream outputStream2 = new FileOutputStream(outputFile2);

		int count = inputStream.available() / 2;

		while(inputStream.available() > count) {
			outputStream1.write(inputStream.read());
		}
        while(inputStream.available() > 0) {
			outputStream2.write(inputStream.read());
		}

		reader.close();
		inputStream.close();
		outputStream1.close();
		outputStream2.close();
    }
}
