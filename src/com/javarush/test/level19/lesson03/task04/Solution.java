package com.javarush.test.level19.lesson03.task04;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("c:\\data.txt"), "Cp1251");
        PersonScanner personScanner = new PersonScannerAdapter(scan);
        System.out.println(personScanner.read());
        personScanner.close();
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scanner;
        public PersonScannerAdapter(Scanner scanner)    {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            String[] person = scanner.nextLine().split(" ");
            Date date = (new GregorianCalendar(Integer.parseInt(person[5]),Integer.parseInt(person[4]) - 1, Integer.parseInt(person[3]))).getTime();
            return new Person(person[1], person[2], person[0], date);
        }

        @Override
        public void close() throws IOException {
            scanner.close();
        }
    }
}
