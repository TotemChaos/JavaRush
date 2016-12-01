package com.javarush.test.level20.lesson07.task01;

import java.io.*;

/* Externalizable для апартаментов
Реализуйте интерфейс Externalizable для класса Apartment
Подумайте, какие поля не нужно сериализовать.
*/
public class Solution {

    public static void main(String args[])  {
        Apartment apartment = new Apartment("Street", 2016);

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.tmp"));
            oos.writeObject(apartment);
            oos.close();
        }catch(IOException e)   {
            e.printStackTrace();
        }

        System.out.println(apartment.toString());

        apartment = null;

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp.tmp"));
            apartment = (Apartment) ois.readObject();
            ois.close();
        }catch(IOException e)   {
            e.printStackTrace();
            return;
        }catch(ClassNotFoundException e)    {
            System.out.println("Apartment class not found");
            e.printStackTrace();
            return;
        }

        System.out.println(apartment.toString());
    }

    public static class Apartment implements Externalizable {

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment() { super(); }

        public Apartment(String adr, int y) {
            address = adr;
            year = y;
        }

        /**
         * Prints out the fields. used for testing!
         */
        public String toString() {
            return("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(address);
            out.writeInt(year);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            address = (String)in.readObject();
            year = in.readInt();
        }
    }
}
