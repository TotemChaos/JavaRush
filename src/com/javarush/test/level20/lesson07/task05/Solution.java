package com.javarush.test.level20.lesson07.task05;

import java.io.*;

/* Переопределение сериализации
Сделайте так, чтобы после десериализации нить runner продолжила работать.
Ключевые слова объекта runner менять нельзя.
Hint/Подсказка:
Конструктор не вызывается при сериализации, только инициализируются все поля.
*/
public class Solution implements Serializable, Runnable {
    transient private Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        while(--speed > 0) {
            System.out.println(speed);

            try {
                Thread.sleep(1000);
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
        }
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
   }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        runner = new Thread(this);
        runner.start();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(11);
        Solution solution1 = new Solution(2);

        try{
            File testFile = File.createTempFile("testFile", null);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(testFile));
            oos.writeObject(solution);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(testFile));
            solution1 = (Solution)ois.readObject();
            ois.close();

            testFile.deleteOnExit();
        }catch(IOException e)   {
            e.printStackTrace();
        }catch(ClassNotFoundException e)    {
            System.out.println("Solution class not found");
            e.printStackTrace();
        }
    }
}
