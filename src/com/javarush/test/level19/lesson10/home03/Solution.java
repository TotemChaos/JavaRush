package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args)throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        String data;

        while((data = reader.readLine()) != null)	{
            String[] nameLine = data.split("\\s\\d+\\s\\d+\\s\\d+");
            String[] dateLine = data.split(" ");

            String day = dateLine[dateLine.length - 3];
            String month = dateLine[dateLine.length - 2];
            String year = dateLine[dateLine.length - 1];

            Date date = dateFormat.parse(day + " " + month + " " + year);

            PEOPLE.add(new Person(nameLine[0], date));
        }
        reader.close();

        for(Person p : PEOPLE)
            System.out.println(p.getName() + " " + dateFormat.format(p.getBirthday()));
    }
}