package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args)throws Exception {
		BufferedReader readFile = new BufferedReader(new FileReader(args[0]));
		BufferedWriter writeFile = new BufferedWriter(new FileWriter(args[1]));
		Pattern p = Pattern.compile(".*\\d.*");

		while(readFile.ready())	{
			String[] temp = readFile.readLine().split(" ");

			for(String tmp : temp)
				if (p.matcher(tmp).find())
					writeFile.write(tmp + " ");
		}

		readFile.close();
		writeFile.close();
    }
}