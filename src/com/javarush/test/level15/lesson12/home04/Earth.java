package com.javarush.test.level15.lesson12.home04;

public class Earth implements Planet
{
    private static Earth earth;

    private Earth()
    {
    }

    public static Earth getInstance(){
        if(earth == null){
            earth = new Earth();
        }
        return earth;
    }
}
