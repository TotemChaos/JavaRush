package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
		PrintStream consoleStream = System.out;

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		System.setOut(stream);

		testString.printSomething();

		String[] tmp = outStream.toString().split("\\s");

		int result = 0;
		if(tmp[1].equals("+")){
			result = Integer.parseInt(tmp[0]) + Integer.parseInt(tmp[2]);
		}else if(tmp[1].equals("-")){
			result = Integer.parseInt(tmp[0]) - Integer.parseInt(tmp[2]);
		}else if(tmp[1].equals("*")){
			result = Integer.parseInt(tmp[0]) * Integer.parseInt(tmp[2]);
		}


		System.setOut(consoleStream);
		System.out.printf("%d %s %d = %d", Integer.parseInt(tmp[0]), tmp[1], Integer.parseInt(tmp[2]), result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

