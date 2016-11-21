package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		FileOutputStream outputFile1 = new FileOutputStream(reader.readLine());
		FileInputStream inputFile2 = new FileInputStream(reader.readLine());
		FileInputStream inputFile3 = new FileInputStream(reader.readLine());
		SequenceInputStream inputStream = new SequenceInputStream(inputFile2, inputFile3);

		while(inputFile3.available() > 0)	{
			outputFile1.write(inputStream.read());
		}

		reader.close();
		outputFile1.close();
		inputFile2.close();
		inputFile3.close();
		inputStream.close();
    }
}
