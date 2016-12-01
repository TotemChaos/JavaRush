package com.javarush.test.level20.lesson07.task02;

import java.io.*;

/* OutputToConsole
Класс OutputToConsole должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку.
Сигнатуры методов менять нельзя.
*/
public class Solution {
    public static String greeting = "Hello world";

    public static void main(String args[])  {
        OutputToConsole console = new OutputToConsole(7);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.tmp"));
            oos.writeObject(console);
            oos.close();
        }catch(IOException e)   {
            e.printStackTrace();
        }

        console.outputToConsole(6);

        console = null;

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp.tmp"));
            console = (OutputToConsole)ois.readObject();
            ois.close();
        }catch(IOException e)   {
            e.printStackTrace();
            return;
        }catch(ClassNotFoundException e)    {
            System.out.println("OutputToConsole class not found");
            e.printStackTrace();
            return;
        }

        console.outputToConsole(5);
    }

    /**
     * OutputToConsole is the inner base class for improving your attentiveness.
     * An OutputToConsole object encapsulates the information needed
     * for the displaying [greeting] variable to the console by character.
     * @author JavaRush
     */
    public static class OutputToConsole implements Externalizable {
        private int i = 8;

        /**
         * @param out A stream for an externalization
         * @throws java.io.IOException
         */
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(i);
        }

        /**
         * @param in A stream for a de-externalization
         * @throws java.io.IOException
         * @throws ClassNotFoundException
         */
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            i = in.readInt();
        }

        public OutputToConsole() {
            super();
        }

        /**
         * Class constructor specifying fake private field [i].
         */
        public OutputToConsole(int ii) {
            this.i = ii;
        }

        /**
         * Outputs to the console a static field of Solution class [greeting].
         * Has to use [charAt] method of String class
         */
        public void outputToConsole(int ii) {
            for (int i = 0; i < greeting.length(); i++) {
                System.out.write(greeting.charAt(i));
            }
        }
    }
}
