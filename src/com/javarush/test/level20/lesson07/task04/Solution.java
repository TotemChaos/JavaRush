package com.javarush.test.level20.lesson07.task04;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Serializable Solution
Сериализуйте класс Solution.
Подумайте, какие поля не нужно сериализовать, пометить ненужные поля — transient.
Объект всегда должен содержать актуальные итоговые данные.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать файл, открыть поток на чтение (input stream) и на запись(output stream)
2) создать экземпляр класса Solution - savedObject
3) записать в поток на запись savedObject (убедитесь, что они там действительно есть)
4) создать другой экземпляр класса Solution с другим параметром
5) загрузить из потока на чтение объект - loadedObject
6) проверить, что savedObject.string равна loadedObject.string
7) обработать исключения
*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        Solution savedObject = new Solution(4);
        Solution loadedObject = new Solution(5);

        try{
        File testFile = File.createTempFile("testFile", null);

        InputStream in = new FileInputStream(testFile);
        OutputStream out = new FileOutputStream(testFile);

        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(savedObject);
        out.close();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(in);
        loadedObject = (Solution)ois.readObject();
        in.close();
        ois.close();

        testFile.deleteOnExit();

        }catch(IOException e)   {
            e.printStackTrace();

        }catch(ClassNotFoundException e) {
            System.out.println("Solution class not found");
            e.printStackTrace();
        }

        System.out.println(savedObject.toString());
        System.out.println(loadedObject.toString());
        System.out.println(savedObject.equals(loadedObject));
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Solution other = (Solution) obj;
         if (!other.toString().equals(other.toString()))
            return false;
        return true;
    }
}
