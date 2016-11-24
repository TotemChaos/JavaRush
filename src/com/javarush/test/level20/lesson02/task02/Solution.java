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
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();

            User user = new User();
            user.setFirstName("Путя");
            user.setLastName("Пувич");
            user.setBirthDate(new Date());
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);

            User user2 = new User();
            user2.setFirstName(null);
            user2.setLastName(null);
            user2.setBirthDate(null);
            user2.setMale(false);
            user2.setCountry(null);

            //User user3 = new User();

            javaRush.users.add(user);
            javaRush.users.add(user2);
            //javaRush.users.add(user3);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);

            for(User u : loadedObject.users)  {
                System.out.println(u.getFirstName() + u.getLastName() + u.getBirthDate() + u.isMale() + u.getCountry());
            }

            outputStream.close();
            inputStream.close();
            your_file_name.deleteOnExit();

        } catch (IOException e) {
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);

            for(User user : users) {
                if(user != null) {
                    printWriter.println(user.getFirstName());

                    printWriter.println(user.getLastName());

                    if(user.getBirthDate() != null) {
                        printWriter.println(user.getBirthDate().getTime());
                    }

                    printWriter.println(user.isMale());

                    if(user.getCountry().getDisplayedName() != null) {
                        printWriter.println(user.getCountry().getDisplayedName());
                    }
                }
            }

            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            while(reader.ready())   {
                User user = new User();

                user.setFirstName(reader.readLine());

                user.setLastName(reader.readLine());

                user.setBirthDate(new Date(Long.parseLong(reader.readLine())));

                if("true".equals(reader.readLine()))
                    user.setMale(true);

                if(User.Country.RUSSIA.getDisplayedName().equals(reader.readLine())) {
                    user.setCountry(User.Country.RUSSIA);
                }else if(User.Country.UKRAINE.getDisplayedName().equals(reader.readLine())) {
                    user.setCountry(User.Country.UKRAINE);
                }else user.setCountry(User.Country.OTHER);

                users.add(user);
            }
        }
    }
}
