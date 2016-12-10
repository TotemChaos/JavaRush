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
        byte count = 0;

        for(int i = 1; i < N; i++)  {
            int number = i, tmpSize = i, sum = 0;
            byte size = 1;

            while((tmpSize = tmpSize/10) > 0)
                size++;

            while(number > 0) {
                int tmpNumber =  number % 10;
                int tmpSum = tmpNumber;

                for(int j = 1; j < size; j++)
                    tmpSum *= tmpNumber;

                sum += tmpSum;

                number /= 10;
            }

            if(sum == i)
                tmpResult[count++] = i;
        }

        int[] result = Arrays.copyOf(tmpResult, count);

        return result;
    }

    public static void main(String[] args) {
        Long t0 = System.currentTimeMillis();

        int[] array = getNumbers(100000000 );

        Long t1 = System.currentTimeMillis();

        System.out.println("Time: " + (t1 - t0)/ 1000d + " sec");
        System.out.println("Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");

        for(int arr : array)
            System.out.print(arr + " ");
    }
}