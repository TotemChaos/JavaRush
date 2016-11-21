package com.javarush.test.level18.lesson10.home08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try {
            BufferedReader readerBuffered = new BufferedReader(new InputStreamReader(System.in));
            String readFileName;

            while (!"exit".equals(readFileName = readerBuffered.readLine())) {
                new ReadThread(readFileName).start();
            }

            readerBuffered.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) throws IOException {
            this.fileName = fileName;
        }

        public void run() {
            try {
                FileInputStream inputStream = new FileInputStream(fileName);
                Map<Integer, Integer> maxValue = new HashMap<Integer, Integer>();
                int value = 0;
                int valueKey = 0;

                while (inputStream.available() > 0) {
                    int data = inputStream.read();

                    if (maxValue.containsKey(data)) {
                        maxValue.put(data, maxValue.get(data).intValue() + 1);
                    } else maxValue.put(data, 1);
                }

                for (Map.Entry<Integer, Integer> entry : maxValue.entrySet()) {
                    if (value <= entry.getValue()) {
                        value = entry.getValue();
                        valueKey = entry.getKey();
                    }
                }
                resultMap.put(fileName, valueKey);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}