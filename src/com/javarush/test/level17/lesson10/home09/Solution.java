package com.javarush.test.level17.lesson10.home09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException
    {

            BufferedReader readerConsol = new BufferedReader(new InputStreamReader(System.in));
            String firstFile = readerConsol.readLine();
            String secondFile = readerConsol.readLine();

            BufferedReader readerFile1 = new BufferedReader(new FileReader(firstFile));
            BufferedReader readerFile2 = new BufferedReader(new FileReader(secondFile));
            String line1;
            String line2;
            while ((line1 = readerFile1.readLine()) != null)
            {
                allLines.add(line1);
            }
            while ((line2 = readerFile2.readLine()) != null)
            {
                forRemoveLines.add(line2);
            }


        new Solution().joinData();
    }

    public void joinData() throws CorruptedDataException
    {
        /*int count = 0;

        for (int i = 0; i < forRemoveLines.size(); i++)   {
                if((Collections.frequency(allLines, forRemoveLines.get(i))) > 0)  {
                    count++;
                }
        }

        if (count == forRemoveLines.size()) {
            for (int i = 0; i < allLines.size(); i++)   {
                for (int j = 0; j < forRemoveLines.size(); j++)   {
                    if(allLines.get(i).contains(forRemoveLines.get(j)) & allLines.size() >= 1)    {
                        allLines.remove(i);
                    }
                }
            }
            */
        if(allLines.containsAll(forRemoveLines))   {
            allLines.removeAll(forRemoveLines);

        } else  {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
