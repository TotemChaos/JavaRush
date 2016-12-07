package com.javarush.test.level20.lesson10.bonus01;

import java.util.Arrays;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int[] tmpResult = new int[100];
        int count1 = 0;

        for(int i = 1; i < N; i++)  {
            int number = i, count2 = 0, sum = 0;
            int[] tmpNumberArr = new int[String.valueOf(i).length()];

            while(number > 0) {
                tmpNumberArr[count2++] = number % 10;
                number /= 10;
            }

            for(int arr : tmpNumberArr) {
                int tmp = arr;

                for(int j = 1; j < tmpNumberArr.length; j++)
                    tmp *= arr;

                sum += tmp;
            }

            if(sum == i)
                tmpResult[count1++] = i;
        }

        int[] result = Arrays.copyOf(tmpResult, count1);

        return result;
    }

    public static void main(String[] args) {
        Long t0 = System.currentTimeMillis();
        long memoryStart = Runtime.getRuntime().freeMemory();

        int[] array = getNumbers(10000 );

        for(int arr : array)
            System.out.println(arr);

        System.out.println();

        long memoryEnd = Runtime.getRuntime().freeMemory();
        System.out.println("Memory: " + (memoryStart - memoryEnd) + " byte"); //Max_50.000.000

        Long t1 = System.currentTimeMillis();
        System.out.println("Time: " + (t1 - t0) + " ms"); //Max_10.000
    }
}
