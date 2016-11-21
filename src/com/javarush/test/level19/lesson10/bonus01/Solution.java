package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args)throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader readFile1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedReader readFile2 = new BufferedReader(new FileReader(reader.readLine()));

        List<String> file1 = new ArrayList<>();
        List<String> file2 = new ArrayList<>();

        while(readFile1.ready())
            file1.add(readFile1.readLine());

        while(readFile2.ready())
            file2.add(readFile2.readLine());

        while(file1.size() > 0 & file2.size() > 0) {
            if(file1.get(0).equals(file2.get(0))) {
                lines.add(new LineItem(Type.SAME, file1.get(0)));
                file1.remove(0);
                file2.remove(0);
            }else if(!file1.get(0).equals(file2.get(0)) & file1.size() != 0 && file1.get(1).equals(file2.get(0)))   {
                lines.add(new LineItem(Type.REMOVED, file1.get(0)));
                file1.remove(0);
            }else if(!file1.get(0).equals(file2.get(0)) & file2.size() != 0 && file1.get(0).equals(file2.get(1)))   {
                lines.add(new LineItem(Type.ADDED, file2.get(0)));
                file2.remove(0);
            }

            if(file1.size() == 0 && file2.size() > 0)    {
                lines.add(new LineItem(Type.ADDED, file2.get(0)));
            }else if(file2.size() == 0 && file1.size() > 0){
                lines.add(new LineItem(Type.REMOVED, file1.get(0)));
            }
        }

        reader.close();
        readFile1.close();
        readFile2.close();

        for(LineItem item : lines)
            System.out.println(item.getType() + " " + item.getline());
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        public String getType() {
            return type.toString();
        }

        public String getline(){
            return line;
        }
    }
}
