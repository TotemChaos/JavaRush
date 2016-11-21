package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args)throws Exception {
		Map<String, Double> map = new HashMap<String, Double>();
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		Double maxValue = Double.MIN_VALUE;

		while(reader.ready())	{
			String[] tmp = reader.readLine().split(" ");

			String key = tmp[0];
			Double value = Double.parseDouble(tmp[1]);

			if(map.containsKey(key))	{
				map.put(key, map.get(key) + value);
			}else map.put(key, value);
		}
		reader.close();

		for(Double v : map.values())
			if (v > maxValue)
				maxValue = v;

		for(Map.Entry<String, Double> e : map.entrySet())
			if(maxValue.equals(e.getValue()))
				System.out.println(e.getKey());
	}
}
