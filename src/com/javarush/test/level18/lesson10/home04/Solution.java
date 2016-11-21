package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String first = reader.readLine();
		String second = reader.readLine();
		FileInputStream inStream1 = new FileInputStream(first);
		FileInputStream inStream2 = new FileInputStream(second);
		byte fIn[] = new byte[inStream1.available()];
		byte sIn[] = new byte[inStream2.available()];
		inStream1.read(fIn);
		inStream2.read(sIn);
		FileOutputStream outStream1 = new FileOutputStream(first, false);
		outStream1.write(sIn);
		FileOutputStream outStream2 = new FileOutputStream(first, true);
		outStream2.write(fIn);

		reader.close();
		inStream1.close();
		inStream2.close();
		outStream1.close();
		outStream2.close();
	}
}
