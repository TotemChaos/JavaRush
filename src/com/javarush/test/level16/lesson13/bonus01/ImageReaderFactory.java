package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.BmpReader;
import com.javarush.test.level16.lesson13.bonus01.common.PngReader;
import com.javarush.test.level16.lesson13.bonus01.common.ImageReader;
import com.javarush.test.level16.lesson13.bonus01.common.ImageTypes;
import com.javarush.test.level16.lesson13.bonus01.common.JpgReader;

public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes image)  {
        if(image == ImageTypes.JPG)  {
            return new JpgReader();
        } else if(image == ImageTypes.PNG)  {
            return new PngReader();
        }else if(image == ImageTypes.BMP)  {
            return new BmpReader();
        }else throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
