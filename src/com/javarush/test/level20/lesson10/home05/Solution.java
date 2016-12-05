package com.javarush.test.level20.lesson10.home05;

import java.io.*;
import java.util.logging.Logger;

/* Сериализуйте Person
Сериализуйте класс Person стандартным способом. При необходимости поставьте полям модификатор transient.
*/
public class Solution implements Serializable {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex implements Serializable {
        MALE,
        FEMALE
    }

    public static void main(String args[])  {
        Person person = new Person("first", "last", "Russia", Sex.MALE);

        try{
            File tmpFile = File.createTempFile("tmpFile", null);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmpFile));
            oos.writeObject(person);
            oos.close();

            System.out.println(person.fullName + " " + person.greetingString + " " + person.country + " " + person.sex + " " + person.logger);
            person = null;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmpFile));
            person = (Person)ois.readObject();
            ois.close();

            person.logger = Logger.getLogger(String.valueOf(Person.class));
            System.out.println(person.fullName + " " + person.greetingString + " " + person.country + " " + person.sex + " " + person.logger);

        }catch(IOException e)   {
            e.printStackTrace();
        }catch(ClassNotFoundException e)    {
            System.out.println("Person class not found");
            e.printStackTrace();
        }
    }
}
