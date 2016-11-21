package com.javarush.test.level14.lesson08.home05;

public class Computer
{
    private Monitor monitor;
    private Mouse mouse;
    private Keyboard keyboard;

    public Computer()
    {
        monitor = new Monitor();
        mouse = new Mouse();
        keyboard = new Keyboard();
    }

    public Monitor getMonitor()
    {
        return monitor;
    }

    public Mouse getMouse()
    {
        return mouse;
    }

    public Keyboard getKeyboard()
    {
        return keyboard;
    }
}
