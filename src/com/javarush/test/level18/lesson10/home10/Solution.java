package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static void main(String[] args)	throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<String> fileNameArray = new ArrayList<String>();
		String fileName;
		String mainFile;

		while(!(fileName = reader.readLine()).equals("end"))	{
			fileNameArray.add(fileName);
		}
		reader.close();

		mainFile = fileNameArray.get(0).substring(0, fileNameArray.get(0).lastIndexOf("."));

		Collections.sort(fileNameArray, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int first = Integer.parseInt(o1.substring(o1.lastIndexOf(".") + 5));
				int second = Integer.parseInt(o2.substring(o2.lastIndexOf(".") + 5));
				return first - second;
			}
		});

		for(String str : fileNameArray) {
			FileInputStream inputStream = new FileInputStream(str);
			FileOutputStream outputStream = new FileOutputStream(mainFile, true);
			byte[] tmp = new byte[inputStream.available()];
			inputStream.read(tmp);
			outputStream.write(tmp);
			inputStream.close();
			outputStream.close();
		}
    }
}