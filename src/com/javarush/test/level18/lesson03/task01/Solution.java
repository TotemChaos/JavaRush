package com.javarush.test.level18.lesson03.task01;

import java.io.*;
/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		FileInputStream inStream = new FileInputStream(reader.readLine());
		int result = 0;
		while(inStream.available() > 0)	{
			int data = inStream.read();
			if(result < data)	{ result = data; }
		}
		inStream.close();
		System.out.println(result);
	}
}
