package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user = new User();
            user.setFirstName("Муторная");
            user.setLastName("Задача");
            user.setBirthDate(new Date());
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);

            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();
            your_file_name.deleteOnExit();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);

            for(User user : users) {
                String userValidator = user != null ? "YES" : "NO";
                printWriter.println(userValidator);

                if(user != null) {
                    if(user.getFirstName() != null) {
                        printWriter.println(user.getFirstName());
                    }else printWriter.println("NO_FIRST_NAME");

                    if(user.getLastName() != null) {
                        printWriter.println(user.getLastName());
                    }else printWriter.println("NO_LAST_NAME");

                    if(user.getBirthDate() != null) {
                        printWriter.println(user.getBirthDate().getTime());
                    }else printWriter.println("NO_BIRTH_DATE");

                    printWriter.println(user.isMale());

                    if(user.getCountry() != null) {
                        printWriter.println(user.getCountry());
                    }else printWriter.println("NO_COUNTRY");
                }
            }

            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            while(reader.ready())   {
                String firstName, lastName, birthDate, country;
                User user = new User();

                if("YES".equals(reader.readLine())) {
                    if (!"NO_FIRST_NAME".equals(firstName = reader.readLine())) {
                        user.setFirstName(firstName);
                    } else user.setFirstName(null);

                    if (!"NO_LAST_NAME".equals(lastName = reader.readLine())) {
                        user.setLastName(lastName);
                    } else user.setLastName(null);

                    if (!"NO_BIRTH_DATE".equals(birthDate = reader.readLine())) {
                        user.setBirthDate(new Date(Long.parseLong(birthDate)));
                    } else user.setBirthDate(null);

                    user.setMale(Boolean.valueOf(reader.readLine()));

                    if (!"NO_COUNTRY".equals(country = reader.readLine())) {
                        user.setCountry(User.Country.valueOf(country));
                    } else user.setCountry(null);
                } else user = null;

                users.add(user);
            }
        }
    }
}
