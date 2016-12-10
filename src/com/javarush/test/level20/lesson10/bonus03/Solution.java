package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> words = detectAllWords(crossword, "home", "same");
        for(Word word : words)
            System.out.println(word.toString());
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordsList = new ArrayList<>();

        for(String s : words) {
            Word word = new Word(s);

            for(int i = 0; i < crossword.length; i++)   {
                for(int j = 0; j < crossword[i].length; j++)    {
                    if(s.charAt(0) == crossword[i][j]) {
                        int tmpLen = s.length() - 1;
                        StringBuilder sb = new StringBuilder();

                        try {
                            int len = s.length(), tmpI = i;

                            while (len-- > 0)
                                sb.append((char) crossword[tmpI++][j]);

                            if (s.equals(sb.toString())) {
                                word.setStartPoint(j, i);
                                word.setEndPoint(j, i + tmpLen);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {sb.delete(0, sb.length());}

                        try {
                            int len = s.length(), tmpI = i;

                            while (len-- > 0)
                                sb.append((char) crossword[tmpI--][j]);

                            if (s.equals(sb.toString())) {
                                word.setStartPoint(j, i);
                                word.setEndPoint(j, i - tmpLen);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {sb.delete(0, sb.length());}

                        try {
                            int len = s.length(), tmpJ = j;

                            while (len-- > 0)
                                sb.append(String.valueOf((char) crossword[i][tmpJ++]));

                            if (s.equals(sb.toString())) {
                                word.setStartPoint(j, i);
                                word.setEndPoint(j + tmpLen, i);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {sb.delete(0, sb.length());}

                        try {
                            int len = s.length(), tmpJ = j;

                            while (len-- > 0)
                                sb.append(String.valueOf((char) crossword[i][tmpJ--]));

                            if (s.equals(sb.toString())) {
                                word.setStartPoint(j, i);
                                word.setEndPoint(j - tmpLen, i);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {sb.delete(0, sb.length());}

                        try {
                            int len = s.length(), tmpI = i, tmpJ = j;

                            while (len-- > 0)
                                sb.append(String.valueOf((char) crossword[tmpI++][tmpJ++]));

                            if (s.equals(sb.toString())) {
                                word.setStartPoint(j, i);
                                word.setEndPoint(j + tmpLen, i + tmpLen);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {sb.delete(0, sb.length());}

                        try {
                            int len = s.length(), tmpI = i, tmpJ = j;

                            while (len-- > 0)
                                sb.append(String.valueOf((char) crossword[tmpI--][tmpJ--]));

                            if (s.equals(sb.toString())) {
                                word.setStartPoint(j, i);
                                word.setEndPoint(j - tmpLen, i - tmpLen);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {sb.delete(0, sb.length());}

                        try {
                            int len = s.length(), tmpI = i, tmpJ = j;

                            while (len-- > 0)
                                sb.append(String.valueOf((char) crossword[tmpI++][tmpJ--]));

                            if (s.equals(sb.toString())) {
                                word.setStartPoint(j, i);
                                word.setEndPoint(j - tmpLen, i + tmpLen);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {sb.delete(0, sb.length());}

                        try {
                            int len = s.length(), tmpI = i, tmpJ = j;

                            while (len-- > 0)
                                sb.append(String.valueOf((char) crossword[tmpI--][tmpJ++]));

                            if (s.equals(sb.toString())) {
                                word.setStartPoint(j, i);
                                word.setEndPoint(j + tmpLen, i - tmpLen);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {sb.delete(0, sb.length());}
                    }
                }
            }
            wordsList.add(word);
        }
        return wordsList;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}