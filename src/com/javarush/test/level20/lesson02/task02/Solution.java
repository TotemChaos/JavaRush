package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
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
            user.setFirstName("Путя");
            user.setLastName("Пувич");
            user.setBirthDate(new Date());
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);

            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getBirthDate() + " " + user.isMale() + " " + user.getCountry());
            System.out.println(loadedObject.users.size());
            System.out.println(javaRush.users.containsAll(loadedObject.users));

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
                String trueFalse = user != null ? "true" : "false";
                printWriter.println(trueFalse);

                if(user.getFirstName() != null)
                    printWriter.println(user.getFirstName());

                if(user.getLastName() != null)
                    printWriter.println(user.getLastName());

                if(user.getBirthDate() != null)
                    printWriter.println(user.getBirthDate());

                if(user.isMale() != false)
                    printWriter.println(user.isMale());

                if(user.getCountry().getDisplayedName() != null)
                    printWriter.println(user.getCountry().getDisplayedName());
            }

            printWriter.flush();
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
            String firstName;
            String lastName;
            String birthDate;

            String trueFalse = reader.readLine();

            if(trueFalse.equals("true")) {
                for(User user : users)  {
                    if (!"null".equals(firstName = reader.readLine()))
                        user.setFirstName(firstName);

                    if (!"null".equals(lastName = reader.readLine()))
                        user.setLastName(lastName);

                    if (!"null".equals(birthDate = reader.readLine()))
                        user.setBirthDate(dateFormat.parse(birthDate));

                    if("true".equals(reader.readLine()))
                        user.setMale(true);

                    if(User.Country.RUSSIA.getDisplayedName().equals(reader.readLine())) {
                        user.setCountry(User.Country.RUSSIA);
                    }else if(User.Country.UKRAINE.getDisplayedName().equals(reader.readLine())) {
                        user.setCountry(User.Country.UKRAINE);
                    }else user.setCountry(User.Country.OTHER);
                }
            }

            reader.close();
        }
    }
}
