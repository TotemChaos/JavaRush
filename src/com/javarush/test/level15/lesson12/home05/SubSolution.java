package com.javarush.test.level15.lesson12.home05;

public class SubSolution extends Solution
{
    public SubSolution(String string)
    {
        super(string);
    }
    public SubSolution(Integer string)
    {
        super(string);
    }
    public SubSolution(Double string)
    {
        super(string);
    }

    protected SubSolution(Integer string, String s)
    {
        super(string, s);
    }
    protected SubSolution(Double string, String s)
    {
        super(string, s);
    }
    protected SubSolution(Double string, Integer s)
    {
        super(string, s);
    }

    SubSolution()
    {
        super();
    }
    SubSolution(String string, Double s)
    {
        super(string, s);
    }
    SubSolution(String string, Integer s)
    {
        super(string, s);
    }

    private SubSolution(String string, String s)
    {

    }
    private SubSolution(Integer string, Integer s)
    {

    }
    private SubSolution(Double string, Double s)
    {

    }
}
