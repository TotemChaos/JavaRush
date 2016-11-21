package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;

public class Solution
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader brf = new BufferedReader(new FileReader(br.readLine()));
		BufferedWriter bwf = new BufferedWriter(new FileWriter(br.readLine()));

		String[] tmp = brf.readLine().split(" ");
		for (String item : tmp) {
			int e = (int) Math.round(Double.parseDouble(item));
			bwf.write(String.valueOf(e) + " ");
		}

		br.close();
		brf.close();
		bwf.close();
	}
}