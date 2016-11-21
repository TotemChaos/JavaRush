package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
		FileReader fileReader = new FileReader(args[0]);
		int data;
		int countSymbol = 0;
		int countGap = 0;
		while((data = fileReader.read()) != -1)	{
			if(Character.isSpaceChar(data))	{
				countGap++;
				countSymbol++;
			}else if(Character.isDefined(data)) {
				countSymbol++;
			}
		}
		System.out.println(String.format("%.2f", ((float)countGap / countSymbol * 100)).replace(',', '.'));
		fileReader.close();
    }
}
