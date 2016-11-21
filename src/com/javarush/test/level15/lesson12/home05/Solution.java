package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    public Solution(String string)
    {
    }
    public Solution(Integer string)
    {
    }
    public Solution(Double string)
    {
    }

    protected Solution(Integer string, String s)
    {
    }
    protected Solution(Double string, String s)
    {
    }
    protected Solution(Double string, Integer s)
    {
    }

    Solution()
    {
    }
    Solution(String string, Double s)
    {
    }
    Solution(String string, Integer s)
    {
    }

    private Solution(String string, String s)
    {

    }
    private Solution(Integer string, Integer s)
    {

    }
    private Solution(Double string, Double s)
    {

    }
}

