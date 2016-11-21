package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader readFile = new BufferedReader(new FileReader(reader.readLine()));
		FileWriter writeFile = new FileWriter(reader.readLine());

		Pattern p = Pattern.compile("^\\d+$");
		String tmp;

		while((tmp = readFile.readLine()) != null)	{
			String[] line = tmp.split("\\s");
			for(String s: line)
				if (p.matcher(s).find())
					writeFile.write(s + " ");
		}

		reader.close();
		readFile.close();
		writeFile.close();
    }
}
